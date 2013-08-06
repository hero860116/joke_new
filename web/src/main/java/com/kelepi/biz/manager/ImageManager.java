package com.kelepi.biz.manager;

import com.alibaba.citrus.util.io.StreamUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component("imageManager")
public class ImageManager {

    public static final String DOT = ".";
    public static final String PRE = "_";

    private String faceimagePath = "/statics/images/faceimage";

    /**
     * 
     * @param fileItem
     * @return
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public String saveToFile(FileItem fileItem, String basePath) throws FileNotFoundException, IOException {
        File base = new File(basePath);
        
        String picName = RandomStringUtils.randomAlphanumeric(30) + DOT + getExtension(fileItem.getName());

        File picFile = new File(base, picName);
        StreamUtil.io(fileItem.getInputStream(), new FileOutputStream(picFile), true, true);

        return picName;
    }

    public String saveFaceImageUrl(HttpServletRequest request, FileItem fileItem) {
        String basePath = request.getSession().getServletContext().getRealPath(faceimagePath);

        String picUrl = null;
        try {
        	String fileName = saveToFile(fileItem, basePath);
        	if (fileName != null) {
        		picUrl = faceimagePath + "/" + fileName;
        	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picUrl;
    }

    
    private String getExtension(String name) {
        int index = name.lastIndexOf(DOT);

        return name.substring(index + 1);
    }
}
