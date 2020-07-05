package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.nt.pojo.Result;
import com.nt.pojo.TbUser;
import com.nt.pojo.TbWorkInfo;
import com.nt.user.service.CategoryService;
import com.nt.user.service.UserService;
import com.nt.user.service.WorkService;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/work")
public class WorkController {

    @Reference
    private WorkService workService;

    @Reference
    private CategoryService categoryService;

    @Reference
    private UserService userService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("queueTextDestinationDelete")
    private Destination deleteDestination;

    @Autowired
    @Qualifier("queueTextDestination")
    private Destination destination;

    /**
     * 用户上传作品
     * @param workInfo
     * @param ids
     * @return
     */
    @RequestMapping("/issue.do")
    public Result issue(@RequestBody TbWorkInfo workInfo, String ids) {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        TbUser user = userService.findOne(mobile);
        workInfo.setAuthorId(user.getId());
        int workId = workService.save(workInfo);
        // 发送消息：插入数据之后更新solr
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TbWorkInfo work = workService.findOneById(workId);
                TextMessage textMessage = session.createTextMessage();
                String workString = JSON.toJSONString(work);
                textMessage.setText(workString);
                return textMessage;
            }
        });
        String[] categoryIds = ids.split(",");
        categoryService.saveCategoryAndWork(categoryIds, workId);
        return new Result("发布成功", true);
    }

    /**
     * 用户修改作品
     * @param workInfo
     * @param ids
     * @return
     */
    @RequestMapping("/update.do")
    public Result update(@RequestBody TbWorkInfo workInfo, String ids) {
        Integer workId = workInfo.getId();
        workService.update(workInfo);
        // 修改完数据，发消息solr更新数据
        // 先删除
        jmsTemplate.send(deleteDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(Integer.toString(workId));
                return textMessage;
            }
        });
        // 再从新导入
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TbWorkInfo workInfo = workService.findOneById(workId);
                TextMessage textMessage = session.createTextMessage();
                String workString = JSON.toJSONString(workInfo);
                textMessage.setText(workString);
                return textMessage;
            }
        });
        // 更新分类表数据
        categoryService.deleteByWorkId(workId);
        String[] categoryIds = ids.split(",");
        categoryService.saveCategoryAndWork(categoryIds, workId);
        return new Result("修改成功", true);
    }

    /**
     * 作品封面图片的保存到本地（还没存入数据库）
     * @param multipartFile
     * @return
     */
    @RequestMapping("/saveCover.do")
    public Map saveCover(@RequestParam("cover") MultipartFile multipartFile) {
        Map<String,String> map = new HashMap<>();
        try {
            String filename = multipartFile.getOriginalFilename();
            int index = filename.lastIndexOf(".");
            String extName = filename.substring(index);
            String newName = UUID.randomUUID() + extName;
            // 创建一个目录存放上传的文件
            File file = new File("D://upload/avatar/work", newName);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 将上传的文件保存到自定义的文件夹中
            multipartFile.transferTo(file);
            String path = file.getPath();
            int indexOf = path.indexOf("\\");
            String filePath = path.substring(indexOf);
            map.put("cover", filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取所有的作品信息
     * @return
     */
    @RequestMapping("/getAll.do")
    public List<TbWorkInfo> getAll() {
        return workService.getAll();
    }

    /**
     * 通过分类的id来查询作品的信息
     * @param id
     * @return
     */
    @RequestMapping("/findWorkByCategoryId.do")
    public List<TbWorkInfo> findWorkByCategoryId(int id) {
        return workService.findWorkByCategoryId(id);
    }

    /**
     * 通过用户的id来获取该用户的所有信息
     * @param id
     * @return
     */
    @RequestMapping("/getMyWorks.do")
    public List<TbWorkInfo> getMyWorks(int id) {
        return workService.getMyWorks(id);
    }

    /**
     * 通过用户的id来获取该用户的收藏作品
     * @param id
     * @return
     */
    @RequestMapping("/getMyCollect.do")
    public List<TbWorkInfo> getMyCollect(int id) {
        return workService.getMyCollect(id);
    }

    /**
     * 通过作品的id查询用户和作品信息
     * @param id
     * @return
     */
    @RequestMapping("/getWorkInfo.do")
    public TbUser getWorkInfo(int id) {
        TbUser userAndWork = workService.findUserAndWork(id);
        return userAndWork;
    }

    /**
     * 通过作品的id来删除作品
     * @param id
     * @return
     */
    @RequestMapping("delete.do")
    public Result delete(int id) {
        workService.delete(id);
        // 当作品删除之后，需要发送消息更新solr数据
        jmsTemplate.send(deleteDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(Integer.toString(id));
                return textMessage;
            }
        });
        return new Result("删除成功", true);
    }

    /**
     * 通过作品id和用户的id来删除收藏表信息
     * @param id
     * @return
     */
    @RequestMapping("deleteCollect.do")
    public Result deleteCollect(int id) {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        TbUser user = userService.findOne(mobile);
        Integer uid = user.getId();
        workService.deleteCollect(id, uid);
        return new Result("删除成功", true);
    }

    /**
     * 收藏作品，同时更新收藏数
     * @param workInfo
     * @return
     */
    @RequestMapping("/collectWork.do")
    public Result collectWork(@RequestBody TbWorkInfo workInfo) {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        TbUser user = userService.findOne(mobile);
        Integer uid = user.getId();
        Integer wid = workInfo.getId();
        workService.collect(uid, wid);
        workService.update(workInfo);
        return new Result("收藏成功", true);
    }

    /**
     * 通过作品的id查询作品信息
     * @param id
     * @return
     */
    @RequestMapping("/findOneById.do")
    public TbWorkInfo findOneById(int id) {
        return workService.findOneById(id);
    }

}
