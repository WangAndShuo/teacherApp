package com.classproject.teacherapp.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * app_note
 * @author 
 */
@Data
public class AppNote implements Serializable {
    private String noteId;

    private String noteInfo;

    private String userId;

    private String createTime;

    private static final long serialVersionUID = 1L;
}