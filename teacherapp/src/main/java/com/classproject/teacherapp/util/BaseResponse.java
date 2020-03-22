package com.classproject.teacherapp.util;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class BaseResponse {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;


    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    // 状态码
    private Integer code;

    private StatusCode statusCode;

    public static BaseResponse build(Integer status, String msg, Object data) {
        return new BaseResponse(status, msg, data);
    }

    public static BaseResponse build(Integer status, String msg, Object data, Integer code) {
        return new BaseResponse(status, msg, data,code);
    }

    public static BaseResponse build(StatusCode statusCode, Object data) {
        return new BaseResponse(statusCode, data);
    }

    public static BaseResponse build(Integer status, String msg) {
        return new BaseResponse(status, msg, null);
    }

    public static BaseResponse build(StatusCode statusCode) {
        return new BaseResponse(statusCode, null);
    }

    public static BaseResponse build(StatusCode statusCode,Object data,Integer code) {
        return new BaseResponse(statusCode,  data,  code);
    }

    public static BaseResponse ok(Object data) {
        return new BaseResponse(data);
    }

    public static BaseResponse ok() {
        return new BaseResponse(null);
    }

    public BaseResponse() {

    }

    public BaseResponse(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public BaseResponse(Integer status, String msg, Object data, Integer code) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }
    public BaseResponse(StatusCode statusCode, String msg, Object data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }
    public BaseResponse(StatusCode StatusCode, Object data) {
        this.status = StatusCode.getCode();
        this.msg = StatusCode.getMsg();
        this.data = data;
    }
    public BaseResponse(StatusCode StatusCode, Object data, Integer code) {
        this.status = StatusCode.getCode();
        this.msg = StatusCode.getMsg();
        this.data = data;
        this.code = code;
    }
    public BaseResponse(Object data) {
        this.status = 0;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为BaseResponse 对象
     *
     * @param jsonData json数据
     * @param clazz BaseResponse 中的object类型
     * @return
     */
    public static BaseResponse formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, BaseResponse.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static BaseResponse format(String json) {
        try {
            return MAPPER.readValue(json, BaseResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static BaseResponse formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
