package com.classproject.teacherapp.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Api("注册信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserVo {

    /**
     * 用户名
     */
    @NotBlank
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @NotBlank
    @ApiModelProperty("密码")
    private String password;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String brithday;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 学校
     */
    @ApiModelProperty("学校")
    private String school;

    /**
     * 学院
     */
    @ApiModelProperty("学院")
    private String college;

    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private String education;

    /**
     * 年级
     */
    @ApiModelProperty("年级")
    private String grade;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String head;
    /**
     * 是否是学生1-学生 0-老师
     */
    @ApiModelProperty("是否是学生1-学生 0-老师")
    private String teacher;
}
