package com.rachel.common.response;

import lombok.Data;

@Data
public class ResponseEntity {
    public static final Integer RESPONSE_CODE_SUCCESS = 200;

    public static final Integer RESPONSE_CODE_FAILURE = 500;

    private Integer code;

    private String message;

    private Object data;

    public static ResponseEntity ok(Object data){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(RESPONSE_CODE_SUCCESS);
        responseEntity.setData(data);
        return responseEntity;
    }

    public static ResponseEntity failure(String message){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(RESPONSE_CODE_FAILURE);
        responseEntity.setMessage(message);
        return responseEntity;
    }


}
