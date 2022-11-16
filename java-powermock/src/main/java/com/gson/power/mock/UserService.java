package com.gson.power.mock;


import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public PageDataVO<UserVO> pageQueryUser(String companyId, Integer beginIndex, Integer limit){
        Integer count = userDAO.getCount(companyId);
        if (count == null || count <= 0){
            return new PageDataVO<>(0, null);
        }
        List<UserVO> userVOList = userDAO.getPage(companyId, beginIndex, limit);
        return new PageDataVO<>(count, userVOList);
    }

}
