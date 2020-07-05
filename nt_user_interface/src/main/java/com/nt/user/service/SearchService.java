package com.nt.user.service;

import com.nt.pojo.TbWorkInfo;

import java.util.List;

public interface SearchService {

    /**
     * solr简单查询
     * @param keyword
     * @return
     */
    List<TbWorkInfo> search(String keyword);

    /**
     * solr高亮查询
     * @param keyword
     * @return
     */
    List<TbWorkInfo> searchNotHighlight(String keyword);

    /**
     * 通过作品的id删除solr中的数据
     * @param parseLong
     */
    void deleteData(long parseLong);

    void importData(TbWorkInfo workInfo);
}
