package com.classproject.teacherapp.controller;

import com.classproject.teacherapp.entity.AppClass;
import com.classproject.teacherapp.service.ClassService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ClassController
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/26 10:56
 **/
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @ApiOperation(value = "获取课程表")
    @GetMapping("/getClass")
    public BaseResponse getClassTable(String[] weeks){
        Map<String, List<AppClass>> map;
        if (weeks.length != 7) {
            return BaseResponse.build(StatusCode.FAIL,"传值不正确.");
        }
        map = classService.listClassByDay(weeks);
        return BaseResponse.build(StatusCode.SUCCESS,map);
    }


}
