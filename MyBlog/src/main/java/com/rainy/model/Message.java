package com.rainy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by PC on 2017/7/20.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable{
    private Integer messageId;
    private String IP;             //访问IP
    private String messager="匿名";    //留言人
    private String content;        //留言内容
    private Date createDate = new Date();  //留言时间


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    @Column(name = "ip")
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    @Column(name = "messager")
    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != null ? !messageId.equals(message.messageId) : message.messageId != null) return false;
        if (IP != null ? !IP.equals(message.IP) : message.IP != null) return false;
        if (messager != null ? !messager.equals(message.messager) : message.messager != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        return !(createDate != null ? !createDate.equals(message.createDate) : message.createDate != null);

    }

    @Override
    public int hashCode() {
        int result = messageId != null ? messageId.hashCode() : 0;
        result = 31 * result + (IP != null ? IP.hashCode() : 0);
        result = 31 * result + (messager != null ? messager.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", IP='" + IP + '\'' +
                ", messager='" + messager + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
