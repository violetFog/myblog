package com.rainy.model;

import javax.persistence.*;


/**
 * Created by PC on 2017/7/3.
 */
@Entity
@Table(name="picture")
public class Picture {
    private Integer pictureId;    //图片Id
    private Integer albumId;     //所属相册
    private String pictureName;  //图片名称
    private String pictureDescription; //图片描述
    private String img;    //图片路径

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }
    @Column(name = "album_id")
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Column(name = "picture_name")
    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Column(name = "picture_description")
    public String getPictureDescription() {
        return pictureDescription;
    }

    public void setPictureDescription(String pictureDescription) {
        this.pictureDescription = pictureDescription;
    }
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (pictureId != null ? !pictureId.equals(picture.pictureId) : picture.pictureId != null) return false;
        if (albumId != null ? !albumId.equals(picture.albumId) : picture.albumId != null) return false;
        if (pictureName != null ? !pictureName.equals(picture.pictureName) : picture.pictureName != null) return false;
        if (pictureDescription != null ? !pictureDescription.equals(picture.pictureDescription) : picture.pictureDescription != null)
            return false;
        return !(img != null ? !img.equals(picture.img) : picture.img != null);

    }

    @Override
    public int hashCode() {
        int result = pictureId != null ? pictureId.hashCode() : 0;
        result = 31 * result + (albumId != null ? albumId.hashCode() : 0);
        result = 31 * result + (pictureName != null ? pictureName.hashCode() : 0);
        result = 31 * result + (pictureDescription != null ? pictureDescription.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", albumId=" + albumId +
                ", pictureName='" + pictureName + '\'' +
                ", pictureDescription='" + pictureDescription + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
