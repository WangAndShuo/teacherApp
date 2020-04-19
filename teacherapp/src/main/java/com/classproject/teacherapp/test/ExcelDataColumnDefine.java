//package com.classproject.teacherapp.util;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * 统一管理各个部分的导出定义
// * see: com.lemon.util.ExcelDataSupporter
// * see: com.lemon.util.ExcelDataSupporter$ExcelColumn
// */
//public class ExcelDataColumnDefine {
//
//    private static final Map<String, Map<String, ExcelDataSupporter.ExcelColumn>> STORE = new HashMap<>();
//
//    static {
//        init();
//    }
//
//    ExcelDataColumnDefine() {
//    }
//
//    /**
//     * Key:Value
//     * <p>
//     * JrfSts : 即日付状态导出<br/>
//     * </p>
//     *
//     * @param key
//     * @return
//     * @author WangLei
//     */
//    public static Map<String, ExcelDataSupporter.ExcelColumn> find(String key) {
//        Map<String, ExcelDataSupporter.ExcelColumn> value = STORE.get(key);
//        return null == value ? Collections.emptyMap() : value;
//    }
//
//    /**
//     * 注册 存在的键或空的值返回false
//     *
//     * @param key
//     * @param excelColumnDefine
//     * @return
//     * @author WangLei
//     */
//    public static boolean regist(String key, Map<String, ExcelDataSupporter.ExcelColumn> excelColumnDefine) {
//        if (null == excelColumnDefine || STORE.containsKey(key)) {
//            return false;
//        }
//        STORE.put(key, excelColumnDefine);
//        return true;
//    }
//
//    private static void init() {
//        Map<String, ExcelDataSupporter.ExcelColumn> columnInfoJrfSts = new LinkedHashMap<>();
//        ExcelDataSupporter.ExcelColumn mno = new ExcelDataSupporter.ExcelColumn();
//        mno.setTitle("商户编号");
//        mno.setMaxLength(15);
//        mno.setCode("mno");
//        columnInfoJrfSts.put("mno", mno);
//        ExcelDataSupporter.ExcelColumn cprRegNmCn = new ExcelDataSupporter.ExcelColumn();
//        cprRegNmCn.setTitle("商户名称");
//        cprRegNmCn.setWidth(80);
//        mno.setCode("cprRegNmCn");
//        columnInfoJrfSts.put("cprRegNmCn", cprRegNmCn);
//        ExcelDataSupporter.ExcelColumn jrfStsDisplay = new ExcelDataSupporter.ExcelColumn();
//        jrfStsDisplay.setTitle("即日付状态");
//        jrfStsDisplay.setWidth(20);
//        jrfStsDisplay.setCode("jrfStsDisplay");
//        columnInfoJrfSts.put("JRFStsDisplay", jrfStsDisplay);
//        ExcelDataSupporter.ExcelColumn rate = new ExcelDataSupporter.ExcelColumn();
//        rate.setTitle("即日付费率");
//        rate.setWidth(20);
//        columnInfoJrfSts.put("rate", rate);
//        ExcelDataSupporter.ExcelColumn openSysIdDisplay = new ExcelDataSupporter.ExcelColumn();
//        openSysIdDisplay.setTitle("最后开通渠道");
//        openSysIdDisplay.setWidth(40);
//        columnInfoJrfSts.put("openSysIdDisplay", openSysIdDisplay);
//        ExcelDataSupporter.ExcelColumn lastModifyTime = new ExcelDataSupporter.ExcelColumn();
//        lastModifyTime.setTitle("最后操作时间");
//        lastModifyTime.setWidth(60);
//        columnInfoJrfSts.put("lastModifyTime", lastModifyTime);
//        regist("JrfSts", columnInfoJrfSts);
//    }
//}
