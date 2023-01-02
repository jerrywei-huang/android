package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataService {

    public static List<HashMap<String,Object>> mapList=new ArrayList<>();

    public boolean add(User user){
        boolean flag=false;
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name",user.getUsername());
        hashMap.put("password",user.getPassword());
        hashMap.put("hobby",user.getHobby());
        hashMap.put("gender",user.getGender());
        hashMap.put("marrige",user.getMarrige());
        hashMap.put("position",user.getPosition());

        flag=mapList.add(hashMap);
        return  flag;

    }

    public List<HashMap<String,Object>> getUserList(){
        return mapList;
    }



}
