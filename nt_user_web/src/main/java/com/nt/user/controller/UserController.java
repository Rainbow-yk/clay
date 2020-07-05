package com.nt.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.Result;
import com.nt.pojo.TbUser;
import com.nt.user.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author 99362
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    /**
     * 用户更换头像
     * @param multipartFile
     * @return
     */
    @RequestMapping("/changeAvatar.do")
    public Result changeAvatar(@RequestParam("avatar") MultipartFile multipartFile,@RequestParam("mobile") String mobile) {
        try {
            // 获取文件名
            String filename = multipartFile.getOriginalFilename();
            System.out.println(filename);
            int index = filename.lastIndexOf(".");
            String extName = filename.substring(index);
            String newName = UUID.randomUUID() + extName;
            // 创建一个目录存放上传的文件
            File file = new File("D://upload/avatar/"+mobile+"/", newName);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 将上传的文件保存到自定义的文件夹中
            multipartFile.transferTo(file);
            String path = file.getPath();
            int indexOf = path.indexOf("\\");
            String filePath = path.substring(indexOf);
            userService.updateAvatar(filePath, mobile);
            return new Result("修改成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result("修改失败", false);
        }
    }

    @RequestMapping("update.do")
    public Result update(@RequestBody TbUser user) {
        userService.update(user);
        return new Result("修改成功", true);
    }



}
