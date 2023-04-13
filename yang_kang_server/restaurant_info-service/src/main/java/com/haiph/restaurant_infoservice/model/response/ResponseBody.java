package com.haiph.restaurant_infoservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody {
    public static final int _200=200;
    public static final int _400=400;
    public static final String OK = "ok";
    public static final String FAIL = "fail";
    public static final String CREATE_FAIL = "Create Fail";
    public static final String CREATE_SUCESS = "Create Success";
    public static final String UPDATE_FAIL = "Update Fail";
    public static final String UPDATE_SUCESS = "Update Success";
    public static final String DELETE_FAIL = "Delete Fail";
    public static final String DELETE_SUCESS = "Delte Success";
    public static final String NOT_FIND_ID = "ID not found";

    private Integer code;
    private String message;
    private Object object;

    public ResponseBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
