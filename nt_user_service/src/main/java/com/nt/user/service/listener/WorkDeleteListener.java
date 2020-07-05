package com.nt.user.service.listener;

import com.nt.user.service.SearchService;
import com.nt.util.SolrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author 99362
 */
@Component("deleteListener")
public class WorkDeleteListener implements MessageListener {

    @Autowired
    private SearchService searchService;

    /**
     * 对删除solr数据的监听处理
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String id = textMessage.getText();
            searchService.deleteData(Long.parseLong(id));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
