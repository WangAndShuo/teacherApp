package com.classproject.teacherapp.mapper;

import com.classproject.teacherapp.entity.AppCreJob;
import com.classproject.teacherapp.entity.UserInfo;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.vo.CreateJobVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppCreJobMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AppCreJob record);

    int insertSelective(AppCreJob record);

    AppCreJob selectByPrimaryKey(String uuid);

    List<AppCreJob> selectByTeacher(CreateJobVo createJobVo);

    int updateByPrimaryKeySelective(AppCreJob record);

    int updateByPrimaryKey(AppCreJob record);

    List<UserInfo> selectStrudentByJob(String jobId);

}