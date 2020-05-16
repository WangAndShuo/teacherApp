package com.classproject.teacherapp.service;

import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.entity.AppScore;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.vo.CreateJobVo;
import com.classproject.teacherapp.vo.SelectScoreVo;

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
    BaseResponse createJob(AppCreJob appCreJob);

    /**
     * 查询作业
     * @param createJobVo
     * @return
     */
    BaseResponse selectJob(CreateJobVo createJobVo);

    /**
     * 查询需要交改作业的用户
     * @param jobId
     * @return
     */
    BaseResponse selectStrudentByJob(String jobId);

    /**
     * 查询某作业学生的成绩
     * @param jobId
     * @return
     */
    BaseResponse selectScore(String jobId);

    /**
     * 查询某人成绩
     * @param selectScoreVo
     * @return
     */
    BaseResponse selectScoreOne(SelectScoreVo selectScoreVo);

    /**
     * 修改成绩
     * @param appScore
     * @return
     */
    BaseResponse updateScore(AppScore appScore);
}
