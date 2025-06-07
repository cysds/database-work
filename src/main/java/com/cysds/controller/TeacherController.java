package com.cysds.controller;

import com.cysds.dao.ITeacherDao;
import com.cysds.dao.po.Teacher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
public class TeacherController {

    @Resource
    private ITeacherDao teacherDao;

    @GetMapping
    public List<Teacher> getAll() {
        return teacherDao.queryAllTeacher();
    }

    @GetMapping("/{tName}")
    public Teacher getByName(@PathVariable String tName) {
        return teacherDao.queryTeacherByName(tName);
    }

    @PostMapping
    public String add(@RequestBody Teacher teacher) {
        System.out.println("接收到的教师数据: " + teacher);
        teacherDao.addTeacher(teacher);
        return "OK";
    }

    @PutMapping("/{tName}")
    public String update(@PathVariable String tName, @RequestBody Teacher teacher) {
        teacherDao.updateTeacher(teacher);
        return "Updated";
    }

    @DeleteMapping("/{tName}")
    public String delete(@PathVariable String tName) {
        teacherDao.deleteTeacherByName(tName);
        return "Deleted";
    }
} 