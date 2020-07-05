package com.nt.user.service;

import com.nt.pojo.TbUser;
import com.nt.pojo.TbWorkInfo;

import java.util.List;

/**
 * @author 99362
 */
public interface WorkService {

    /**
     * 保存
     * @param workInfo
     */
    int save(TbWorkInfo workInfo);

    /**
     * 获取所有作品
     * @return
     */
    List<TbWorkInfo> getAll();

    List<TbWorkInfo> getMyWorks(int id);

    TbUser findUserAndWork(int id);

    void delete(int id);

    void collect(Integer uid, Integer wid);

    List<TbWorkInfo> getMyCollect(int id);

    void update(TbWorkInfo workInfo);

    void deleteCollect(int id, int uid);

    TbWorkInfo findOneById(int id);

    List<TbWorkInfo> findWorkByCategoryId(int id);
}
