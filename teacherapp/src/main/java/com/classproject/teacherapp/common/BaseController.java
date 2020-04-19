package com.classproject.teacherapp.common;

import com.classproject.teacherapp.mapper.User;
import com.classproject.teacherapp.util.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BaseController
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/2 11:19
 **/
@RestController
@RequestMapping("/base")
public class BaseController {

    @GetMapping("/excel")
    @ApiOperation(notes = "导出excel", value = "导出excel")
    public BaseResponse excel(HttpServletResponse response){

        User user = new User();
        user.setName("12131");
        List<User> tList = new ArrayList<>();
        tList.add(user);
        List<ExcelBody> exportList = new ArrayList<>(tList);
        // 首行展示数据
        String[] cellText = {"门店名称"};
        // 首行列宽
        int[] columnWidth = {15};
        // 文件名
        String fileName = DateUtils.format(new Date(), DateUtils.YYYYMMDDHHMMSS);
        ExcelUtils.exportXlsx(exportList, cellText, response, fileName, columnWidth);
        return BaseResponse.build(StatusCode.FAIL);
    }

    @GetMapping("/downLoadQrCode")
    @ApiOperation(notes = "下载台卡", value = "下载台卡")
    public void create(HttpServletResponse response){
        String qrUrl = "45546";
        String qrNum = "dsadas";
        ImageUtils.creatQrImge(qrNum + ".png", qrUrl, qrNum, response);
    }

}
