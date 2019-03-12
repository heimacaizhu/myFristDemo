package com.clothes.pojo;

import java.util.List;

/**
 * 区域实体类
 * @author 项目组
 *
 */
public class Region {
	//区域id
    private int rId;
    //区域名
    private String rName;
    //父区域id
    private Integer rPid;
    //状态
    private Integer rState;
    //该区域客户
    private List<Customer> list;
    
    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public Integer getrState() {
        return rState;
    }

    public void setrState(Integer rState) {
        this.rState = rState;
    }

	public List<Customer> getCustomer() {
		return list;
	}

	public void setCustomer(List<Customer> list) {
		this.list = list;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public Integer getrPid() {
		return rPid;
	}

	public void setrPid(Integer rPid) {
		this.rPid = rPid;
	}

	@Override
	public String toString() {
		return "Region [rId=" + rId + ", rName=" + rName + ", rPid=" + rPid + ", rState=" + rState + ", list=" + list
				+ "]";
	}
    
}
