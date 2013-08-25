package com.kelepi.biz.manager;

import com.alibaba.citrus.util.io.StreamUtil;
import com.kelepi.common.bean.Result;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

@Component("imageManager")
public class ImageManager extends BaseManager {

    Logger logger = LoggerFactory.getLogger(ImageManager.class);

    public static final String DOT = ".";
    public static final String PRE = "_";

    private String faceimagePath = "/statics/images/faceimage";

    private String jokeimagePath = "/statics/images/jokeimage";

    private String materialimagePath = "/statics/images/materialimage";

    @Resource
    private UpYunManager upYunManager;

    /**
     * 
     *
     *
     * @param fileItem
     * @param request
     * @param dirPath
     * @return
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public String saveToFile(FileItem fileItem, HttpServletRequest request, String dirPath) {

        String fildPath = "";

        try {
        String basePath = request.getSession().getServletContext().getRealPath(dirPath);
        File base = new File(basePath);
        
        String picName = RandomStringUtils.randomAlphanumeric(30) + DOT + getExtension(fileItem.getName());

        File picFile = new File(base, picName);
        StreamUtil.io(fileItem.getInputStream(), new FileOutputStream(picFile), true, true);




        if (picName != null) {
            fildPath = dirPath + "/" + picName;
        }

        //保存到又拍
        String localPath = request.getSession().getServletContext().getRealPath(fildPath);
        upYunManager.upload(localPath, fildPath);

        } catch (Exception e) {
             logger.warn("保存文件出错", e);
        }
        return fildPath;
    }

    public String saveFile(FileItem fileItem, HttpServletRequest request, String dirPath) {

        String fildPath = "";

        try {
            String basePath = request.getSession().getServletContext().getRealPath(dirPath);
            File base = new File(basePath);

            String picName = RandomStringUtils.randomAlphanumeric(30) + DOT + getExtension(fileItem.getName());

            File picFile = new File(base, picName);
            StreamUtil.io(fileItem.getInputStream(), new FileOutputStream(picFile), true, true);

            if (picName != null) {
                fildPath = dirPath + "/" + picName;
            }

        } catch (Exception e) {
            logger.warn("保存文件出错", e);
        }
        return fildPath;
    }

    public String saveFaceImageUrl(HttpServletRequest request, FileItem fileItem) {
        return saveToFile(fileItem, request, faceimagePath);
    }

    public String saveJokeImageUrl(HttpServletRequest request, FileItem fileItem) {
        return  saveToFile(fileItem, request, jokeimagePath);
    }

    public Result saveMaterialImageUrl(HttpServletRequest request, FileItem fileItem) {
        Result result = createResult(true);

        String filePath = saveFile(fileItem, request, materialimagePath);

        String localPath =  request.getSession().getServletContext().getRealPath(filePath);

        //压缩图片
/*        boolean creResult = false;
        try {
            creResult = JmagickHelps.createThumbnail(localPath, localPath, 440);
        } catch (MagickException e) {
            e.printStackTrace();
        }

        if (!creResult) {
            result.setSuccess(false);
            result.setModel("message", "图片宽度必须大于440px！");
            return result;
        }*/

        //上传到upyun
        upYunManager.upload(localPath, filePath);

        result.setModel("url", filePath);

        return result;
    }

    
    private String getExtension(String name) {
        int index = name.lastIndexOf(DOT);

        return name.substring(index + 1);
    }
}
