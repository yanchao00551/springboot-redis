package com.power.common.validator;

import com.power.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.quartz.SimpleTrigger;

public abstract class Assert {
    public static void isBlank(String str,String message){
        if(StringUtils.isBlank(str)){
            throw new RRException(message);
        }
    }
    public static void isNull(Object object, String message){
        if(object == null){
            throw new RRException(message);
        }
    }
}
