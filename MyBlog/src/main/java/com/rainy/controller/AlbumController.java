package com.rainy.controller;

import com.rainy.model.Album;
import com.rainy.model.Picture;
import com.rainy.repository.AlbumRepository;
import com.rainy.repository.PictureRepository;
import com.rainy.service.PictureService;
import com.rainy.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/7/13.
 */
@Controller
@RequestMapping(value = "/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private PictureService pictureService;
    @RequestMapping(value = "/view/{albumId}",method = {RequestMethod.GET})
    public String showPhone(){
        return "page/showPhone";
    }



    @RequestMapping(value = "createAlbum",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> createAlbum(HttpServletRequest request){
        String albumName = StringUtil.null2Str(request.getParameter("albumName"));
        String albumDescription = StringUtil.null2Str(request.getParameter("albumDescription"));
        String albumType = StringUtil.null2Str(request.getParameter("albumType"));
        String albumFile = StringUtil.null2Str(request.getParameter("albumFile"));
        Map<String,Object> result = new HashMap<>();
        Album album = new Album();
        album.setAlbumName(albumName);
        album.setAlbumDescription(albumDescription);
        album.setAlbumType(albumType);
        album.setCover(albumFile);
        Album saveAlbum = albumRepository.saveAndFlush(album);
        result.put("album",saveAlbum);
        return result;
    }
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request,@RequestParam("file") MultipartFile tmpFile){
        Map<String,Object> map = new HashMap<>();
        String targetDirectory = request.getSession().getServletContext().getRealPath("/album");
        String tmpFileName = tmpFile.getOriginalFilename();
        File target = new File(targetDirectory, tmpFileName);
        System.out.println(target);
        try {
            // 保存文件
            FileUtils.copyInputStreamToFile(tmpFile.getInputStream(), target);
        } catch (Exception e) {
            // e.printStackTrace();
            map.put("err",e);
            return map;
        }
        map.put("file_path","/album/" + tmpFileName);
        return map;
    }

    @RequestMapping(value = "deleteAlbum",method = {RequestMethod.POST})
    @ResponseBody
    public Integer deleteAlbum(HttpServletRequest request){
        String albumId = StringUtil.null2Str(request.getParameter("albumId"));
        try {
            albumRepository.delete(Integer.parseInt(albumId));
        } catch (NumberFormatException e) {
            return 1;
        }
        return 0;
    }
    @RequestMapping(value = "showAlbums",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> showAlbums(){
        Map<String,Object> result = new HashMap<>();
        List<Album> albumList = albumRepository.findAll();
        result.put("albumList",albumList);
        return result;
    }







    @RequestMapping(value = "showPicture",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> showPicture(HttpServletRequest request){
        String albumId = StringUtil.null2Str(request.getParameter("albumId"));
        Map<String,Object> result = new HashMap<>();
        List<Picture> pictureList = pictureService.findByAlbumId(Integer.parseInt(albumId));
        result.put("pictureList",pictureList);
        return result;
    }
    @RequestMapping(value = "setCover",method = RequestMethod.POST)
    @ResponseBody
    public void setCover(HttpServletRequest request){
        String img = StringUtil.null2Str(request.getParameter("img"));
        String albumId = StringUtil.null2Str(request.getParameter("albumId"));
        Album album = albumRepository.findOne(Integer.parseInt(albumId));
        album.setCover(img);
        albumRepository.saveAndFlush(album);
    }
}
