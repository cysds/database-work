package com.cysds.controller;

import com.cysds.dao.ICourseDao;
import com.cysds.dao.po.Course;
import com.cysds.dao.po.CourseWithStudentCount;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {

    @Resource
    private ICourseDao courseDao;

    @GetMapping
    public List<Course> getAll() {
        return courseDao.queryAllCourse();
    }

    @GetMapping("/{cno}")
    public Course getByCno(@PathVariable String cno) {
        return courseDao.queryCourseByCno(cno);
    }

    @GetMapping("/with-student-count")
    public List<CourseWithStudentCount> getAllWithStudentCount() {
        return courseDao.queryAllCourseWithStudentCount();
    }

    @GetMapping("/with-student-count/{cno}")
    public CourseWithStudentCount getWithStudentCountByCno(@PathVariable String cno) {
        return courseDao.queryCourseWithStudentCountByCno(cno);
    }

    @PostMapping
    public String add(@RequestBody Course course) {
        courseDao.addCourse(course);
        return "OK";
    }

    @DeleteMapping("/{cno}")
    public String delete(@PathVariable String cno) {
        courseDao.deleteCourseByCno(cno);
        return "Deleted";
    }

    @PutMapping("/{cno}")
    public String update(@PathVariable String cno, @RequestBody Course course) {
        courseDao.updateCourseByCno(course);
        return "Updated";
    }
} 