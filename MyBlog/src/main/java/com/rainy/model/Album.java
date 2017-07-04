package com.rainy.model;

import javax.persistence.*;

/**
 * Created by PC on 2017/7/3.
 */
@Entity
@Table(name="album")
public class Album {
    private int albumId;           //相册Id
    private String albumDescription;  //相册描述
    private String cover;          //封面路径
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    @Column(name = "albumDescription")
    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (albumId != album.albumId) return false;
        if (albumDescription != null ? !albumDescription.equals(album.albumDescription) : album.albumDescription != null)
            return false;
        return !(cover != null ? !cover.equals(album.cover) : album.cover != null);

    }

    @Override
    public int hashCode() {
        int result = albumId;
        result = 31 * result + (albumDescription != null ? albumDescription.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumDescription='" + albumDescription + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
