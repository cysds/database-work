package com.cysds.controller;

import com.cysds.dao.ISCDao;
import com.cysds.dao.ITeacherDao;
import com.cysds.dao.po.SC;
import com.cysds.dao.po.Teacher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sc")
@CrossOrigin
public class SCController {

    @Resource
    private ISCDao scDao;
    
    @Resource
    private ITeacherDao teacherDao;

    @GetMapping
    public List<SC> getAll() {
        return scDao.queryAllSC();
    }

    @GetMapping("/student/{sno}")
    public List<SC> getBySno(@PathVariable String sno) {
        return scDao.querySCBySno(sno);
    }

    @GetMapping("/course/{cno}")
    public List<SC> getByCno(@PathVariable String cno) {
        return scDao.querySCByCno(cno);
    }
    
    @GetMapping("/teacher/{tName}")
    public List<Map<String, Object>> getByTeacher(@PathVariable String tName) {
        // 首先获取教师教授的课程
        Teacher teacher = teacherDao.queryTeacherByName(tName);
        if (teacher != null && teacher.getTCourse() != null) {
            // 然后获取选修该课程的所有学生
            return scDao.queryStudentsByCourse(teacher.getTCourse());
        }
        return new ArrayList<>();
    }
    
    @GetMapping("/search")
    public List<Map<String, Object>> searchByCourseNameAndMinGrade(
            @RequestParam("courseName") String courseName,
            @RequestParam("minGrade") Integer minGrade) {
        return scDao.querySCByCourseNameAndMinGrade(courseName, minGrade);
    }

    @PostMapping
    public String add(@RequestBody SC sc) {
        scDao.addSC(sc);
        return "OK";
    }

    @DeleteMapping("/{sno}/{cno}")
    public String delete(@PathVariable String sno, @PathVariable String cno) {
        scDao.deleteSC(sno, cno);
        return "Deleted";
    }

    @DeleteMapping("/student/{sno}")
    public String deleteBySno(@PathVariable String sno) {
        scDao.deleteSCBySno(sno);
        return "Deleted";
    }

    @DeleteMapping("/course/{cno}")
    public String deleteByCno(@PathVariable String cno) {
        scDao.deleteSCByCno(cno);
        return "Deleted";
    }

    @PutMapping
    public String update(@RequestBody SC sc) {
        scDao.updateSC(sc);
        return "Updated";
    }
} 