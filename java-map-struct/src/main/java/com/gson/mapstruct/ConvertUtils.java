package com.gson.mapstruct;

import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Named("ConvertUtils")
public class ConvertUtils {

    @Named("convert2StatusList")
    public static  List<Integer> convert2StatusList(Integer status){
        List<Integer> statusList = new ArrayList<>();
        if (status == 1){
            statusList.add(1);
            statusList.add(2);
        }else if (status == 2){
            statusList.add(3);
            statusList.add(4);
        }else if (status == 3){
            statusList.add(5);
            statusList.add(6);
        }
        return statusList;
    }
}
