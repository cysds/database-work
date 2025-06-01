package com.cysds.dao;

import com.cysds.dao.po.SC;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 谢玮杰
 * @description:
 * @create: 2025-05-17 15:22
 **/
@Mapper
public interface ISCDao {
    List<SC> queryAllSC();
    List<SC> querySCBySno(String sNo);
    List<SC> querySCByCno(String cNo);
    void deleteSC(String sNo, String cNo);
    void updateSC(SC sc);
    void addSC(SC sc);
    void deleteSCBySno(String sNo);
    void deleteSCByCno(String cNo);
}
