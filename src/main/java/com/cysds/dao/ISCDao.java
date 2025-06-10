package com.cysds.dao;

import com.cysds.dao.po.SC;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
    // 查询选修某课程的所有学生及其成绩
    List<Map<String, Object>> queryStudentsByCourse(String cNo);
    // 查询选修某课程且成绩大于等于指定分数的记录
    List<Map<String, Object>> querySCByCourseNameAndMinGrade(String courseName, Integer minGrade);
}
