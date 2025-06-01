package com.cysds.controller;

import com.cysds.dao.IStudentDao;
import com.cysds.dao.po.Student;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 谢玮杰
 * @description:
 * @create: 2025-05-17 16:36
 **/
@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {

    @Resource
    private IStudentDao studentDao;

    @GetMapping
    public List<Student> getAll() {
        return studentDao.queryAllStudent();
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentDao.addStudent(student);
        return "OK";
    }

    @DeleteMapping("/{sno}")
    public String delete(@PathVariable String sno) {
        studentDao.deleteStudentBySno(sno);
        return "Deleted";
    }

    @PutMapping("/{sno}")
    public String updateStudent(@PathVariable String sno, @RequestBody Student student) {
        studentDao.updateStudentBySno(student);
        return "Updated";
    }
}
