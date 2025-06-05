package com.cysds.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生选课成绩详情DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseGrade {
    // 学生信息
    private String Sno;
    private String Sname;
    
    // 成绩信息
    private int Grade;
} 