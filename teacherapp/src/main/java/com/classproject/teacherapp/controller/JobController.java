package com.classproject.teacherapp.controller;

import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.service.JobService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import com.classproject.teacherapp.vo.CreateJobVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName JobController
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/2 21:23
 **/
@Slf4j
@Api
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @ApiOperation(value = "创建作业")
    @PostMapping("/createJob")
    public BaseResponse createJob(@RequestBody  AppCreJob createJobVo){
        if (StringUtils.isBlank(createJobVo.getUserId())) {
            return BaseResponse.error("发布人为空");
        }
        if (StringUtils.isBlank(createJobVo.getClassName())) {
            return BaseResponse.error("请添加正确的班级");
        }

        return jobService.createJob(createJobVo);
    }

    @ApiOperation(value = "查询该用户下的所有作业")
    @PostMapping("/selectJob")
    public BaseResponse selectJob(@RequestBody CreateJobVo createJobVo){
        log.info("前端传来的数据：{}", createJobVo );
        if (createJobVo == null) {
            return BaseResponse.error("返回数据为空");
        }
        return jobService.selectJob(createJobVo);
    }

    @ApiOperation(value = "查询某作业学生的成绩")
    @PostMapping("/selectScore")
    public BaseResponse selectScore(@RequestBody CreateJobVo createJobVo){
        log.info("前端传来的数据：{}", createJobVo );
        if (createJobVo == null) {
            return BaseResponse.error("返回数据为空");
        }
        return jobService.selectJob(createJobVo);
    }


    @ApiOperation(value = "查询学生用户下的所有作业")
    @PostMapping("/selectJob/{jobId}")
    public BaseResponse selectStrudentByJob(@PathVariable("jobId")  String jobId){
        return jobService.selectScore(jobId);
    }

    public void get() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
    }
}
