package com.classproject.teacherapp.lrean;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.lrean.Excel.AppUserListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @ClassName LreaningWork_Excelinput
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/13 19:18
 **/
@Slf4j
public class LreaningWork_Excelinput {



    @Test
    public void test01(){
        /**
         * 构建一个读的工作簿对象
         *
         * @param pathName
         *          要读的文件路径
         * @param head
         *          文件中每一行数据要存储的实体的类型的class
         * @param readListener
         *          读监听器，没读一行内容，都会调用一次该对象的invoke，在invoke可以操作使用读取到的数据
         *
         */
        //获取工作簿
        ExcelReaderBuilder readWorkBook = EasyExcel.read("a.xlsx", AppUser.class, new AppUserListener());
        //获取一个工作表
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
        //读取工作表中内容
        sheet.doRead();
    }

    @Test
    public void test02(){
        /**
         * 构建一个写的工作簿对象
         *
         * @param pathName
         *          要写入的文件路径
         * @param head
         *          封装写入的数据的实体的类型
         */
        //工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write("a-write.xlsx", AppUser.class);
        //工作表
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        //准备数据
        List<AppUser> user = initData();
        //写
        sheet.doWrite(user);
    }


    /**
     * 列式组合填充
     */
    public void test03(){
        String tempalte = "";
        //工作簿对象
        ExcelWriter workBook = EasyExcel.write("a.xlsx", AppUser.class).withTemplate(tempalte).build();
        //工作表
        WriteSheet sheet = EasyExcel.writerSheet().build();
        //组合填充是，因为多组填充的数据量不确定，需要在多组填充完之后另起一行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();
        //准备数据
        List<AppUser> user = initData();

        Map map = new HashMap();
        map.put("date","xxxxx");
        map.put("total","100000");


        //写
        //多组填充
        workBook.fill(user,fillConfig,sheet);
        //单组填充
        workBook.fill(map,sheet);
        //关闭流
        workBook.finish();

    }

    /**
     * 数据水平填充
     * {age}——单组
     * {.age}——多组
     */
    public void test04(){
        String tempalte = "";
        //工作簿对象
        ExcelWriter workBook = EasyExcel.write("a.xlsx", AppUser.class).withTemplate(tempalte).build();
        //工作表
        WriteSheet sheet = EasyExcel.writerSheet().build();
        //组合填充是，因为多组填充的数据量不确定，需要在多组填充完之后另起一行
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        //准备数据
        List<AppUser> user = initData();

//        Map map = new HashMap();
//        map.put("date","xxxxx");
//        map.put("total","100000");
//

        //写
        //多组填充
        workBook.fill(user,fillConfig,sheet);
//        //单组填充
//        workBook.fill(map,sheet);
        //关闭流
        workBook.finish();
    }


    /**
     * 综合练习——报表导出
     */
    public void test05(){
        String tempalte = "";
        //工作簿对象
        ExcelWriter workBook = EasyExcel.write("a.xlsx", AppUser.class).withTemplate(tempalte).build();
        //工作表
        WriteSheet sheet = EasyExcel.writerSheet().build();
        //组合填充是，因为多组填充的数据量不确定，需要在多组填充完之后另起一行
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        //准备数据
        List<AppUser> user = initData();

        workBook.fill(user,fillConfig,sheet);
//        //单组填充
//        workBook.fill(map,sheet);
        //关闭流
        workBook.finish();
    }
    private static List<AppUser> initData(){
        ArrayList<AppUser> list = new ArrayList<>();
        AppUser user = new AppUser();
        for (int i = 0; i < 3; i++) {
            user.setUsername("姓名："+i);
            user.setPassword(UUID.randomUUID().toString());
            list.add(user);
        }
        return list;
    }

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
}
