package com.kelepi.biz.manager;

import com.alibaba.citrus.util.io.StreamUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

@Component("imageManager")
public class ImageManager {

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

    public String saveFaceImageUrl(HttpServletRequest request, FileItem fileItem) {
        return saveToFile(fileItem, request, faceimagePath);
    }

    public String saveJokeImageUrl(HttpServletRequest request, FileItem fileItem) {
        return  saveToFile(fileItem, request, jokeimagePath);
    }

    public String saveMaterialImageUrl(HttpServletRequest request, FileItem fileItem) {
        return  saveToFile(fileItem, request, materialimagePath);
    }

    
    private String getExtension(String name) {
        int index = name.lastIndexOf(DOT);

        return name.substring(index + 1);
    }
}
