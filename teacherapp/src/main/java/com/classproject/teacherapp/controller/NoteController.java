package com.classproject.teacherapp.controller;

import com.classproject.teacherapp.entity.AppNote;
import com.classproject.teacherapp.service.NoteService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Resource
    private NoteService noteService;

    @ApiOperation(value = "添加便签", notes = "添加便签")
    @PostMapping("/insertNote")
    public BaseResponse insertNote(@RequestBody AppNote appNote) {
        log.info("【添加便签】： 内容[{}]\t",appNote);

        return  noteService.insertNote(appNote);
    }

    @ApiOperation(value = "查询用户下所有便签", notes = "查询用户下所有便签")
    @GetMapping("/selectNote/{userId}")
    public BaseResponse selectNote(@PathVariable("userId") String userId) {
        log.info("【查询用户下所有便签】： 内容[{}]\t",userId);

        return   noteService.selectNote(userId);
    }

    @ApiOperation(value = "查询便签内容", notes = "查询便签内容")
    @GetMapping("/selectOneNote/{noteId}")
    public BaseResponse selectOneNote(@PathVariable("noteId") String noteId) {
        log.info("【查询便签内容】： 内容[{}]\t",noteId);
        return  noteService.selectOneNote(noteId);
    }
}
