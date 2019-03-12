package com.clothes.pojo;

import java.io.Serializable;

public class Applyitem implements Serializable {
	//id
    private String aiId;
    //物品明细
    private Goodstitem goodsItem;
    //数量
    private Integer num;
    //状态
    private Integer aiState;
    //所属申请
    private Apply apply;

    public String getAiId() {
        return aiId;
    }

    public void setAiId(String aiId) {
        this.aiId = aiId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getAiState() {
        return aiState;
    }

    public void setAiState(Integer aiState) {
        this.aiState = aiState;
    }

	public Goodstitem getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(Goodstitem goodsItem) {
		this.goodsItem = goodsItem;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	@Override
	public String toString() {
		return "Applyitem [aiId=" + aiId + ", goodsItem=" + goodsItem + ", num=" + num + ", aiState=" + aiState
				+ ", apply=" + apply + "]";
	}
    
}
