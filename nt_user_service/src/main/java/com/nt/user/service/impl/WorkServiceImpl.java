package com.nt.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.nt.mapper.TbWorkMapper;
import com.nt.pojo.TbUser;
import com.nt.pojo.TbWorkInfo;
import com.nt.user.service.WorkService;
import com.nt.util.SolrUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 99362
 */
@Service
public class WorkServiceImpl implements WorkService {


    @Autowired
    private TbWorkMapper workMapper;

    @Autowired
    private SolrUtil solrUtil;

    @Override
    public int save(TbWorkInfo workInfo) {
        workInfo.setCollectCount(0);
        workInfo.setLikeCount(0);
        workInfo.setDelete(false);
        workMapper.save(workInfo);
        return workInfo.getId();
    }

    @Override
    public List<TbWorkInfo> getAll() {
        return workMapper.getAll();
    }

    @Override
    public List<TbWorkInfo> getMyWorks(int id) {
        return workMapper.getMyWorks(id);
    }

    @Override
    public TbUser findUserAndWork(int id) {
        return workMapper.findUserAndWork(id);
    }

    @Override
    public void delete(int id) {
        workMapper.delete(id);
    }

    @Override
    public void collect(Integer uid, Integer wid) {
        workMapper.collect(uid, wid);
    }

    @Override
    public List<TbWorkInfo> getMyCollect(int id) {
        return workMapper.getMyCollect(id);
    }

    @Override
    public void update(TbWorkInfo workInfo) {
        workMapper.update(workInfo);
    }

    @Override
    public void deleteCollect(int id, int uid) {
        workMapper.deleteCollect(id, uid);
    }

    @Override
    public TbWorkInfo findOneById(int id) {
        return workMapper.findOneById(id);
    }

    @Override
    public List<TbWorkInfo> findWorkByCategoryId(int id) {
        return workMapper.findWorkByCategoryId(id);
    }
}
