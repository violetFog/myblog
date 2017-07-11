package com.rainy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by PC on 2017/6/19.
 */
@Entity
@Table(name="blog")
public class Blog implements Serializable{
    private Integer blogId;              //序号
    private String title;      //标题
    private String text;       //正文
    private String blogType;       //类别
    private Date updateTime=new Date();   //更新时间
    private Date createTime=new Date();   //创建时间
    private Integer number=0;        //浏览次数
    private Integer comment=0;               //评论次数
    private String img;        //图片
    private Integer isTop=0; //是否置顶 0:false 1:true

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Column(name = "blog_type")
    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }
    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Column(name = "create_time")
    @Temporal(TemporalType.DATE)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @Column(name = "comment")
    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    @Column(name = "is_top")
    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        if (blogId != null ? !blogId.equals(blog.blogId) : blog.blogId != null) return false;
        if (title != null ? !title.equals(blog.title) : blog.title != null) return false;
        if (text != null ? !text.equals(blog.text) : blog.text != null) return false;
        if (blogType != null ? !blogType.equals(blog.blogType) : blog.blogType != null) return false;
        if (updateTime != null ? !updateTime.equals(blog.updateTime) : blog.updateTime != null) return false;
        if (createTime != null ? !createTime.equals(blog.createTime) : blog.createTime != null) return false;
        if (number != null ? !number.equals(blog.number) : blog.number != null) return false;
        if (comment != null ? !comment.equals(blog.comment) : blog.comment != null) return false;
        if (img != null ? !img.equals(blog.img) : blog.img != null) return false;
        return !(isTop != null ? !isTop.equals(blog.isTop) : blog.isTop != null);

    }

    @Override
    public int hashCode() {
        int result = blogId != null ? blogId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (blogType != null ? blogType.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (isTop != null ? isTop.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", blogType='" + blogType + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", number=" + number +
                ", comment=" + comment +
                ", img='" + img + '\'' +
                ", isTop=" + isTop +
                '}';
    }
}
