package com.cysds.dao;

import com.cysds.dao.po.Course;
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
}
