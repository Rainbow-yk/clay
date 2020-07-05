package com.nt.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.Result;
import com.nt.pojo.TbWorkInfo;
import com.nt.user.service.SearchService;
import com.nt.user.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/work")
public class WorkController {

    @Reference
    private WorkService workService;

    @Reference
    private SearchService searchService;

    @Autowired
    private Destination destination;

    @Autowired
    private JmsTemplate jmsTemplate;


    /**
     * 获取所有的作品信息
     * @return
     */
    @RequestMapping("/getAll.do")
    public List<TbWorkInfo> getAll() {
        return workService.getAll();
    }

    /**
     * 通过作品的id来删除作品
     * @param id
     * @return
     */
    @RequestMapping("delete.do")
    public Result delete(int id) {
        workService.delete(id);
        // 删除完更新solr中的数据
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 通过session获取TextMessage
                TextMessage textMessage = session.createTextMessage();
                // 设置需要发送的文本消息
                textMessage.setText(Integer.toString(id));
                return textMessage;
            }
        });
        return new Result("删除成功", true);
    }

    @RequestMapping("/search.do")
    public List<TbWorkInfo> search(String keyword) {
        try {
            keyword = URLDecoder.decode(keyword, "utf-8");
            return searchService.searchNotHighlight(keyword);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
