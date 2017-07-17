package com.rainy.repository;

import com.rainy.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 2017/7/14.
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album,Integer>{

}
