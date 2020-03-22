package com.classproject.teacherapp.controller;

import com.classproject.teacherapp.dao.User;
import com.classproject.teacherapp.entity.AppUser;
import com.classproject.teacherapp.redis.RedisUtils;
import com.classproject.teacherapp.service.LoginService;
import com.classproject.teacherapp.util.BaseResponse;
import com.classproject.teacherapp.util.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/2 11:17
 **/
@Api(description = "用户操作接口")
@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "登陆", notes = "通过用户名和密码登陆")
    @ResponseBody
    @GetMapping("/login")
    public BaseResponse login(@RequestBody AppUser user){
        boolean FLAG = (null == user);
        boolean RedisFLAG = (boolean)redisUtils.get(user.getUsername());
        if(FLAG){
            return new BaseResponse(StatusCode.Fail);
        }

        if(RedisFLAG){
            return new BaseResponse(StatusCode.Success);
        }
        int num = loginService.getUser(user);
        redisUtils.set(user.getUsername(),true);

        return new BaseResponse(StatusCode.Success);
    }



    @ApiOperation(value = "获取otp", notes="通过手机号获取OTP验证码")
    //@ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @ResponseBody
    public User getOtp(@RequestParam(name = "telephone") String telphone) {
        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

//        //将OTP验证码同对应用户的手机号关,使用httpsession的方式绑定他的手机号与OTPCode
////        httpServletRequest.getSession().setAttribute(telphone,otpCode);
////        //将OTP验证码通过短信通道发送给用户，省略
////        System.out.println("telphone = " + telphone + "& otpCode = " + otpCode);
////        OtpVo otpVo = new OtpVo();
////        otpVo.setTelephone(telphone);
////        otpVo.setOtpCode(otpCode);
//        return CommonReturnType.create(otpVo);
        return null;
    }
//...
}
