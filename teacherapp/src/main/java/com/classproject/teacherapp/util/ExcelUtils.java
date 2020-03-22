package com.classproject.teacherapp.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ExeclUtil
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/20 14:25
 **/

@Slf4j
public class ExcelUtils {

    /**
     * 入参 文件流 和 第size行数据为表头不解析 返回List<List<String>>
     *
     * @param inputStream
     * @param size
     * @return
     */
    public static List<List<String>> readExcel(InputStream inputStream, int size) {
        List<List<String>> list = new ArrayList<>();
        try {
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            int i = 0;
            for (Object object : data) {
                @SuppressWarnings("unchecked")
                List<String> object1 = (List<String>) object;
                // 每列的第一列数据不能为空，前两行为表头
                if (null == object1 || StringUtils.isEmpty(object1.get(0)) || i < size) {
                    i = i + 1;
                } else {
                    list.add(object1);
                }
            }
        } catch (Exception e) {
            log.info("解析excel失败{}", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.info("解析批量进件excel失败{}", e);
            }
        }
        return list;
    }


    /**
     * 导出 xlsx
     *
     * @param list     需要导出的list
     * @param cellText 标题头
     */
    public static void exportXlsx(List<ExcelBody> list, String[] cellText, HttpServletResponse response, String fileName, int[] columnWidth) {
        if (list == null || list.isEmpty()) {
            log.warn("导出数据为空！");
            return;
        }
        log.info("导出总数:" + list.size());
        // 导出表格 xlsx格式
        XSSFWorkbook workbook = dealExcelXlSX(list, cellText, columnWidth);
        exportExcel(response, workbook, fileName);
    }

    /**
     * 处理xlsx格式文件
     *
     * @Author: zhangyu
     * @Date: 2019-11-29 16:41
     * @Param: list 导出数据
     * @Param: cellText 标题头
     * @Param: columnWidth 列宽
     */
    private static XSSFWorkbook dealExcelXlSX(List<ExcelBody> list, String[] cellText, int[] columnWidth) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0); // 创建首行
        // 设置首行标题头
        for (int i = 0; i < cellText.length; i++) {
            XSSFCell cell = row.createCell(i);

            cell.setCellValue(new XSSFRichTextString(cellText[i]));
            cellStyle(cell, workbook);
        }
        // 封装数据
        if (list != null && !list.isEmpty()) {
            for (int rownum = 0; rownum < list.size(); rownum++) {
                Row r = sheet.createRow(rownum + 1);
                ExcelBody excelBody = list.get(rownum);
                excelBody.setExcel(r);
            }
        }
        sheet.createFreezePane(0, 1, 0, 1); // 冻结首行
        // 设置列宽
        for (int columnNum = 0; columnNum < columnWidth.length; columnNum++) {
            sheet.setColumnWidth(columnNum, columnWidth[columnNum] * 256);
        }
        return workbook;
    }

    /**
     * 导出Excel
     *
     * @Author: zhangyu
     * @Date: 2019-11-29 16:42
     * @Param: response
     * @Param: workbook
     * @Param: fileName
     */
    public static void exportExcel(HttpServletResponse response, XSSFWorkbook workbook, String fileName) {
        response.reset();
        response.setHeader("Content-Disposition",
                "attachment; filename=" + fileName + ".xlsx");
        response.setContentType("application/msexcel; charset=utf-8");
        try {
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
            log.info("xlsx导出成功！");
        } catch (IOException e) {
            log.error("xlsx导出失败！", e);
        }
    }

    /**
     * 设置单元格格式
     *
     * @Author: zhangyu
     * @Date: 2019-11-29 16:42
     * @Param: cell
     * @Param: wb
     */
    private static void cellStyle(XSSFCell cell, XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();

        // 设置单元格字体
        XSSFFont font = wb.createFont();
        font.setFontName("宋体"); // 设置字体名称
        font.setFontHeight((short) 200); // 设置字体高度
        font.setFontHeightInPoints((short) 16); // 设置字体大小
        font.setBold(true); // 字体加粗
        cellStyle.setFont(font);

        // 边框
        cellStyle.setBorderBottom(BorderStyle.MEDIUM); // 下边框
        cellStyle.setBorderLeft(BorderStyle.MEDIUM); // 左边框
        cellStyle.setBorderTop(BorderStyle.MEDIUM); // 上边框
        cellStyle.setBorderRight(BorderStyle.MEDIUM); // 右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中

        cell.setCellStyle(cellStyle);
    }
}
