package com.classproject.teacherapp.service;

import com.classproject.teacherapp.entity.AppNote;
import com.classproject.teacherapp.util.BaseResponse;

public interface NoteService {

    /**
     * 添加便签
     * @param appNote
     * @return
     */
    BaseResponse insertNote(AppNote appNote);

    /**
     * 查询用户下所有便签
     * @param userId
     * @return
     */
    BaseResponse selectNote(String userId);


    /**
     * 查询便签内容
     * @param noteId
     * @return
     */
    BaseResponse selectOneNote(String noteId);
}
