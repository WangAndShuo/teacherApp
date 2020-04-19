package com.classproject.teacherapp.controller;

import com.alibaba.excel.EasyExcel;
import com.classproject.teacherapp.common.BaseController;
import com.classproject.teacherapp.common.Exception.MyTranException;
import com.classproject.teacherapp.mapper.User;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.entity.AppUserinfo;
import com.classproject.teacherapp.common.redis.RedisUtils;
import com.classproject.teacherapp.service.LoginService;
import com.classproject.teacherapp.service.UserInfoService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import com.classproject.teacherapp.util.StringUtil;
import com.classproject.teacherapp.util.UuidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @ClassName UserController
 * @Description:
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/2 11:17
 **/
@Api/*description = "用户操作接口"*/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "登陆", notes = "通过用户名和密码登陆")
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse login(@RequestBody AppUser user){
        boolean flag = (null == user);
        if(flag){
            return new BaseResponse(StatusCode.FAIL);
        }
        //查看redis中是否有用户
//        if(null == (User)redisUtils.get(user.getUuid())){
//            return new BaseResponse(StatusCode.SUCCESS);
//        }
        int num = loginService.getUser(user);
        log.info("取得了{}条数据",num);
        redisUtils.set(user.getUsername(),true);
        return new BaseResponse(StatusCode.SUCCESS);
    }

    @Override
    @ResponseBody
    @GetMapping("/lo")
    public BaseResponse excel(HttpServletResponse response){
        String fileName = "demo.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        // 参数一：写入excel文件路径
        // 参数二：写入的数据类型是DemoData
        // data()方法是写入的数据，结果是List<DemoData>集合
        EasyExcel.write(fileName, AppUser.class).sheet("模板").doWrite(data());
        return  BaseResponse.build(StatusCode.FAIL);
    }

    public List<AppUser> data(){
        List<AppUser> list = null ;
        AppUser appUser = new AppUser();
        appUser.setUuid("121164");

        return list;
    }

   /* @ApiOperation(value = "登陆", notes = "通过邮箱和验证码登陆")
    @ResponseBody
    @GetMapping("/emailLogin")
    public BaseResponse emailLogin(@RequestBody AppUser user){
        boolean flag = (null == user);

        if(flag){
            return new BaseResponse(StatusCode.Fail);
        }

        //查看redis中是否有用户
        if(null == (User)redisUtils.get(user.getUuid())){
            return new BaseResponse(StatusCode.Success);
        }

        int num = loginService.getUser(user);
        log.info("取得了{}条数据",num);
        redisUtils.set(user.getUsername(),true);
        return new BaseResponse(StatusCode.Success);
    }*/

    @ApiOperation(value = "注册", notes = "注册用户")
    @PostMapping("/register")
    @Transactional(rollbackFor= MyTranException.class)
    public  BaseResponse register(String username, String password){
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return  new BaseResponse(StatusCode.FAIL);
        }
        //判断用户是否存在
        if (loginService.getUserByName(username) == 1) {
            return  new BaseResponse(StatusCode.USER_IS_HAVE);
        }

        //登陆用户
        AppUser appUser = new AppUser();
        String uuid = UuidUtils.getUuid();
        appUser.setUsername(username);
        appUser.setPassword(password);
        appUser.setUuid(uuid);

        //用户详情
        AppUserinfo appUserinfo = new AppUserinfo();
        appUserinfo.setUuid(uuid);

        int num = 0;
        try {
            num = loginService.insertUser(appUser);
        } catch (MyTranException e) {
            log.info("抛出异常", e);
        }

        if (num < 1) {
            log.info("注册失败");
            return  new BaseResponse(StatusCode.FAIL,appUser);
        }
        return  new BaseResponse(StatusCode.SUCCESS,appUser);
    }

    @ApiOperation(value = "查询用户是否存在", notes = "查询用户是否存在")
    @PostMapping("/theSame")
    public  BaseResponse theSame(String username) {
        int num = loginService.getUserByName("username");
        if(num == 1){
            return  BaseResponse.build(StatusCode.USER_IS_HAVE);
        }
        return  BaseResponse.build(StatusCode.TRUE);
    }

}
