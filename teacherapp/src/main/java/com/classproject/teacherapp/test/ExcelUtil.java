//package com.classproject.teacherapp.util;
//
//import com.lemon.utils.StringUtils;
//import com.suixingpay.lap.common.base.domain.BnkRetPo;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.CollectionUtils;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Excel工具类
// *
// * @author zhangkai
// */
//public class ExcelUtil {
//
//    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
//
//    /**
//     * 导出excel
//     *
//     * @param response
//     * @param datas
//     * @param fields
//     */
//    public static void exportExcel(HttpServletResponse response, List<Map<String, Object>> datas, String[] fields,
//            String[] columnName) {
//        XSSFWorkbook wb = crtExcel(fields);
//        if (datas != null) {
//            inserDateToExcel(wb, datas, columnName, fields.length);
//        }
//        downExcel(response, wb);
//    }
//
//    /**
//     * 创建excel
//     *
//     * @param fields
//     * @return
//     */
//    private static XSSFWorkbook crtExcel(String[] fields) {
//        XSSFWorkbook wb = new XSSFWorkbook();
//        XSSFCellStyle style = wb.createCellStyle();
//        style.setDataFormat(wb.createDataFormat().getFormat("@"));
//        XSSFSheet sheet = wb.createSheet("sheet");
//        XSSFRow rowt = sheet.createRow(0);
//        XSSFCell cell = null;
//        for (int i = 0; i < fields.length; i++) {
//            if (i == fields.length - 1) {
//                sheet.setColumnWidth(i, 7000);
//            } else {
//                sheet.setColumnWidth(i, 5000);
//            }
//            sheet.setDefaultColumnStyle(i, style);
//            cell = rowt.createCell(i);
//            XSSFCellStyle titleStyle = wb.createCellStyle();
////            titleStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
////            titleStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
////            titleStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
////            titleStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
//            cell.setCellStyle(titleStyle);
//            cell.setCellValue(fields[i]);
//        }
//        return wb;
//    }
//
//    /**
//     * 向excel插入数据
//     *
//     * @param wb
//     * @param datas
//     * @param fieldsLength
//     */
//    private static void inserDateToExcel(XSSFWorkbook wb, List<Map<String, Object>> datas, String[] colmnName,
//            int fieldsLength) {
//        XSSFSheet sheet = wb.getSheet("sheet");
//        XSSFCellStyle style = wb.getCellStyleAt((short) 0);
//        XSSFCell cell = null;
//        for (int i = 1; i <= datas.size(); i++) {
//            XSSFRow rowt2 = sheet.createRow(i);
//            Map<String, Object> bill = datas.get(i - 1);
//            for (int j = 0; j < fieldsLength; j++) {
//                cell = rowt2.createCell(j, XSSFCell.CELL_TYPE_STRING);
//                cell.setCellStyle(style);
//                if (bill.get(colmnName[j]) == null) {
//                    cell.setCellValue("");
//                } else {
//                    cell.setCellValue(bill.get(colmnName[j]) + "");
//                }
//
//            }
//        }
//    }
//
//    /**
//     * 下载excel
//     *
//     * @param response
//     * @param wb
//
//     */
//    private static void downExcel(HttpServletResponse response, XSSFWorkbook wb) {
//        response.setContentType("application/x-msdownload");
//        response.setHeader("Content-disposition", "attachment;filename=" + DateUtil.getCurDTTM() + ".xlsx");
//        response.setCharacterEncoding("utf-8");
//        OutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            wb.write(out);
//            out.flush();
//        } catch (IOException e) {
//            logger.error("异常", e);
//        } finally {
//            try {
//                if (null != out) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                logger.error("异常", e);
//            }
//        }
//    }
//
//    public static XSSFWorkbook checkUploadExcel(InputStream input, Map<String, Object> msg) {
//        XSSFWorkbook xwb = null;
//        try {
//            xwb = new XSSFWorkbook(input);
//        } catch (IOException e) {
//            logger.info("excel生成对象失败！");
//            msg.put("msg", "请导入07+版本EXCEL文件！");
//            return null;
//        }
//        // 获得该工作区的第一个sheet
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        //		if (sheet.getPhysicalNumberOfRows() != sheet.getLastRowNum() + 1) {
//        //			msg.put("msg", "excel文件里有空行！");
//        //			return null;
//        //		}
//        //卡BIN批量导入支持10000条数据（表头有3条）
//        String tSesBin = (String) msg.get("tSesBin");
//        //卡BIN批量删除支持10000条（表头1条）
//        String deleteType = (String) msg.get("deleteType");
//        if (sheet.getLastRowNum() > 1000 && !"1".equals(tSesBin)) {
//            msg.put("msg", "excel文件最多1000行！");
//            return null;
//        } else if (sheet.getLastRowNum() >= 10003 && "1".equals(tSesBin)) {
//            msg.put("msg", "excel文件最多10000行！");
//            return null;
//        } else if (sheet.getLastRowNum() >= 10001 && "1".equals(deleteType)) {
//            msg.put("msg", "excel文件最多10000行！");
//            return null;
//        }
//        return xwb;
//    }
//
//    public static XSSFWorkbook checkUploadExcel(InputStream input, Map<String, Object> msg, int number) {
//        XSSFWorkbook xwb = null;
//        try {
//            xwb = new XSSFWorkbook(input);
//        } catch (IOException e) {
//            logger.error("excel生成对象失败{}", e);
//            msg.put("msg", "请导入07+版本EXCEL文件！");
//            return null;
//        }
//        // 获得该工作区的第一个sheet
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        if (sheet.getLastRowNum() > number) {
//            msg.put("msg", "excel文件最多" + number + "行！");
//            return null;
//        }
//
//        return xwb;
//    }
//
//
//    //银行流水导入excel文件
//    public static XSSFWorkbook checkBankStmtCheckExcel(InputStream input, Map<String, Object> msg) {
//        XSSFWorkbook xwb = null;
//        try {
//            xwb = new XSSFWorkbook(input);
//        } catch (IOException e) {
//            logger.error("excel生成对象失败{}", e);
//            msg.put("msg", "请导入07+版本EXCEL文件！");
//            return null;
//        }
//        // 获得该工作区的第一个sheet
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        if (sheet.getLastRowNum() > 100000) {
//            msg.put("msg", "导入流水过多！");
//            return null;
//        }
//        return xwb;
//    }
//
//    /**
//     * @param xwb
//     * @param fields
//     * @param columnName
//     * @param sizeLimit
//     * @param msg
//     * @return
//     */
//
//    public static List<Map<String, Object>> readExcel(XSSFWorkbook xwb, String[] fields, String[] columnName,
//            int[] sizeLimit, Map<String, Object> msg) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        msg.put("success", "false");
//        logger.info("！！！------文件格式检查完毕，开始遍历数据表！");
//        XSSFSheet sheet = xwb.getSheetAt(0);// 获得该工作区的第一个sheet
//        XSSFRow row = null;
//        XSSFCell cell = null;
//        Map<String, Object> map = null;
//        //循环输出表格中的内容
//        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//            row = sheet.getRow(i);
//            //防止excel文件有空行
//            if (isBlankRow(row)) {
//                continue;
//            }
//            //获得表头内容，并检查
//            if (i == 0) {
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    cell = row.getCell(j);
//                    if (cell != null) {
//                        if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                            if (!cell.getStringCellValue().equals(fields[j])) {
//                                msg.put("msg", "excel文件表头信息不符！");
//                                return null;
//                            }
//                        } else {
//                            msg.put("msg", "第" + (i + 1) + "行第" + (j + 1) + "列单元格类型不是字符型！");
//                            return null;
//                        }
//                    } else {
//                        msg.put("msg", "excel文件表头信息缺少！");
//                        return null;
//                    }
//                }
//            } else {
//                map = new HashMap<String, Object>();
//                if (row.getPhysicalNumberOfCells() != row.getLastCellNum()) {
//                    msg.put("msg", "excel文件里第" + (i + 1) + "行有空列！");
//                    return null;
//                }
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    cell = row.getCell(j);
//                    if (cell == null) {
//                        msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列是空值！");
//                        return null;
//                    }
//                    // 获取单元格内容，
//                    if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                        String string = StringUtils.trim(cell.getStringCellValue());
//                        if (string.length() > sizeLimit[j]) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列字数超出范围！");
//                            return null;
//                        }
//                        if ("".equals(string.trim())) {
//                            break;
//                        }
//                        map.put(columnName[j], string);
//                    } else if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                        Double val = cell.getNumericCellValue();
//                        String string = (val != null) ? String.valueOf(val) : "";
//                        if ("".equals(string.trim())) {
//                            break;
//                        }
//                        if (string.length() > sizeLimit[j]) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列字数超出范围！");
//                            return null;
//                        }
//                        map.put(columnName[j], StringUtils.trim(string));
//                    } else {
//                        String string = cell.getStringCellValue();
//                        if ("".equals(string.trim())) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列是空值！");
//                            return null;
//                        }
//                        msg.put("msg", "第" + (i + 1) + "行第" + (j + 1) + "列单元格类型不是文本型！");
//                        return null;
//                    }
//                }
//                if (map != null && map.size() > 0 && !map.isEmpty()) {
//                    list.add(map);
//                }
//            }
//        }
//        logger.info("！！！------遍历成功，有" + list.size() + "条数据！");
//        if (CollectionUtils.isEmpty(list)) {
//            msg.put("msg", "导入excel文件无数据，请核对后再做操作！");
//            return null;
//        }
//        msg.put("success", "true");
//        return list;
//    }
//
//
//    //检查是否是空行，不是空行的话检查当前行是否有空列
//    public static boolean isBlankRow(XSSFRow row) {
//        if (row == null) {
//            return true;
//        }
//        boolean result = true;
//        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
//            XSSFCell cell = row.getCell(i, XSSFRow.RETURN_BLANK_AS_NULL);
//            String value = "";
//            if (cell != null) {
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        value = cell.getStringCellValue();
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        value = String.valueOf((int) cell.getNumericCellValue());
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:
//                        value = String.valueOf(cell.getBooleanCellValue());
//                        break;
//                    case Cell.CELL_TYPE_FORMULA:
//                        value = String.valueOf(cell.getCellFormula());
//                        break;
//                    //case Cell.CELL_TYPE_BLANK:
//                    //    break;
//                    default:
//                        break;
//                }
//
//                if (!value.trim().equals("")) {
//                    result = false;
//                    break;
//                }
//            }
//        }
//
//        return result;
//    }
//
//    public static boolean isNumeric(String str) {
//        Pattern pattern = Pattern.compile("[0-9]*");
//        Matcher isNum = pattern.matcher(str);
//        if (!isNum.matches()) {
//            return false;
//        }
//        return true;
//    }
//
//    public static List<Map<String, Object>> readExcelAllowBlank(XSSFWorkbook xwb, String[] fields, String[] columnName,
//            int[] sizeLimit, Map<String, Object> msg, List<BnkRetPo> errors, List<String> moneyNoList,
//            List<String> moneyNoIsNullList) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        msg.put("success", "false");
//        logger.info("！！！------文件格式检查完毕，开始遍历数据表！");
//        // 获得该工作区的第一个sheet
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        XSSFRow row = null;
//        XSSFCell cell = null;
//        BigDecimal dAmt = BigDecimal.ZERO;
//        Map<String, Object> map = null;
//        //循环输出表格中的内容
//        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//            row = sheet.getRow(i);
//            //防止excel文件有空行
//            if (isBlankRow(row)) {
//                continue;
//            }
//            //获得表头内容，并检查
//            if (i == 0) {
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    cell = row.getCell(j);
//                    if (cell != null) {
//                        if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                            if (!cell.getStringCellValue().equals(fields[j])) {
//                                msg.put("msg", "excel文件表头信息不符！");
//                                return null;
//                            }
//                        } else {
//                            msg.put("msg", "第" + (i + 1) + "行第" + (j + 1) + "列单元格类型不是字符型！");
//                            return null;
//                        }
//                    } else {
//                        msg.put("msg", "excel文件表头信息缺少！");
//                        return null;
//                    }
//                }
//            } else {
//                map = new HashMap<String, Object>();
//                String moneyNo = "";
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    cell = row.getCell(j);
//                    //判断财务科目号是否存在
//                    if (cell == null) {
//                        msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列是空值！");
//                        errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列是空值！"));
//                        msg.put("errors", errors);
//                        return null;
//                    }
//                    if (j == 0) {
//                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
//                        String date = StringUtils.trim(row.getCell(j).getStringCellValue());
//                        if (!isValidDate(date) || date.length() > 8) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "输入字符非法，[要求输入YYYYMMDD]");
//                            errors.add(new BnkRetPo(moneyNo,
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "输入字符非法，[要求输入YYYYMMDD]"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                    }
//                    if (j == 1) {
//                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
//                        moneyNo = StringUtils.trim(row.getCell(j).getStringCellValue());
//                        if (!moneyNoList.contains(moneyNo)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列的财务科目号不存在！");
//                            errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列的财务科目号不存在！"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        if (moneyNoIsNullList.contains(moneyNo)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列的财务号没有配置借贷关系,请配置后再导入!");
//                            errors.add(new BnkRetPo(moneyNo,
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列的财务号没有配置借贷关系,请配置后再导入!"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                    }
//                    if (j == 3 || j == 4) {
//                        Double val = Double.valueOf(0);
//                        BigDecimal bd1 = BigDecimal.valueOf(val);
//                        if (cell.getCellType() != XSSFCell.CELL_TYPE_NUMERIC) {
//                            String string = cell.getStringCellValue().trim();
//                            bd1 = new BigDecimal(string);
//                        } else {
//                            val = cell.getNumericCellValue();
//                            DecimalFormat df = new DecimalFormat("0.##");
//                            String valStr = df.format(val); //数字型
//                            bd1 = new BigDecimal(valStr);
//                        }
//                        if (j == 3) {
//                            dAmt = bd1;
//                        }
//                        if (j == 4 && (bd1.compareTo(BigDecimal.ZERO) == 0) && (dAmt.compareTo(BigDecimal.ZERO) == 0)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时为空");
//                            errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时为空"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        if (j == 4 && (bd1.compareTo(BigDecimal.ZERO) != 0) && (dAmt.compareTo(BigDecimal.ZERO) != 0)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时有数值");
//                            errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时有数值"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        String string = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
//                        String pattern = "^\\d{1,12}(\\.\\d{1,2})?$";
//                        if (string.length() > sizeLimit[j] || !Pattern.matches(pattern, string)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入值不在允许范围，[范围12位整数，2位小数]!");
//                            errors.add(new BnkRetPo(moneyNo,
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入值不在允许范围，[范围12位整数，2位小数]!"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                    }
//                    // 获取单元格内容，
//                    if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                        String string = cell.getStringCellValue().trim();
//                        if (string.length() > sizeLimit[j]) {
//                            msg.put("msg",
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列字数超出范围！最多可输入" + sizeLimit[j] + "个。");
//                            errors.add(new BnkRetPo(moneyNo,
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列字数超出范围！最多可输入！" + sizeLimit[j] + "个。"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        if ("".equals(string.trim())) {
//                            continue;
//                        }
//                        map.put(columnName[j], string.trim());
//                    } else if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                        Double val = cell.getNumericCellValue();
//                        BigDecimal bd1 = BigDecimal.valueOf(val);
//                        if (j == 3) {
//                            dAmt = bd1;
//                        }
//                        if (j == 4 && (bd1.compareTo(BigDecimal.ZERO) == 0) && (dAmt.compareTo(BigDecimal.ZERO) == 0)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时为空");
//                            errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时为空"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        if (j == 4 && (bd1.compareTo(BigDecimal.ZERO) != 0) && (dAmt.compareTo(BigDecimal.ZERO) != 0)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时有数值");
//                            errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行的借方发生额和贷方发生额不能同时有数值"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        String string = bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
//                        if ("".equals(string.trim())) {
//                            continue;
//                        }
//                        String pattern = "^\\d{1,12}(\\.\\d{1,2})?$";
//                        if (string.length() > sizeLimit[j] || !Pattern.matches(pattern, string)) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入值不在允许范围，[范围12位整数，2位小数]!");
//                            errors.add(new BnkRetPo(moneyNo,
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入值不在允许范围，[范围12位整数，2位小数]!"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        map.put(columnName[j], string.trim());
//                    } else if (XSSFCell.CELL_TYPE_BLANK == cell.getCellType() && j == 6) {
//                        continue;
//                    } else {
//                        String string = cell.getStringCellValue();
//                        if ("".equals(string.trim())) {
//                            msg.put("msg", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列是空值！");
//                            errors.add(new BnkRetPo(moneyNo, "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列是空值！"));
//                            msg.put("errors", errors);
//                            return null;
//                        }
//                        msg.put("msg", "第" + (i + 1) + "行第" + (j + 1) + "列单元格类型不是文本型！");
//                        return null;
//                    }
//                }
//                if (map != null && map.size() > 0 && !map.isEmpty()) {
//                    list.add(map);
//                }
//            }
//        }
//        logger.info("！！！------遍历成功，有" + list.size() + "条数据");
//        if (CollectionUtils.isEmpty(list)) {
//            msg.put("msg", "导入excel文件无数据，请核对后再做操作！");
//            return null;
//        }
//        msg.put("success", "true");
//        msg.put("successCount", sheet.getLastRowNum());
//        return list;
//    }
//
//
//
//    /**
//     * @todo 处理单元内容格式   暂时没有使用
//     */
///*	private static String parseExcel(Cell cell) {
//		String result = new String();
//		switch (cell.getCellType()) {
//		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
//			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
//				SimpleDateFormat sdf = null;
//				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
//					sdf = new SimpleDateFormat("HH:mm");
//				} else {// 日期
//					sdf = new SimpleDateFormat("yyyy-MM-dd");
//				}
//				Date date = cell.getDateCellValue();
//				result = sdf.format(date);
//			} else if (cell.getCellStyle().getDataFormat() == 58) {
//				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//				double value = cell.getNumericCellValue();
//				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
//				result = sdf.format(date);
//			} else {
//				double value = cell.getNumericCellValue();
//				CellStyle style = cell.getCellStyle();
//				DecimalFormat format = new DecimalFormat();
//				String temp = style.getDataFormatString();
//				// 单元格设置成常规
//				if (temp.equals("General")) {
//					format.applyPattern("#");
//				}
//				result = format.format(value).replace(",", "");
//
//				Double val=cell.getNumericCellValue();
//				result = (val!=null)?String.valueOf(val):"";
//			}
//			break;
//		case HSSFCell.CELL_TYPE_STRING:// String类型
//			result = cell.getRichStringCellValue().toString();
//			break;
//		case HSSFCell.CELL_TYPE_BLANK:
//			result = "";
//		default:
//			result = "";
//			break;
//		}
//		return result;
//	}*/
//
//
//    /**
//     * Description: 自动即时T+1结算批量导入
//     *
//     * @throws
//     * @author zhou_jn 2016-02-23
//     */
//    public static List<Map<String, Object>> readAutoImmeT1SesExcel(XSSFWorkbook xwb, String[] fields,
//            String[] columnName, int[] sizeLimit, Map<String, Object> msg) {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        msg.put("success", "false");
//        logger.info("！！！------文件格式检查完毕，开始遍历数据表！");
//        // 获得该工作区的第一个sheet
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        XSSFRow row = null;
//        XSSFCell cell = null;
//        Map<String, Object> map = null;
//        //循环输出表格中的内容
//        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//            row = sheet.getRow(i);
//            //防止excel文件有空行
//            if (isBlankRow(row)) {
//                continue;
//            }
//            //获得表头内容，并检查
//            if (i == 0) {
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    cell = row.getCell(j);
//                    if (cell != null) {
//                        if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                            if (!cell.getStringCellValue().equals(fields[j])) {
//                                msg.put("msg", "excel文件表头信息不符！");
//                                return null;
//                            }
//                        } else {
//                            msg.put("msg", "第" + (i + 1) + "行第" + (j + 1) + "列单元格类型不是字符型！");
//                            return null;
//                        }
//                    } else {
//                        msg.put("msg", "excel文件表头信息缺少！");
//                        return null;
//                    }
//                }
//            } else {
//                map = new HashMap<String, Object>();
//                map.put("rowNum", row.getRowNum() + 1);
//                cell = row.getCell(0);
//                if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                    String string = cell.getStringCellValue();
//                    map.put(columnName[0], string.trim());
//                } else {
//                    msg.put("msg", "第" + (i + 1) + "行数据格式不正确！");
//                    return null;
//                }
//                if (map != null && map.size() > 0 && !map.isEmpty()) {
//                    list.add(map);
//                }
//            }
//        }
//        logger.info("！！！------遍历成功，有" + list.size() + "条数据！");
//        if (CollectionUtils.isEmpty(list)) {
//            msg.put("msg", "导入excel文件无数据，请核对后再做操作！");
//            return null;
//        }
//        msg.put("success", "true");
//        return list;
//    }
//
//    public static boolean isValidDate(String str) {
//        boolean convertSuccess = true;
//        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        try {
//            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
//            format.setLenient(false);
//            format.parse(str);
//        } catch (Exception e) {
//            // LOGGER.error("error", e);
//            logger.error("异常", e);
//            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
//            convertSuccess = false;
//        }
//        return convertSuccess;
//    }
//
//    public static boolean isNum(String str) {
//        Pattern pattern = Pattern.compile("^-?[0-9]+");
//
//        return pattern.matcher(str).matches();
//
//    }
//
//
//
//    /**
//     * @param xwb
//     * @param fields
//     * @param columnName
//     * @param sizeLimit
//     * @param infos
//     * @param msg
//     * @param errors
//     * @param list
//     * @description:一览表导入专用读取Excel方法
//     * @return:List<Map<String,Object>>
//     * @author:wen_jf@suixingpay.com
//     * @date:2017年5月16日
//     */
//    public static List<Map<String, Object>> readExcelForSchedule(XSSFWorkbook xwb, String[] fields, String[] columnName,
//            int[] sizeLimit, List<Map<String, Object>> infos, Map<String, Object> msg, List<BnkRetPo> errors,
//            List<Map<String, Object>> list) {
//        //List<String> cellNumlist=Arrays.asList(mustIncellNum);
//        msg.put("success", "false");
//        logger.info("#批量导入操作一览表信息#文件格式检查完毕，开始遍历数据表！");
//        XSSFSheet sheet = xwb.getSheetAt(0);// 获得该工作区的第一个sheet
//        XSSFRow row = null;
//        XSSFCell cell = null;
//        Map<String, Object> map = null;
//        Set<String> onlyKeySet = new HashSet<String>();//创建一个队列，存放交易日期+机构唯一性
//        //String templateType = (String)msg.get("templateType");//导入模板类型
//        //循环输出表格中的内容
//        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//            row = sheet.getRow(i);
//            //防止excel文件有空行
//            if (isBlankRow(row)) {
//                continue;
//            }
//            //获得表头内容，并检查
//            if (i == 0) {
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    cell = row.getCell(j);
//                    if (cell != null) {
//                        if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                            if (!cell.getStringCellValue().trim().equals(fields[j])) {
//                                msg.put("msg", "第" + (j + 1) + "列excel文件表头信息不符！");
//                                return null;
//                            }
//                        } else {
//                            msg.put("msg", "第" + (i + 1) + "行第" + (j + 1) + "列单元格类型不是字符型！");
//                            return null;
//                        }
//                    } else {
//                        msg.put("msg", "excel文件表头信息缺少！");
//                        return null;
//                    }
//                }
//            } else {
//                map = new HashMap<String, Object>();
//                Map<String, Object> info = null;
//                for (int j = 0; j < sizeLimit.length; j++) {
//                    String corgNm = "";
//                    String tranDt = "";
//                    if (row.getCell(0) != null && XSSFCell.CELL_TYPE_STRING == row.getCell(0).getCellType()) {
//                        tranDt = row.getCell(0).getStringCellValue();
//                    } else if (row.getCell(0) != null && XSSFCell.CELL_TYPE_NUMERIC == row.getCell(0).getCellType()) {
//                        Double val = row.getCell(0).getNumericCellValue();
//                        DecimalFormat myformat = new DecimalFormat("0");
//                        tranDt = (val != null) ? myformat.format(val) : "";
//                    }
//                    if (row.getCell(1) != null && XSSFCell.CELL_TYPE_STRING == row.getCell(1).getCellType()) {
//                        corgNm = row.getCell(1).getStringCellValue();
//                    } else if (row.getCell(1) != null && XSSFCell.CELL_TYPE_NUMERIC == row.getCell(1).getCellType()) {
//                        Double val = row.getCell(1).getNumericCellValue();
//                        DecimalFormat myformat = new DecimalFormat("0");
//                        corgNm = (val != null) ? myformat.format(val) : "";
//                    }
//                    String onlyKey = corgNm.trim() + tranDt.trim();
//                    cell = row.getCell(j);
//                    info = new HashMap<String, Object>();
//                    if (cell == null) {
//                        info.put("cdeCd", onlyKey);
//                        info.put("failReason", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列缺少必填项！");
//                        errors.add(new BnkRetPo(onlyKey, "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列缺少必填项！"));
//                        break;
//                    }
//                    // 获取单元格内容
//                    String string = "";
//                    if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                        string = StringUtils.trim(cell.getStringCellValue());
//                    } else if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                        Double val = cell.getNumericCellValue();
//                        DecimalFormat myformat = null;
//                        if (j == 0 || j == 1) {
//                            myformat = new DecimalFormat("0");
//                        } else {
//                            myformat = new DecimalFormat("0.00");
//                        }
//
//                        string = (val != null) ? myformat.format(val) : "";
//                    } else {
//                        string = StringUtils.trim(cell.getStringCellValue());
//                    }
//                    //去空格非空校验
//                    if ("".equals(string.trim())) {
//                        info.put("cdeCd", onlyKey);
//                        info.put("failReason", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列必填项未输入！");
//                        errors.add(new BnkRetPo(onlyKey, "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列必填项未输入！"));
//                        break;
//                    }
//                    //校验交易时间和结算时间格式是否符合要求
//                    if ((j == 0) && (!isValidDate(string) || !isNum(string))) {
//                        info.put("cdeCd", onlyKey);
//                        info.put("failReason", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入时间格式不正确");
//                        errors.add(new BnkRetPo(onlyKey,
//                                "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入时间格式不正确；[要求输入YYYYMMDD时间格式]"));
//                        break;
//                    }
//                    //数值校验全量导入
//                    if (j != 0 && j != 1) {
//                        if (!isAmt(string)) {
//                            info.put("cdeCd", onlyKey);
//                            info.put("failReason", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入字符非法[要求输入数值型字符]！");
//                            errors.add(new BnkRetPo(onlyKey,
//                                    "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入字符非法[要求输入数值型字符]！"));
//                            break;
//                        }
//                    }
//                    //长度校验
//                    if (string.length() > sizeLimit[j]) {
//                        info.put("cdeCd", onlyKey);
//                        info.put("failReason",
//                                "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入值不在允许范围内,范围[" + sizeLimit[j] + "]");
//                        errors.add(new BnkRetPo(onlyKey,
//                                "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列输入值不在允许范围内,范围[" + sizeLimit[j] + "]"));
//                        break;
//                    }
//                    //检查导入文件的唯一性
//                    if (j == 1) {
//                        if (onlyKeySet.contains(onlyKey.toLowerCase())) {
//                            info.put("cdeCd", onlyKey);
//                            info.put("failReason", "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列交易日期+机构有重复！");
//                            errors.add(new BnkRetPo(onlyKey, "excel文件里第" + (i + 1) + "行第" + (j + 1) + "列交易日期+机构有重复！"));
//                            break;
//                        } else {
//                            onlyKeySet.add(onlyKey.toLowerCase());
//                        }
//                    }
//
//                    map.put(columnName[j], string.trim());
//                }
//                if (map != null && map.size() > 0 && !map.isEmpty() && info.isEmpty()) {
//                    list.add(map);
//                }
//                if (info != null && info.size() > 0 && !info.isEmpty()) {
//                    infos.add(info);
//                }
//            }
//        }
//        logger.info("#批量导入操作卡BIN信息#遍历成功，有" + list.size() + "条数据！");
//        msg.put("success", "true");
//        return list;
//
//    }
//
//    public static boolean isAmt(String str) {
//        Pattern pattern = Pattern.compile("^([1-9][\\d]{0,11}|0)(\\.[\\d]{1,2})?$");
//        return pattern.matcher(str).matches();
//    }
//
//}
