package com.classproject.teacherapp.controller;

import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.service.JobService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import com.classproject.teacherapp.vo.CreateJobVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "创建JOB")
    @PostMapping("/createJob")
    public BaseResponse createJob(AppCreJob createJobVo){
        jobService.createJob(createJobVo);
        return BaseResponse.build(StatusCode.FAIL);
    }

    @ApiOperation(value = "创建JOB")
    @PostMapping("/selectJob")
    public BaseResponse selectJob(@RequestBody CreateJobVo createJobVo){
        log.info("前端传来的数据：{}", createJobVo );
        if (createJobVo == null) {
            return BaseResponse.error("返回数据为空");
        }
        return jobService.selectJob(createJobVo);
    }

    public void get() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);


    }
}
