package com.kelepi.biz.manager;

import com.alibaba.citrus.util.io.StreamUtil;
import com.kelepi.common.bean.Result;
import com.kelepi.dal.constants.JokeConstants;
import com.kelepi.dal.dao.UserDAO;
import com.kelepi.dal.dataobject.UserDO;
import com.kelepi.util.FileUtil;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.RandomStringUtils;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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

    @Resource
    private UserDAO userDAO;

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
        boolean creResult = false;
        try {
            creResult = createThumbnail(localPath, localPath, 440);
        } catch (MagickException e) {
            e.printStackTrace();
        }

        if (!creResult) {
            result.setSuccess(false);
            result.setModel("msg", "图片宽度必须大于440px！");
            return result;
        }

        //上传到upyun
        upYunManager.upload(localPath, filePath);

        result.setModel("url", filePath);

        return result;
    }

    public boolean createThumbnail(String filePath, String toPath, int widethTo) throws MagickException{
        try{

            BufferedImage sourceImg = ImageIO.read(new FileInputStream(filePath));
            int wideth = sourceImg.getWidth();
            int height = sourceImg.getHeight();

            if (wideth < widethTo) {
                return false;
            }

            height = widethTo * height / wideth;
            wideth = widethTo;

            IMOperation op = new IMOperation();
            op.addImage(filePath);

            op.resize(wideth, height);
            op.addImage(toPath);

            ConvertCmd convert = new ConvertCmd();

            //linux下不要设置此值，不然会报错
            if (JokeConstants.IMAGE_MAGICK_PATH != null) {
                convert.setSearchPath(JokeConstants.IMAGE_MAGICK_PATH);
            }

            convert.run(op);

        }catch (Exception e) {
            logger.warn("createThumbnail error!", e);
        }

        return true;
    }

    public String generatorTitleImage(String baseHome, long id) {

        UserDO userDO =  userDAO.get(id);
        if (userDO == null) {
            return JokeConstants.JOKE_HEAD_IMAGE;
        }

        if (userDO.getJokeHeadImageUrl() != null) {
            return baseHome + userDO.getJokeHeadImageUrl();
        }

        String titleImage = "";
        try {
            String textUserName = getRodomFileName("txt");
            String textUserNamePath = JokeConstants.TEM_DIR +   textUserName;
            FileUtil.string2File(userDO.getNickName(), textUserNamePath);

            String textUserUrl = getRodomFileName("txt");
            String textUserUrlPath = JokeConstants.TEM_DIR +   textUserUrl;

            String userUrl = "kelepi.com/u/" + userDO.getId();
            FileUtil.string2File(userUrl, textUserUrlPath);

            IMOperation op = new IMOperation();
            op.addImage(JokeConstants.JOKE_HEAD_IMAGE);
            op.font(JokeConstants.MSYH).pointsize(23).fill("white").stroke("none").annotate(0, 0, 62, 32, "@"+textUserNamePath);
            op.font(JokeConstants.MSYH).pointsize(12).fill("#cccccc").stroke("none").annotate(0, 0, 62, 50, "@"+textUserUrlPath);
            String newTitleImage = generatorFileName("jpg");
            String newHeadImage =  baseHome + faceimagePath + "/" + newTitleImage;

            op.addImage(newHeadImage);

            ConvertCmd convert = new ConvertCmd();

            //linux下不要设置此值，不然会报错
            if (JokeConstants.IMAGE_MAGICK_PATH != null) {
                convert.setSearchPath(JokeConstants.IMAGE_MAGICK_PATH);
            }

            convert.run(op);


            String faceImageUrl = userDO.getFaceImageUrl();
            if (faceImageUrl == null) {
                faceImageUrl = JokeConstants.DEFATUL_USER_FACE_IMG;
            } else if (!faceImageUrl.startsWith("http")) {
                faceImageUrl = baseHome + faceImageUrl;
            } else {
                String faceLoacName = getRodomFileName("jpg");
                String faceLoacNamePath = JokeConstants.TEM_DIR +   faceLoacName;
                FileUtil.getUrlFile(faceImageUrl, faceLoacNamePath);

                faceImageUrl = faceLoacNamePath;
            }
            op = new IMOperation();
            op.addImage(faceImageUrl);

            op.resize(50, 50);

            String resizeFace = getRodomFileName("jpg");
            String resizeFacePath = JokeConstants.TEM_DIR +   textUserUrl;
            op.addImage(resizeFacePath);

            convert = new ConvertCmd();

            //linux下不要设置此值，不然会报错
            if (JokeConstants.IMAGE_MAGICK_PATH != null) {
                convert.setSearchPath(JokeConstants.IMAGE_MAGICK_PATH);
            }

            convert.run(op);

            op = new IMOperation();
            op.addImage(resizeFacePath);
            op.geometry(null, null, 3, 3);

            op.addImage(newHeadImage);
            op.addImage(newHeadImage);

            CompositeCmd compositeCmd = new CompositeCmd();
            compositeCmd.run(op);

            titleImage = newHeadImage;

            userDAO.updateJokeHeadImageUrl(faceimagePath + "/" + newTitleImage, id);
        }  catch (Exception e) {
            logger.warn("generatorTitleImage error!", e);
        }


        return titleImage;
    }

    private String getRodomFileName(String fileExtName) {
        String str = "f_" + System.nanoTime();

        return str + "." + fileExtName;
    }

    private String generatorFileName(String fileExtName) {
        String fileName = RandomStringUtils.randomAlphanumeric(30) + DOT + fileExtName;

        return fileName;
    }

    
    private String getExtension(String name) {
        int index = name.lastIndexOf(DOT);

        return name.substring(index + 1);
    }
}
