package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.util.ExcelBody;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

/**
 * @ClassName Usern
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/2/24 17:58
 **/
@Data
public class User implements ExcelBody {
    private  String name ;

    @Override
    public void setExcel(Row row) {
        row.createCell(0).setCellValue(this.getName());
    }
}
