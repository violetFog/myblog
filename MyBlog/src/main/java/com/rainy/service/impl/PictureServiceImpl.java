package com.rainy.service.impl;

import com.rainy.model.Picture;
import com.rainy.repository.PictureRepository;
import com.rainy.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by PC on 2017/7/14.
 */
@Transactional
@Service
public class PictureServiceImpl implements PictureService{
    @Autowired
    private PictureRepository pictureRepository;
    @Override
    public List<Picture> findByAlbumId(Integer albumId) {
        return pictureRepository.findByAlbumId(albumId);
    }
}
