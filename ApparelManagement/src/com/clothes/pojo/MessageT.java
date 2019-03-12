package com.clothes.pojo;

public class MessageT {
    private String msgId;
    private String fromEm;
    private String toEm;
    private String content;
    private String msgDate;
    private String msgState;

    public MessageT() {
    }

    public MessageT(String msgId, String fromEm, String toEm, String content, String msgDate, String msgState) {
        this.msgId = msgId;
        this.fromEm = fromEm;
        this.toEm = toEm;
        this.content = content;
        this.msgDate = msgDate;
        this.msgState = msgState;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId='" + msgId + '\'' +
                ", fromEm='" + fromEm + '\'' +
                ", toEm='" + toEm + '\'' +
                ", content='" + content + '\'' +
                ", msgDate='" + msgDate + '\'' +
                ", msgState='" + msgState + '\'' +
                '}';
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromEm() {
        return fromEm;
    }

    public void setFromEm(String fromEm) {
        this.fromEm = fromEm;
    }

    public String getToEm() {
        return toEm;
    }

    public void setToEm(String toEm) {
        this.toEm = toEm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public String getMsgState() {
        return msgState;
    }

    public void setMsgState(String msgState) {
        this.msgState = msgState;
    }
}
