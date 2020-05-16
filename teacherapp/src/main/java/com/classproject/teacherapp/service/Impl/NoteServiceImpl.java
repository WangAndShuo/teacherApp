package com.classproject.teacherapp.service.Impl;

import com.classproject.teacherapp.entity.AppNote;
import com.classproject.teacherapp.mapper.AppNoteDao;
import com.classproject.teacherapp.service.NoteService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.DateUtil;
import com.classproject.teacherapp.util.UuidUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private AppNoteDao appNoteDao;

    @Override
    public BaseResponse insertNote(AppNote appNote) {
        String UUID = UuidUtils.getUuid();
        if(appNote.getNoteId() != null){
            appNoteDao.updateByPrimaryKeySelective(appNote);
            return BaseResponse.ok("修改便签成功");
        }
        appNote.setNoteId(UUID);
        appNote.setCreateTime(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        int i = appNoteDao.insertSelective(appNote);
        if(i == 0){
            return  BaseResponse.error("添加便签失败，请重试");
        }
        return BaseResponse.ok("添加便签成功");
    }

    @Override
    public BaseResponse selectNote(String userId) {
        List<AppNote> appNotes = appNoteDao.selectByUser(userId);
        if(appNotes == null){
            return  BaseResponse.error("该用户没有添加过便签");
        }
        return  BaseResponse.ok("查询便签成功，共有便签 "+ appNotes.size()+" 个",appNotes);
    }

    @Override
    public BaseResponse selectOneNote(String noteId) {

        AppNote appNote = appNoteDao.selectByPrimaryKey(noteId);

        if(appNote == null){
            return  BaseResponse.error("该用户没有添加过便签");
        }
        return  BaseResponse.ok("查询便签成功",appNote);
    }
}
