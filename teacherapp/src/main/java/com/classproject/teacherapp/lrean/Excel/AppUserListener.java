package com.classproject.teacherapp.lrean.Excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.classproject.teacherapp.entity.AppUser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @ClassName AppUserListener
 * @Description:    读取文档的监听器类
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/13 19:24
 **/
@Component
@Scope("prototype")
public class AppUserListener extends AnalysisEventListener<AppUser> {


    /**
     *每读一行内容，都会调用一次该对象的invoke，在invoke可以操作使用读取到的数据
     * @param appUser 每次读取到的数据封装的对象
     * @param analysisContext
     */ @Override
    public void invoke(AppUser appUser, AnalysisContext analysisContext) {
        System.out.println("user:"+appUser.toString());
    }

    /**
     * 读取完整个文档之后调用的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
