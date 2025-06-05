package com.cysds.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包含平均成绩的学生信息DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithAvgGrade {
    // 学生基本信息
    private String Sno;
    private String Sname;
    private String Sgender;
    private int Sage;
    private String Sdept;
    
    // 平均成绩
    private Double avgGrade;
} 