package com.cysds.dao.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师实体类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @JsonProperty("tName")
    private String tName;   // 教师姓名
    
    @JsonProperty("tCourse")
    private String tCourse; // 讲授课程
} 