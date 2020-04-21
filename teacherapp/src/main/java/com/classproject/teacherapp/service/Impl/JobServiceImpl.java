package com.classproject.teacherapp.service.Impl;


import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.mapper.AppCreJobMapper;
import com.classproject.teacherapp.service.JobService;
import com.classproject.teacherapp.util.BaseResponse;
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
    public BaseResponse createJob(AppCreJob appCreJob) {
        int count  = appCreJobMapper.insertSelective(appCreJob);
        if (count > 0) {
            return BaseResponse.ok("插入成功");
        }
        return BaseResponse.error("插入失败");
    }

    @Override
    public BaseResponse selectJob(CreateJobVo createJobVo) {
        List<AppCreJob> list = appCreJobMapper.selectByTeacher(createJobVo);
        return BaseResponse.ok("成功",list);
    }
}
