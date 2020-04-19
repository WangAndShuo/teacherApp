package com.classproject.teacherapp.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-22
 */
@Data
//@ContentRowHeight      //内容的行高
//@HeadRowHeight         //表头的行高
public class AppUser extends BaseRowModel {
    /**
     * uuid
     */
    @ExcelProperty(value = "主键", index = 0)
    @ColumnWidth(20)
    @NotBlank
    private String uuid;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名", index = 1)
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码", index = 2)
    @ColumnWidth(40)
    @NotBlank
    private String password;


}