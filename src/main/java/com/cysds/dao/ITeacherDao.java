package com.cysds.dao;

import com.cysds.dao.po.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 教师DAO接口
 */
@Mapper
public interface ITeacherDao {
    List<Teacher> queryAllTeacher();
    Teacher queryTeacherByName(String tName);
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherByName(String tName);
} 