package com.clothes.pojo;

import java.util.List;

/**
 * 订单实体类
 * @author 项目组
 *
 */
public class Orders {
	//订单id
    private String oId;
    //下单时间
    private String oDate;
    //下单客户
    private Customer customer;
    //客服员工
    private Employee employee;
    //订单状态
    private Integer oState;
    //已付金额
    private Double spend;
    //金额
    private Double total;
    //未付金额
    private Double notPayment;
    //订单money支付状态
    private Integer paymentState;
    //订单状态
    private String latest;
    //订单条目
    private List<Orderitem> list;
    //该订单的退货订单
    private List<Creditorder> creList;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }
    
    public Integer getoState() {
        return oState;
    }

    public void setoState(Integer oState) {
        this.oState = oState;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getNotPayment() {
        return notPayment;
    }

    public void setNotPayment(Double notPayment) {
        this.notPayment = notPayment;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Orderitem> getList() {
		return list;
	}

	public void setList(List<Orderitem> list) {
		this.list = list;
	}

	public List<Creditorder> getCreList() {
		return creList;
	}

	public void setCreList(List<Creditorder> creList) {
		this.creList = creList;
	}

	@Override
	public String toString() {
		return "Orders [oId=" + oId + ", oDate=" + oDate + ", customer=" + customer + ", employee=" + employee
				+ ", oState=" + oState + ", spend=" + spend + ", total=" + total + ", notPayment=" + notPayment
				+ ", paymentState=" + paymentState + ", latest=" + latest + ", list=" + list + ", creList=" + creList
				+ "]";
	}
    
}
