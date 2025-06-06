package com.cysds.dao;

import com.cysds.dao.po.Course;
import com.cysds.dao.po.CourseWithStudentCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: 谢玮杰
 * @description:
 * @create: 2025-05-17 15:22
 **/
@Mapper
public interface ICourseDao {
    List<Course> queryAllCourse();
    Course queryCourseByCno(String cNo);
    void deleteCourseByCno(String cNo);
    void updateCourseByCno(Course course);
    void addCourse(Course course);
    
    // 查询所有课程及其选课人数
    List<CourseWithStudentCount> queryAllCourseWithStudentCount();
    
    // 查询单个课程及其选课人数
    CourseWithStudentCount queryCourseWithStudentCountByCno(String cNo);
}
