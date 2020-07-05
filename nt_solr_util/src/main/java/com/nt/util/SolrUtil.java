package com.nt.util;

import com.nt.mapper.TbWorkMapper;
import com.nt.pojo.TbWorkInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 99362
 */
@Component
public class SolrUtil {

    @Autowired
    private TbWorkMapper workMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    public void importData() {
        List<TbWorkInfo> list = workMapper.getAll();
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    public void deleteData() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

}
