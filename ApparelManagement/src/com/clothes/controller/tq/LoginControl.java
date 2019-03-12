package com.clothes.controller.tq;

import com.clothes.dao.tq.messageTMapper;
import com.clothes.pojo.Employee;
import com.clothes.pojo.EmployeeT;
import com.clothes.utils.DataGride;
import com.clothes.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginControl {
    @Autowired
    private messageTMapper dao;

    @ResponseBody
    @RequestMapping("/jhsqShow")
    public DataGride jhsqShow(@RequestParam(value = "page",required = false)String page){
        PageHelper.startPage(Integer.parseInt(page),10);

        List<Map<String, Object>> list = dao.jhsqShow();

        DataGride dataGride = PageUtil.convertToResult(list);

        return dataGride;
    }
}
