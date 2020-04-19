package com.classproject.teacherapp.service.Impl;


import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.mapper.AppCreJobMapper;
import com.classproject.teacherapp.service.JobService;
import com.classproject.teacherapp.vo.CreateJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName JobServiceImpl
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/6 17:48
 **/
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private AppCreJobMapper appCreJobMapper;

    @Override
    public int createJob(AppCreJob appCreJob) {
        int count  = appCreJobMapper.insertSelective(appCreJob);
        return count;
    }

    @Override
    public List<AppCreJob> selectJob(CreateJobVo createJobVo) {
        return appCreJobMapper.selectByTeacher(createJobVo);
    }
}
