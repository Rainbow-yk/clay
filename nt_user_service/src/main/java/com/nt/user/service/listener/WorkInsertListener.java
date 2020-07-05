package com.nt.user.service.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nt.pojo.TbWorkInfo;
import com.nt.user.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import java.util.List;

/**
 * @author 99362
 */
@Component("insertListener")
public class WorkInsertListener implements MessageListener {

    @Autowired
    private SearchService searchService;

    /**
     * 对添加数据到solr的监听处理
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String objString = textMessage.getText();
            JSONObject jsonObject = JSONObject.parseObject(objString);
            TbWorkInfo workInfo = JSON.toJavaObject(jsonObject, TbWorkInfo.class);
            searchService.importData(workInfo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
