package com.rainy.repository;

import com.rainy.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PC on 2017/7/14.
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture,Integer>{
    @Query("from Picture p where p.albumId=?1")
    List<Picture> findByAlbumId(Integer albumId);
}
