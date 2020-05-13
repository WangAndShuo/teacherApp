package com.classproject.teacherapp.service.Impl;


import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.entity.AppScore;
import com.classproject.teacherapp.entity.AppUserinfo;
import com.classproject.teacherapp.entity.UserInfo;
import com.classproject.teacherapp.mapper.AppCreJobMapper;
import com.classproject.teacherapp.mapper.AppUserinfoMapper;
import com.classproject.teacherapp.service.JobService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.DateUtil;
import com.classproject.teacherapp.util.UuidUtils;
import com.classproject.teacherapp.vo.CreateJobVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName JobServiceImpl
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/4/6 17:48
 **/
@Service
@Slf4j
public class JobServiceImpl implements JobService {
    @Autowired
    private AppCreJobMapper appCreJobMapper;

    @Autowired
    private AppUserinfoMapper appUserinfoMapper;

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
        appCreJob.setCreTime(DateUtil.format(new Date(),"yyyyMMddHHmmss"));

        int count  = appCreJobMapper.insertSelective(appCreJob);
        List<AppUserinfo> list = appUserinfoMapper.selectByClass(appCreJob.getClassId());
        log.info("该班级共有 {} 个人",list.size());
        for (AppUserinfo userinfo :list) {
            AppScore score = new AppScore();
            score.setUuid(UuidUtils.getUuid());
            score.setClassName(appCreJob.getClassName());
            score.setUserName(userinfo.getName());
            score.setTeacher(appCreJob.getUserId());
            score.setGrade(userinfo.getGrade());
            score.setJobId(appCreJob.getUuid());
            appCreJobMapper.insertScore(score);
        }
        if (count > 0) {
            return BaseResponse.ok("成功创建作业，该班级共有 "+list.size()+" 人");
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

    /**
     * 查询某作业学生的成绩
     *
     * @param jobId
     * @return
     */
    @Override
    public BaseResponse selectScore(String jobId) {
        List<AppScore> list = appCreJobMapper.selectScore(jobId);
        if(list.size() == 0){
            return  BaseResponse.ok("数据为空");
        }
        return BaseResponse.ok("成功",list);
    }


}
