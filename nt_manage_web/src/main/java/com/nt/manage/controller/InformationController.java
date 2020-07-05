package com.nt.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nt.pojo.Result;
import com.nt.pojo.TbInformation;
import com.nt.user.service.InformationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 99362
 */
@RestController
@RequestMapping("information")
public class InformationController {

    @Reference
    private InformationService informationService;

    @RequestMapping("getInformation.do")
    public TbInformation findAll() {
        return informationService.getAll().get(0);
    }

    @RequestMapping("updateInformation.do")
    public Result update(@RequestBody TbInformation information) {
        informationService.update(information);
        return new Result("修改成功", true);
    }

    @RequestMapping("/changePicture.do")
    public Map changePicture(@RequestParam("picture") MultipartFile multipartFile) {

        Map<String,String> map = new HashMap<>();
        try {
            // 获取文件名
            String filename = multipartFile.getOriginalFilename();
            int index = filename.lastIndexOf(".");
            String extName = filename.substring(index);
            String newName = UUID.randomUUID() + extName;
            // 创建一个目录存放上传的文件
            File file = new File("D://upload/avatar/information/", newName);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 将上传的文件保存到自定义的文件夹中
            multipartFile.transferTo(file);
            String path = file.getPath();
            int indexOf = path.indexOf("\\");
            String filePath = path.substring(indexOf);
            map.put("picUrl", filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
