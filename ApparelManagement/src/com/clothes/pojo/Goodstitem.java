package com.clothes.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 物品明细实体类
 * @author 项目组
 *
 */
public class Goodstitem implements Serializable {
	//物品明细id
    private int giId;
    //所属物品
    private Goods goods;
    //颜色
    private String color;
    //尺寸
    private String size;
    //物品状态
    private Integer giState;
   //物品图片
    private List<Goodsimgs> imgList;
    //物品订单
    private List<Orderitem> orderitemList;
    //物品退货明细
    private List<COrderitem> colist;
    //物品库存
    private List<Inventory> invenList;
    //物品出入库
    private List<Recorditem> recordList;
    //物品相关申请
    private List<Applyitem> applyitemList;

    public int getGiId() {
        return giId;
    }

    public void setGiId(int giId) {
        this.giId = giId;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getGiState() {
        return giState;
    }

    public void setGiState(Integer giState) {
        this.giState = giState;
    }

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<Goodsimgs> getImgList() {
		return imgList;
	}

	public void setImgList(List<Goodsimgs> imgList) {
		this.imgList = imgList;
	}

	public List<COrderitem> getColist() {
		return colist;
	}

	public void setColist(List<COrderitem> colist) {
		this.colist = colist;
	}

	public List<Inventory> getInvenList() {
		return invenList;
	}

	public void setInvenList(List<Inventory> invenList) {
		this.invenList = invenList;
	}

	public List<Recorditem> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Recorditem> recordList) {
		this.recordList = recordList;
	}

	public List<Orderitem> getOrderitemList() {
		return orderitemList;
	}

	public void setOrderitemList(List<Orderitem> orderitemList) {
		this.orderitemList = orderitemList;
	}

	public List<Applyitem> getApplyitemList() {
		return applyitemList;
	}

	public void setApplyitemList(List<Applyitem> applyitemList) {
		this.applyitemList = applyitemList;
	}

	@Override
	public String toString() {
		return "Goodstitem [giId=" + giId + ", goods=" + goods + ", color=" + color + ", size=" + size + ", giState="
				+ giState + ", imgList=" + imgList + ", orderitemList=" + orderitemList + ", colist=" + colist
				+ ", invenList=" + invenList + ", recordList=" + recordList + ", applyitemList=" + applyitemList + "]";
	}
    
}
