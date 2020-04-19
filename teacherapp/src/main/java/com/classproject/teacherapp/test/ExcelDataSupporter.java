//package com.classproject.teacherapp.util;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFFooter;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.Footer;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.OutputStream;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 数据导出工具类
// * <p/>
// *
// * @author WangLei
// * @version 2014年10月28日10:43:05
// */
//public final class ExcelDataSupporter {
//
//    public static final String KEY_ERROR_MESSAGE = "error";
//    // 启用log4j日志
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDataSupporter.class);
//
//    private ExcelDataSupporter() {
//    }
//
//    /**
//     * 输出excel
//     * <p/>
//     *
//     * @param outputStream   在输出后会被关闭
//     * @param sheetName      sheet页名
//     * @param excelColumnMap 对照
//     * @param items          数据
//     * @author WangLei
//     */
//    public static void createExcel(OutputStream outputStream, String sheetName, Map<String, ExcelColumn> excelColumnMap,
//            List<Map<String, Object>> items) {
//        if (null == outputStream) {
//            return;
//        }
//        Set<String> columnKeySet = excelColumnMap.keySet();
//        int columnSize = excelColumnMap.size();
//        // 获取表头数据
//        String[] headers = new String[columnSize];
//        Map<String, Object> titleMap = Maps.newLinkedHashMap();
//        int[] columnTypes = new int[columnKeySet.size()];
//        int index = 0;
//        for (String key : columnKeySet) {
//            ExcelColumn excelColumn = excelColumnMap.get(key);
//            headers[index] = StringUtils.defaultIfEmpty(excelColumn.getCode(), key);
//            titleMap.put(key, excelColumn.getTitle());
//            String columnType = excelColumn.getType();
//            columnType = null == columnType ? "" : columnType.toUpperCase();
//            if (columnType.contains("NUMBER") || columnType.contains("DOUBLE") || columnType.contains("FLOAT")
//                    || columnType.contains("INT") || columnType.contains("BIT")) {
//                columnTypes[index] = XSSFCell.CELL_TYPE_NUMERIC;
//            } else if (columnType.contains("DATE") || columnType.contains("TIME")) {
//                columnTypes[index] = XSSFCell.CELL_TYPE_STRING;
//            } else {
//                columnTypes[index] = XSSFCell.CELL_TYPE_STRING;
//            }
//            index++;
//        }
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet(sheetName);
//            sheet.setAutobreaks(true);
//            Footer footer = sheet.getFooter();
//            footer.setRight("@Page " + HSSFFooter.page() + " of " + HSSFFooter.numPages());
//            sheet.createFreezePane(0, 1, 0, 1);
//            List<Map<String, Object>> outPutData = Lists.newArrayList();
//            outPutData.add(titleMap);
//            outPutData.addAll(items);
//            int rowSize = outPutData.size();
//            XSSFCellStyle defaultDataStyle = workbook.createCellStyle();
//            defaultDataStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//            defaultDataStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
//            defaultDataStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//            defaultDataStyle.setBottomBorderColor(HSSFColor.BLACK.index);
//            defaultDataStyle.setLeftBorderColor(HSSFColor.BLACK.index);
//            defaultDataStyle.setBorderLeft((short) 1);
//            defaultDataStyle.setRightBorderColor(HSSFColor.BLACK.index);
//            defaultDataStyle.setBorderRight((short) 1);
//            defaultDataStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
//            XSSFFont defaultDataFont = workbook.createFont();
//            defaultDataFont.setFontName("宋体");
//            defaultDataFont.setFontHeightInPoints((short) 10);
//            defaultDataFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//            defaultDataStyle.setFont(defaultDataFont);
//            for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
//                Map<String, Object> result = outPutData.get(rowIndex);
//                XSSFRow row = sheet.createRow(rowIndex);
//                for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
//                    Object valueObject = result.get(headers[columnIndex]);
//                    if (null != valueObject) {
//                        String value = String.valueOf(valueObject);
//                        if (!StringUtils.isBlank(value)) {
//                            XSSFCell cell = row.createCell(columnIndex);
//                            cell.setCellType(columnTypes[columnIndex]);
//                            if (rowIndex == 0) {
//                                cell.setCellStyle(defaultDataStyle);
//                            }
//                            cell.setCellValue(value);
//                        }
//                    }
//                }
//            }
//            for (int i = 0; i < columnSize; i++) {
//                Integer maxLength = excelColumnMap.get(headers[i]).getWidth();
//                maxLength = null == maxLength ? 60 : maxLength;
//                maxLength = maxLength < 40 ? 40 : maxLength;
//                maxLength = maxLength > 100 ? 100 : maxLength;
//                sheet.setColumnWidth(i, maxLength * 96);
//            }
//            workbook.write(outputStream);
//            outputStream.flush();
//            LOGGER.info("文件生成完毕,名称:{},导出记录数目:{}", sheetName, (rowSize - 1));
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        } finally {
//            if (null != outputStream) {
//                IOUtils.closeQuietly(outputStream);
//            }
//        }
//    }
//
//    public static ValidateResult<Map<String, Object>> readExcel(XSSFWorkbook xwb, int maxRows,
//            List<ExcelColumn> columns) {
//        LOGGER.info("！！！------文件格式检查完毕，开始遍历数据！");
//        ValidateResult<Map<String, Object>> validateResult = new ValidateResult<>();
//        if (null == columns || columns.isEmpty()) {
//            validateResult.addErrorTip(ValidateResult.badTip(KEY_ERROR_MESSAGE, "请指定正确的导入信息！"));
//            return validateResult;
//        }
//        int columnSize = columns.size();
//        XSSFSheet sheet = xwb.getSheetAt(0);
//        int lastRowNum = sheet.getLastRowNum();
//        Map<String, Object> checkHeaderResult = checkHeader(columns, sheet.getRow(0));
//        if (null != checkHeaderResult) {
//            validateResult.addErrorTip(checkHeaderResult);
//            return validateResult;
//        }
//        Map<String, Object> checkCountResult = checkCount(maxRows, lastRowNum);
//        LOGGER.error("物理跳数:{},逻辑条数:{}", sheet.getPhysicalNumberOfRows(), lastRowNum);
//        LOGGER.info("预期最大条数{},实际条数:{},条数校验结果:{}", maxRows, lastRowNum, checkCountResult);
//        if (null != checkCountResult) {
//            validateResult.addErrorTip(checkCountResult);
//            return validateResult;
//        }
//        int emptyRow = 1;
//        int emptyCell = 1;
//        int invalidLength = 1;
//        List<Map<String, Object>> data = new ArrayList<>();
//        for (int i = 1; i <= lastRowNum; i++) {
//            XSSFRow row = sheet.getRow(i);
//            Map<String, Object> map = new HashMap<>();
//            // 防止excel文件有空行
//            int rowNo = i + 1;
//            String stringCellValue = "";
//            if (nullOrEmptyRow(row)) {
//                validateResult.addInValidItem(ValidateResult.badTip("空行" + (emptyRow++), "第" + rowNo + "行数据为空！"));
//                for (int j = 0; j < columnSize; j++) {
//                    ExcelColumn column = columns.get(j);
//                    map.put(column.getCode(), "");
//                }
//            } else {
//                for (int j = 0; j < columnSize; j++) {
//                    ExcelColumn column = columns.get(j);
//                    String code = column.getCode();
//                    XSSFCell cell = row.getCell(j);
//                    if (null == cell) {
//                        String key = "空单元格" + (emptyCell++);
//                        String reason = "第" + rowNo + "行第" + (j + 1) + "列是空值！";
//                        validateResult.addInValidItem(ValidateResult.badTip(key, reason));
//                    } else {
//                        // 获取单元格内容，
//                        if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
//                            Double val = cell.getNumericCellValue();
//                            stringCellValue = null == val ? null : String.valueOf(val).trim();
//                        } else {
//                            String val = cell.getStringCellValue();
//                            stringCellValue = null == val ? null : val.trim();
//                        }
//                        if (!column.checkLength(StringUtils.length(stringCellValue))) {
//                            String key = "长度错误" + (invalidLength++);
//                            String reason = "第" + rowNo + "行第" + (j + 1) + "列字数超出范围！";
//                            validateResult.addInValidItem(ValidateResult.badTip(key, reason));
//                        }
//                    }
//                    map.put(code, stringCellValue);
//                }
//            }
//            data.add(map);
//        }
//        validateResult.setValidItems(data);
//        LOGGER.info("！！！------遍历成功，有" + data.size() + "条数据！");
//        if (data.isEmpty()) {
//            validateResult.addErrorTip(ValidateResult.badTip(KEY_ERROR_MESSAGE, "导入excel文件无数据，请核对后再做操作！"));
//        }
//        return validateResult;
//    }
//
//    /**
//     * 验证数据条数
//     *
//     * @param maxRows
//     * @param lastRowNum
//     * @return
//     * @author WangLei
//     */
//    private static Map<String, Object> checkCount(int maxRows, int lastRowNum) {
//        if (lastRowNum < 1) {
//            return ValidateResult.badTip(KEY_ERROR_MESSAGE, "导入excel文件无数据，请核对后再做操作！");
//        }
//        if (lastRowNum > maxRows) {
//            return ValidateResult.badTip(KEY_ERROR_MESSAGE, "导入的数据条数超出" + maxRows + "条！");
//        }
//        return null;
//    }
//
//    /**
//     * 校验Sheet页表头
//     * <p>
//     * 第一行必须有数据
//     * </p>
//     *
//     * @param columns
//     * @param row
//     * @return
//     * @author WangLei
//     */
//    private static Map<String, Object> checkHeader(List<ExcelColumn> columns, XSSFRow row) {
//        Map<String, Object> checkResult = new HashMap<>();
//        if (nullOrEmptyRow(row)) {
//            checkResult.put(KEY_ERROR_MESSAGE, "excel文件表头信息缺少！");
//            return checkResult;
//        }
//        for (int j = 0; j < columns.size(); j++) {
//            ExcelColumn column = columns.get(j);
//            XSSFCell cell = row.getCell(j);
//            if (null == cell) {
//                checkResult.put(KEY_ERROR_MESSAGE, "excel文件表头信息缺少！");
//                return checkResult;
//            }
//            if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
//                String cellValue = cell.getStringCellValue();
//                if (!cellValue.equals(column.getTitle())) {
//                    checkResult.put(KEY_ERROR_MESSAGE, "excel文件表头信息不符！");
//                    return checkResult;
//                }
//            } else {
//                checkResult.put(KEY_ERROR_MESSAGE, "第1行第" + (j + 1) + "列单元格类型不是字符型！");
//                return checkResult;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 检查是否是空行,不是空行的话检查当前行是否有有非空值的列
//     *
//     * @param row
//     * @return
//     * @author WangLei
//     */
//    private static boolean nullOrEmptyRow(XSSFRow row) {
//        if (null != row) {
//            for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
//                String value = getCellValue(row.getCell(i, XSSFRow.RETURN_BLANK_AS_NULL));
//                if (!value.trim().equals("")) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 对Excel的各个单元格的格式进行判断并转换
//     */
//    private static String getCellValue(XSSFCell cell) {
//        String cellValue = "";
//        if (null == cell) {
//            return cellValue;
//        }
//        DecimalFormat df = new DecimalFormat("#");
//        switch (cell.getCellType()) {
//            case XSSFCell.CELL_TYPE_STRING:
//                cellValue = cell.getRichStringCellValue().getString().trim();
//                break;
//            case XSSFCell.CELL_TYPE_NUMERIC:
//                cellValue = df.format(cell.getNumericCellValue()).toString();
//                break;
//            case XSSFCell.CELL_TYPE_BOOLEAN:
//                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
//                break;
//            case XSSFCell.CELL_TYPE_FORMULA:
//                cellValue = cell.getCellFormula();
//                break;
//            default:
//                ;
//        }
//        return cellValue;
//    }
//
//    /**
//     * 映射关系
//     *
//     * @author WangLei
//     */
//    public static class ExcelColumn {
//        private String title;
//        private String code;
//        private Integer width;
//        private Integer minLength;
//        private Integer maxLength;
//        private String type;
//
//        /**
//         * 无参构造函数
//         */
//        public ExcelColumn() {
//            // 使用set注入各字段的值
//            this.width = 40;
//        }
//
//        public ExcelColumn(String title, String code) {
//            this(title, code, 40);
//        }
//
//        public ExcelColumn(String title, String code, Integer width) {
//            this.title = title;
//            this.code = code;
//            this.width = width;
//        }
//
//        public boolean checkLength(int length) {
//            boolean minValid;
//            if (null == minLength) {
//                minValid = true;
//            } else {
//                minValid = length >= minLength;
//            }
//            boolean maxValid;
//            if (null == maxLength) {
//                maxValid = true;
//            } else {
//                maxValid = length <= maxLength;
//            }
//
//            return minValid && maxValid;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public Integer getWidth() {
//            return width;
//        }
//
//        public void setWidth(Integer width) {
//            this.width = width;
//        }
//
//        public Integer getMinLength() {
//            return minLength;
//        }
//
//        public void setMinLength(Integer minLength) {
//            this.minLength = minLength;
//        }
//
//        public Integer getMaxLength() {
//            return maxLength;
//        }
//
//        public void setMaxLength(Integer maxLength) {
//            this.maxLength = maxLength;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//    }
//}
