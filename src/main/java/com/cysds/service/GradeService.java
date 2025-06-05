package com.cysds.service;

import com.cysds.dao.ICourseDao;
import com.cysds.dao.ISCDao;
import com.cysds.dao.IStudentDao;
import com.cysds.dao.po.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 成绩统计服务
 */
@Service
public class GradeService {

    @Resource
    private ISCDao scDao;

    @Resource
    private IStudentDao studentDao;

    @Resource
    private ICourseDao courseDao;

    /**
     * 计算所有学生的加权平均成绩并按降序排序
     * @return 包含平均成绩的学生列表
     */
    public List<StudentWithAvgGrade> calculateStudentsAvgGrade() {
        // 获取所有学生
        List<Student> students = studentDao.queryAllStudent();
        // 获取所有课程
        List<Course> courses = courseDao.queryAllCourse();
        // 创建课程ID到学分的映射
        Map<String, Integer> courseCreditsMap = courses.stream()
                .collect(Collectors.toMap(Course::getCno, Course::getCcredit));
        
        List<StudentWithAvgGrade> result = new ArrayList<>();
        
        for (Student student : students) {
            // 获取学生的所有选课记录
            List<SC> studentCourses = scDao.querySCBySno(student.getSno());
            
            // 过滤掉未修读完毕的课程（成绩为0）
            List<SC> completedCourses = studentCourses.stream()
                    .filter(sc -> sc.getGrade() > 0)
                    .collect(Collectors.toList());
            
            // 如果没有完成的课程，设置平均分为null
            if (completedCourses.isEmpty()) {
                result.add(StudentWithAvgGrade.builder()
                        .Sno(student.getSno())
                        .Sname(student.getSname())
                        .Sgender(student.getSgender())
                        .Sage(student.getSage())
                        .Sdept(student.getSdept())
                        .avgGrade(null)
                        .build());
                continue;
            }
            
            // 计算总学分
            int totalCredits = completedCourses.stream()
                    .mapToInt(sc -> courseCreditsMap.getOrDefault(sc.getCno(), 0))
                    .sum();
            
            if (totalCredits == 0) {
                result.add(StudentWithAvgGrade.builder()
                        .Sno(student.getSno())
                        .Sname(student.getSname())
                        .Sgender(student.getSgender())
                        .Sage(student.getSage())
                        .Sdept(student.getSdept())
                        .avgGrade(null)
                        .build());
                continue;
            }
            
            // 计算加权总分
            double weightedTotalGrade = 0;
            for (SC sc : completedCourses) {
                int credit = courseCreditsMap.getOrDefault(sc.getCno(), 0);
                double weight = (double) credit / totalCredits;
                weightedTotalGrade += sc.getGrade() * weight;
            }
            
            // 保留两位小数
            BigDecimal bd = new BigDecimal(weightedTotalGrade).setScale(2, RoundingMode.HALF_UP);
            double avgGrade = bd.doubleValue();
            
            result.add(StudentWithAvgGrade.builder()
                    .Sno(student.getSno())
                    .Sname(student.getSname())
                    .Sgender(student.getSgender())
                    .Sage(student.getSage())
                    .Sdept(student.getSdept())
                    .avgGrade(avgGrade)
                    .build());
        }
        
        // 按平均成绩降序排序（将null值排在最后）
        result.sort((s1, s2) -> {
            if (s1.getAvgGrade() == null && s2.getAvgGrade() == null) {
                return 0;
            } else if (s1.getAvgGrade() == null) {
                return 1;
            } else if (s2.getAvgGrade() == null) {
                return -1;
            } else {
                return Double.compare(s2.getAvgGrade(), s1.getAvgGrade());
            }
        });
        
        return result;
    }
    
    /**
     * 计算所有课程的平均成绩
     * @return 包含平均成绩的课程列表
     */
    public List<CourseWithAvgGrade> calculateCoursesAvgGrade() {
        // 获取所有课程
        List<Course> courses = courseDao.queryAllCourse();
        List<CourseWithAvgGrade> result = new ArrayList<>();
        
        for (Course course : courses) {
            // 获取选修该课程的所有记录
            List<SC> courseRecords = scDao.querySCByCno(course.getCno());
            
            // 过滤掉未修读完毕的记录（成绩为0）
            List<SC> completedRecords = courseRecords.stream()
                    .filter(sc -> sc.getGrade() > 0)
                    .collect(Collectors.toList());
            
            Double avgGrade = null;
            if (!completedRecords.isEmpty()) {
                // 计算平均成绩
                double totalGrade = completedRecords.stream()
                        .mapToInt(SC::getGrade)
                        .sum();
                
                // 保留两位小数
                BigDecimal bd = new BigDecimal(totalGrade / completedRecords.size()).setScale(2, RoundingMode.HALF_UP);
                avgGrade = bd.doubleValue();
            }
            
            result.add(CourseWithAvgGrade.builder()
                    .Cno(course.getCno())
                    .Cname(course.getCname())
                    .Cpno(course.getCpno())
                    .Ccredit(course.getCcredit())
                    .avgGrade(avgGrade)
                    .build());
        }
        
        return result;
    }
    
    /**
     * 获取选修指定课程的学生成绩列表（按成绩降序排序）
     * @param cno 课程号
     * @return 学生成绩列表
     */
    public List<StudentCourseGrade> getStudentsByCourseOrderByGrade(String cno) {
        // 获取选修该课程的所有记录
        List<SC> courseRecords = scDao.querySCByCno(cno);
        // 获取所有学生
        List<Student> allStudents = studentDao.queryAllStudent();
        // 创建学生ID到姓名的映射
        Map<String, String> studentNameMap = allStudents.stream()
                .collect(Collectors.toMap(Student::getSno, Student::getSname));
        
        List<StudentCourseGrade> result = new ArrayList<>();
        
        for (SC sc : courseRecords) {
            result.add(StudentCourseGrade.builder()
                    .Sno(sc.getSno())
                    .Sname(studentNameMap.getOrDefault(sc.getSno(), "未知学生"))
                    .Grade(sc.getGrade())
                    .build());
        }
        
        // 按成绩降序排序
        result.sort((s1, s2) -> Integer.compare(s2.getGrade(), s1.getGrade()));
        
        return result;
    }
} 