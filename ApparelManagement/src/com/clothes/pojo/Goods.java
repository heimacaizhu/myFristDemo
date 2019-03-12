package com.clothes.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 物品实体类  包括原材料和产品
 * @author 项目组
 *
 */
public class Goods implements Serializable {
	//物品id
    private int gId;
    //物品名
    private String gName;
    //物品品牌名
    private String brandName;
    //物品价格
    private Double gPrice;
    //物品材料类别名
    private String fabrics;
    //物品类别  原材料/产品
    private Integer gType;
    //物品状态
    private Integer gState;
    //物品明细
    private List<Goodstitem> list;

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Double getgPrice() {
        return gPrice;
    }

    public void setgPrice(Double gPrice) {
        this.gPrice = gPrice;
    }

    public String getFabrics() {
        return fabrics;
    }

    public void setFabrics(String fabrics) {
        this.fabrics = fabrics;
    }

    public Integer getgType() {
        return gType;
    }

    public void setgType(Integer gType) {
        this.gType = gType;
    }

    public Integer getgState() {
        return gState;
    }

    public void setgState(Integer gState) {
        this.gState = gState;
    }

	public List<Goodstitem> getList() {
		return list;
	}

	public void setList(List<Goodstitem> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Goods [gId=" + gId + ", gName=" + gName + ", brandName=" + brandName + ", gPrice=" + gPrice
				+ ", fabrics=" + fabrics + ", gType=" + gType + ", gState=" + gState + ", list=" + list + "]";
	}
	
}
