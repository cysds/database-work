package com.cysds.dao;

/**
 * @author: 谢玮杰
 * @description:
 * @create: 2025-05-17 15:22
 **/

import com.cysds.dao.po.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudentDao {

    List<Student> queryAllStudent();

    Student queryStudentBySno(String sNo);

    void deleteStudentBySno(String sNo);

    void updateStudentBySno(Student student);

    void addStudent(Student student);
}
