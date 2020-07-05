package com.nt.mapper;

import com.nt.pojo.TbUser;
import com.nt.pojo.TbWorkInfo;

import java.util.List;

/**
 * @author 99362
 */
public interface TbWorkMapper {

    /**
     * 添加作品
     * @param workInfo
     */
    void save(TbWorkInfo workInfo);

    /**
     * 获取全部作品
     * @return
     */
    List<TbWorkInfo> getAll();

    /**
     * 获取指定用户的作品
     * @param id
     * @return
     */
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
