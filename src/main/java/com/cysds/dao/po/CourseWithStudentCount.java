package com.cysds.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包含选课人数的课程信息DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithStudentCount {
    // 课程基本信息
    private String Cno;
    private String Cname;
    private String Cpno;
    private int Ccredit;
    
    // 选课人数
    private int studentCount;
} 