package com.cysds.controller;

import com.cysds.dao.po.CourseWithAvgGrade;
import com.cysds.dao.po.StudentCourseGrade;
import com.cysds.dao.po.StudentWithAvgGrade;
import com.cysds.service.GradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 成绩统计控制器
 */
@RestController
@RequestMapping("/api/grade")
@CrossOrigin
public class GradeController {

    @Resource
    private GradeService gradeService;

    /**
     * 获取所有学生的平均成绩（按降序排序）
     */
    @GetMapping("/students")
    public List<StudentWithAvgGrade> getStudentsAvgGrade() {
        return gradeService.calculateStudentsAvgGrade();
    }

    /**
     * 获取所有课程的平均成绩
     */
    @GetMapping("/courses")
    public List<CourseWithAvgGrade> getCoursesAvgGrade() {
        return gradeService.calculateCoursesAvgGrade();
    }

    /**
     * 获取指定课程的学生成绩列表（按成绩降序排序）
     */
    @GetMapping("/course/{cno}/students")
    public List<StudentCourseGrade> getStudentsByCourseOrderByGrade(@PathVariable String cno) {
        return gradeService.getStudentsByCourseOrderByGrade(cno);
    }
} 