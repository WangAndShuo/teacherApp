package com.classproject.teacherapp.service.Impl;


import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.entity.UserInfo;
import com.classproject.teacherapp.mapper.AppCreJobMapper;
import com.classproject.teacherapp.service.JobService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.UuidUtils;
import com.classproject.teacherapp.vo.CreateJobVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    /**
     * 添加作业
     *
     * @param appCreJob
     * @return
     */
    @Override
    public BaseResponse createJob(AppCreJob appCreJob) {
        String uuid = UuidUtils.getUuid();
        appCreJob.setUuid(uuid);
        int count  = appCreJobMapper.insertSelective(appCreJob);
        if (count > 0) {
            return BaseResponse.ok("插入成功");
        }
        return BaseResponse.error("插入失败");
    }

    /**
     * 查询作业
     *
     * @param createJobVo
     * @return
     */
    @Override
    public BaseResponse selectJob(CreateJobVo createJobVo) {
        List<AppCreJob> list = appCreJobMapper.selectByTeacher(createJobVo);
        return BaseResponse.ok("成功",list);
    }

    /**
     * 查询需要交改作业的用户
     *
     * @param jobId
     * @return
     */
    @Override
    public BaseResponse selectStrudentByJob(String jobId){
        List<UserInfo> userInfos = appCreJobMapper.selectStrudentByJob(jobId);
        return BaseResponse.ok("成功",userInfos);
    }
}
