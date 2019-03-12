package com.clothes.pojo;

import java.util.List;

/**
 * 客户退货订单
 * @author 项目组
 *
 */
public class Creditorder {
	//退货单id
    private String coId;
    //所属订单
    private Orders orders;
    //客户
    private Customer customer;
    //退货原因
    private String reason;
    //状态
    private Integer coState;
    //退货单明细
    private List<COrderitem> list;

    public String getCoId() {
        return coId;
    }

    public void setCoId(String coId) {
        this.coId = coId;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCoState() {
        return coState;
    }

    public void setCoState(Integer coState) {
        this.coState = coState;
    }

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public List<COrderitem> getList() {
		return list;
	}

	public void setList(List<COrderitem> list) {
		this.list = list;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Creditorder [coId=" + coId + ", orders=" + orders + ", customer=" + customer + ", reason=" + reason
				+ ", coState=" + coState + ", list=" + list + "]";
	}
    
}
