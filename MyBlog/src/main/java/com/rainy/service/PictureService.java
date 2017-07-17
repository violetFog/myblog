package com.rainy.service;

import com.rainy.model.Picture;

import java.util.List;

/**
 * Created by PC on 2017/7/14.
 */
public interface PictureService {
    List<Picture> findByAlbumId(Integer albumId);
}
