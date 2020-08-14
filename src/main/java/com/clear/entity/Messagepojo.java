package com.clear.entity;

import java.io.Serializable;

/**
 * ClassName Messagepojo
 *
 * @author qml
 * Date 2020/8/12 17:16
 * Version 1.0
 **/


public class Messagepojo implements Serializable {

    private static final long serialVersionUID = -6451812593150428369L;

    private String sourse;
    private String messageType;
    private String msgContent;
    private String target;
    private String infoSourceIP;
    private String createtime;
    private String otherContent;

    public Messagepojo() {
        super();
    }

    public Messagepojo(String sourse, String messageType, String msgContent,
                       String target, String infoSourceIP, String createtime,
                       String otherContent) {
        super();
        this.sourse = sourse;
        this.messageType = messageType;
        this.msgContent = msgContent;
        this.target = target;
        this.infoSourceIP = infoSourceIP;
        this.createtime = createtime;
        this.otherContent = otherContent;
    }

    public String getSourse() {
        return sourse;
    }

    public void setSourse(String sourse) {
        this.sourse = sourse;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getInfoSourceIP() {
        return infoSourceIP;
    }

    public void setInfoSourceIP(String infoSourceIP) {
        this.infoSourceIP = infoSourceIP;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOtherContent() {
        return otherContent;
    }

    public void setOtherContent(String otherContent) {
        this.otherContent = otherContent;
    }

    @Override
    public String toString() {
        return "Messagepojo [sourse=" + sourse + ", messageType=" + messageType
                + ", msgContent=" + msgContent + ", target=" + target
                + ", infoSourceIP=" + infoSourceIP + ", createtime="
                + createtime + ", otherContent=" + otherContent + "]";
    }

}