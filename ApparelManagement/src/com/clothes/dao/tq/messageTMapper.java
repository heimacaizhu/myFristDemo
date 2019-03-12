package com.clothes.dao.tq;

import com.clothes.pojo.EmployeeT;
import com.clothes.pojo.MessageT;

import java.util.List;
import java.util.Map;

public interface messageTMapper {
    public EmployeeT employeetById(String id);
    public List<Map<String,Object>> jhsqShow();
    public int addMessage(MessageT message);
}
