package com.classproject.teacherapp.util;


import org.apache.poi.ss.usermodel.Row;

/**
 * @ClassName ExcelBody
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/20 14:33
 **/
public interface ExcelBody {
    /**
     * 向表格中插入数据并对数据进行处理
     *
     * @param row
     */
    void setExcel(Row row);

}