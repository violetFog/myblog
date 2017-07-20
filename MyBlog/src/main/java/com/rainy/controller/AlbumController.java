package com.rainy.controller;

import com.rainy.model.Album;
import com.rainy.repository.AlbumRepository;
import com.rainy.utils.StringUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping(value = "createAlbum",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> createAlbum(HttpServletRequest request){
        String albumName = StringUtil.null2Str(request.getParameter("albumName"));
        String albumDescription = StringUtil.null2Str(request.getParameter("albumDescription"));
        String albumType = StringUtil.null2Str(request.getParameter("albumType"));
        String albumFile = StringUtil.null2Str(request.getParameter("albumFile"));
        String src = StringUtil.null2Str(request.getParameter("src"));
        Map<String,Object> result = new HashMap<>();
        Album album = new Album();
        album.setAlbumName(albumName);
        album.setAlbumDescription(albumDescription);
        album.setAlbumType(albumType);
        if(!StringUtil.isNullStr(albumFile)){
            album.setCover(albumFile);
        }
        album.setSrc(src);
        Album saveAlbum = albumRepository.saveAndFlush(album);
        result.put("album",saveAlbum);
        return result;
    }
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> upload(HttpServletRequest request,@RequestParam("file") MultipartFile tmpFile){
        Map<String,Object> map = new HashMap<>();
        String targetDirectory = request.getSession().getServletContext().getRealPath("/album");
        String pic_time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String tmpFileName = tmpFile.getOriginalFilename();
        String targetFilName=tmpFileName.length()+pic_time+tmpFileName.substring(tmpFileName.length() - 4);
        File target = new File(targetDirectory, targetFilName);
        String imgFile=targetDirectory+File.separator+targetFilName;
        String newFile=targetDirectory+File.separator+"small"+File.separator+targetFilName;
        File imageFile = new File(targetDirectory+File.separator+"small");
        if (!imageFile.exists()) {
            imageFile.mkdirs();

        }

        System.out.println(imgFile);
        try {
            // 保存文件
            FileUtils.copyInputStreamToFile(tmpFile.getInputStream(), target);
        } catch (Exception e) {
            // e.printStackTrace();
            map.put("err",e);
            return map;
        }
        int[] imgWidth = getImgWidth(target);
        if(imgWidth[0]>160||imgWidth[1]>160){
            reduceImg(imgFile, newFile, 160, 160, null);
        }else{
            try {
                FileUtils.copyInputStreamToFile(tmpFile.getInputStream(), new File(newFile));
            } catch (Exception e) {
                map.put("err",e);
                return map;
            }
        }

        map.put("file_path", "/album/small/" + targetFilName);
        map.put("src","/album/" + targetFilName);
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
        result.put("albumList", albumList);
        return result;
    }

    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream out = new FileOutputStream(imgdist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
