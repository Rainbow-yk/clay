package com.nt.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.nt.pojo.TbWorkInfo;
import com.nt.user.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 99362
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 高亮查询，关键高亮显示
     * @param keyword
     * @return
     */
    @Override
    public List<TbWorkInfo> search(String keyword) {
        // 高亮查询对象
        HighlightQuery highlightQuery = new SimpleHighlightQuery();
        HighlightOptions options = new HighlightOptions();
        List<String> list = new ArrayList<>();
        list.add("item_title");
        list.add("item_brief");
        options.addFields(list);
        // 将关键字设置为红色高亮显示
        options.setSimplePrefix("<font style='color:red'>");
        options.setSimplePostfix("</font>");
        highlightQuery.setHighlightOptions(options);
        // 设置一页显示100条数据
        highlightQuery.setOffset(0);
        highlightQuery.setRows(100);
        Criteria criteria = new Criteria("item_keywords");
        criteria.is(keyword);
        highlightQuery.addCriteria(criteria);
        HighlightPage<TbWorkInfo> page = solrTemplate.queryForHighlightPage(highlightQuery, TbWorkInfo.class);

        //循环高亮入口集合
        for(HighlightEntry<TbWorkInfo> h: page.getHighlighted()){
            //获取原实体类
            TbWorkInfo workInfo = h.getEntity();
            // 获取高亮列表(高亮域的个数) h.getHighlights() // 获取每个域有可能存储多值  h.getSnipplets() // 获取要高亮的内容 h.getSnipplets.get(0)
            if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
                //设置高亮的结果
                workInfo.setTitle(h.getHighlights().get(0).getSnipplets().get(0));
            }
        }
        return page.getContent();
    }

    /**
     * 普通查询，关键字不会高亮显示
     * @param keyword
     * @return
     */
    @Override
    public List<TbWorkInfo> searchNotHighlight(String keyword) {
        Query query = new SimpleQuery();
        Criteria criteria = new Criteria("item_keywords");
        criteria.is(keyword);
        query.addCriteria(criteria);
        query.setOffset(0);
        query.setRows(100);
        ScoredPage<TbWorkInfo> page = solrTemplate.queryForPage(query, TbWorkInfo.class);
        return page.getContent();
    }

    /**
     * 通过作品的id来删除solr中对应的数据
     * @param workId
     */
    @Override
    public void deleteData(long workId) {
        SolrDataQuery query = new SimpleQuery();
        Criteria criteria = new Criteria("id");
        query.addCriteria(criteria.is(workId));
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

    /**
     * 向solr中插入数据
     * @param workInfo
     */
    @Override
    public void importData(TbWorkInfo workInfo) {
        solrTemplate.saveBean(workInfo);
        solrTemplate.commit();
    }
}
