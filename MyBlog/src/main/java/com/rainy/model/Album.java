package com.rainy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by PC on 2017/7/3.
 */
@Entity
@Table(name="album")
public class Album implements Serializable{
    private int albumId;           //相册Id
    private String albumName;     //相册名称
    private String albumDescription;  //相册描述
    private String albumType;        //相册类型
    private String cover="/images/default.png";//封面压缩图
    private String src;//原始图
    private Date createTime=new Date();           //创建时间
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    @Column(name = "album_name")
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Column(name = "album_description")
    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }
    @Column(name = "album_type")
    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    @Column(name = "src")
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (albumId != album.albumId) return false;
        if (albumName != null ? !albumName.equals(album.albumName) : album.albumName != null) return false;
        if (albumDescription != null ? !albumDescription.equals(album.albumDescription) : album.albumDescription != null)
            return false;
        if (albumType != null ? !albumType.equals(album.albumType) : album.albumType != null) return false;
        if (cover != null ? !cover.equals(album.cover) : album.cover != null) return false;
        if (src != null ? !src.equals(album.src) : album.src != null) return false;
        return !(createTime != null ? !createTime.equals(album.createTime) : album.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + (albumName != null ? albumName.hashCode() : 0);
        result = 31 * result + (albumDescription != null ? albumDescription.hashCode() : 0);
        result = 31 * result + (albumType != null ? albumType.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (src != null ? src.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", albumDescription='" + albumDescription + '\'' +
                ", albumType='" + albumType + '\'' +
                ", cover='" + cover + '\'' +
                ", src='" + src + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
