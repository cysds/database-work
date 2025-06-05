package com.cysds.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包含平均成绩的课程信息DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithAvgGrade {
    // 课程基本信息
    private String Cno;
    private String Cname;
    private String Cpno;
    private int Ccredit;
    
    // 平均成绩
    private Double avgGrade;
} 