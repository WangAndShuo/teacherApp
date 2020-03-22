/**
 *
 */
package com.classproject.teacherapp.util;


import java.util.UUID;

/**
 * <功能详细描述>
 *
 * @author wenc
 * @version [版本号, 2014年12月29日 下午2:15:19 ]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UuidUtils  {

    /**
     * 使用JDK自带工具生成32的UUID
     * <p>创 建 人：  wenc<br>
     * 创建时间：  2014年12月29日 下午2:15:22
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
