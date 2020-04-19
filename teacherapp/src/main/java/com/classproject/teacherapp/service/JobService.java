package com.classproject.teacherapp.service;

import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.vo.CreateJobVo;

import java.util.List;

/**
 * @ClassName JobService
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/6 17:48
 **/
public interface JobService {

    /**
     * 添加作业
     * @param appCreJob
     * @return
     */
    int createJob(AppCreJob appCreJob);

    /**
     * 查询作业
     * @param createJobVo
     * @return
     */
    List<AppCreJob> selectJob(CreateJobVo createJobVo);
}
