package com.classproject.teacherapp.util;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @ClassName TokenTools
 * @Description: TODO
 * @Author wangshuo[wang_shuo2@suixingpay.com]
 * @Date 2020/3/23 14:16
 * Token的工具类
 * @author zhous
 * @since 2018-2-23 14:01:41
 *
 */
@Service
public class TokenTools {
    /**
     * 生成token放入session
     * @param request
     * @param tokenServerkey
     */
    public static void createToken(HttpServletRequest request,String tokenServerkey){
        String token = TokenProccessor.getInstance().makeToken();
        request.getSession().setAttribute(tokenServerkey, token);
    }


    /**
     * 移除token
     * @param request
     * @param tokenServerkey
     */
    public static void removeToken(HttpServletRequest request,String tokenServerkey){
        request.getSession().removeAttribute(tokenServerkey);
    }

    /**
     * 判断请求参数中的token是否和session中一致
     * @param request
     * @param tokenClientkey
     * @param tokenServerkey
     * @return
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request,String tokenClientkey,String tokenServerkey){
        String token_client = request.getParameter(tokenClientkey);
        if(StringUtils.isEmpty(token_client)){
            return false;
        }
        String token_server = (String) request.getSession().getAttribute(tokenServerkey);
        if(StringUtils.isEmpty(token_server)){
            return false;
        }
        if(!token_server.equals(token_client)){
            return false;
        }
        return true;
    }
}
