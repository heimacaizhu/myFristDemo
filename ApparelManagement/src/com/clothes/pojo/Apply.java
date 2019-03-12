package com.clothes.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 计划申请实体类
 * @author 项目组
 *
 */
public class Apply implements Serializable {
	//id
    private String apId;
    //备注
    private String remark;
    //处理结果
    private String result;
    //状态
    private Integer apState;
    //类型
    private Integer apType;
    //附件
    private String accessory;
    //申请人
    private Employee applicant;
    //处理人
    private Employee deal_person;
    //申请时间
    private String apDate;
    //处理时间
    private String apDealdate;
    //处理级别
    private Integer apLever;
    //客户
    private Customer customer;
    //申请明细
    private List<Applyitem> list;

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getApState() {
        return apState;
    }

    public void setApState(Integer apState) {
        this.apState = apState;
    }

    public Integer getApType() {
        return apType;
    }

    public void setApType(Integer apType) {
        this.apType = apType;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }
    
	public Employee getApplicant() {
		return applicant;
	}

	public void setApplicant(Employee applicant) {
		this.applicant = applicant;
	}

	public Employee getDeal_person() {
		return deal_person;
	}

	public void setDeal_person(Employee deal_person) {
		this.deal_person = deal_person;
	}

	public List<Applyitem> getList() {
		return list;
	}

	public void setList(List<Applyitem> list) {
		this.list = list;
	}

	public String getApDate() {
		return apDate;
	}

	public void setApDate(String apDate) {
		this.apDate = apDate;
	}

	public String getApDealdate() {
		return apDealdate;
	}

	public void setApDealdate(String apDealdate) {
		this.apDealdate = apDealdate;
	}

	public Integer getApLever() {
		return apLever;
	}

	public void setApLever(Integer apLever) {
		this.apLever = apLever;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Apply [apId=" + apId + ", remark=" + remark + ", result=" + result + ", apState=" + apState
				+ ", apType=" + apType + ", accessory=" + accessory + ", applicant=" + applicant + ", deal_person="
				+ deal_person + ", apDate=" + apDate + ", apDealdate=" + apDealdate + ", apLever=" + apLever
				+ ", customer=" + customer + ", list=" + list + "]";
	}
    
}
