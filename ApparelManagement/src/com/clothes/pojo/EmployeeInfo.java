package com.clothes.pojo;

import java.io.Serializable;

/**
 * 员工信息实体类
 * @author 项目组
 *
 */
public class EmployeeInfo implements Serializable {
	//员工信息id
    private int eiId;
    //员工姓名
    private String emName;
    //员工电话
    private String emPhone;
    //员工信息状态
    private Integer emState;
   //所属员工
    private Employee employee;

    public int getEiId() {
        return eiId;
    }

    public void setEiId(int eiId) {
        this.eiId = eiId;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmPhone() {
        return emPhone;
    }

    public void setEmPhone(String emPhone) {
        this.emPhone = emPhone;
    }

    public Integer getEmState() {
        return emState;
    }

    public void setEmState(Integer emState) {
        this.emState = emState;
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeInfo [eiId=" + eiId + ", emName=" + emName + ", emPhone=" + emPhone + ", emState=" + emState
				+ ", employee=" + employee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eiId;
		result = prime * result + ((emName == null) ? 0 : emName.hashCode());
		result = prime * result + ((emPhone == null) ? 0 : emPhone.hashCode());
		result = prime * result + ((emState == null) ? 0 : emState.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeInfo other = (EmployeeInfo) obj;
		if (eiId != other.eiId)
			return false;
		if (emName == null) {
			if (other.emName != null)
				return false;
		} else if (!emName.equals(other.emName))
			return false;
		if (emPhone == null) {
			if (other.emPhone != null)
				return false;
		} else if (!emPhone.equals(other.emPhone))
			return false;
		if (emState == null) {
			if (other.emState != null)
				return false;
		} else if (!emState.equals(other.emState))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}
    
}
