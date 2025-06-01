package com.cysds.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 谢玮杰
 * @description:
 * @create: 2025-05-17 15:24
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private String Cno;

    private String Cname;

    private String Cpno;

    private int Ccredit;
}
