package com.clothes.pojo;

public class Message {
	//消息内容
    private String content;
    //消息id
    private String msgId;
    //发送人
    private Employee fromEm;
    //接收者
    private Employee toEm;
    //发送时间
    private String msgDate;
    //状态
    private Integer msgState;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public Integer getMsgState() {
        return msgState;
    }

    public void setMsgState(Integer msgState) {
        this.msgState = msgState;
    }
    

	public Employee getFromEm() {
		return fromEm;
	}

	public void setFromEm(Employee fromEm) {
		this.fromEm = fromEm;
	}

	public Employee getToEm() {
		return toEm;
	}

	public void setToEm(Employee toEm) {
		this.toEm = toEm;
	}

	@Override
	public String toString() {
		return "Message [content=" + content + ", msgId=" + msgId + ", fromEm=" + fromEm + ", toEm=" + toEm
				+ ", msgDate=" + msgDate + ", msgState=" + msgState + "]";
	}
    
}
