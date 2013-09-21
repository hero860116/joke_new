package com.kelepi.biz.manager;

import com.kelepi.dal.constants.JokeConstants;
import com.kelepi.util.FileUtil;
import magick.ImageInfo;
import magick.MagickImage;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImagesUtil {

    private static Logger logger = LoggerFactory.getLogger(ImagesUtil.class);
    /**
     * ImageMagick的路径
     */
    public static String imageMagickPath = null;

    static{
        //不能漏掉这个，不然jmagick.jar的路径找不到
        System.setProperty("jmagick.systemclassloader","no");
    }

    static{
        /**
         * 获取ImageMagick的路径
         */
        //linux下不要设置此值，不然会报错
        imageMagickPath = "G:\\Program Files\\ImageMagick-6.3.9-Q8";
    }

     
    /**
     * 根据坐标裁剪图片
     *
     * @param srcPath   要裁剪图片的路径
     * @param newPath   裁剪图片后的路径
     * @param x   起始横坐标
     * @param y   起始纵坐标
     * @param x1  结束横坐标
     * @param y1  结束纵坐标
     */
    public static void cutImage(String srcPath, String newPath, int x, int y, int x1,
            int y1)  throws Exception {
        int width = x1 - x;
        int height = y1 - y;
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
         
        /**
         * width：裁剪的宽度
         * height：裁剪的高度
         * x：裁剪的横坐标
         * y：裁剪的挫坐标
         */
        op.crop(width, height, x, y);
         
        op.addImage(newPath);
         
        ConvertCmd convert = new ConvertCmd();
         
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
         
 
        convert.run(op);
    }
     
    /**
     * 根据尺寸缩放图片
     * @param width  缩放后的图片宽度
     * @param height  缩放后的图片高度
     * @param srcPath   源图片路径
     * @param newPath   缩放后图片的路径
     */
    public static void zoomImage(Integer width, Integer height, String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
         
        op.resize(width, height);
        op.addImage(newPath);
         
        ConvertCmd convert = new ConvertCmd();
         
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
         
        convert.run(op);
    }

    public static void appendImgs(List<String> imgPaths, String toPath)  {
        try {
            long t = System.currentTimeMillis();
            IMOperation op = new IMOperation();
            for (String imgPath : imgPaths) {
                op.addImage(imgPath);
            }

            op.append();

            op.addImage(toPath);

            ConvertCmd convert = new ConvertCmd();

            //linux下不要设置此值，不然会报错
            //convert.setSearchPath(imageMagickPath);

            convert.run(op);

            long e = System.currentTimeMillis();
            System.out.println(e-t);
        } catch(Exception e) {
            logger.warn("appendImgs error", e);
        }

    }
     
     
    /**
     * 给图片加水印
     * @param srcPath   源图片路径
     */
    public static void addImgText(String srcPath, String toPath) throws Exception {
        IMOperation op = new IMOperation();
        op.size(320, 100);
        op.addImage("xc:none");
        op.font(JokeConstants.MSYH).pointsize(20).stroke("black").strokewidth(4).annotate(0,0,20,65,"@K:/t.txt").blur(0d,8d).fill("white").stroke("none").annotate(0, 0, 20, 65, "@K:/t.txt");


        ConvertCmd convert = new ConvertCmd();

        op.addImage("K:\\img\\t.png");
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);

        convert.run(op);


        op = new IMOperation();


        op.addImage("K:\\img\\t.png");
        op.geometry(320,100, 50, 600);
        op.addImage(srcPath);
        op.addImage(toPath);

        CompositeCmd compositeCmd = new CompositeCmd();
        compositeCmd.run(op);
    }

    public static void addText(String srcPath, String toPath, String text) {
        logger.info("************* addText in");
        int colHeight = 25;
        int fromBotton = 25;
        int fontSize = 20;

        int wideth = 0;
        int height = 0;
        ImageInfo info = null;
        InputStream is = null;
        try {
            info = new ImageInfo(srcPath);
            MagickImage aImage = new MagickImage(info);
            Dimension imageDim = aImage.getDimension();

            wideth = imageDim.width;
            height = imageDim.height;


        } catch (Exception e) {
            logger.warn("inageInfo", e);
        }

        String[] texts = getTexts(text, 20);

        //处理每一个
        String srcPPP =  srcPath;
        for (int i = 0; i < texts.length; i++) {
            String subText = texts[i];
            int hgt =  height - fromBotton - colHeight * (texts.length - i);

            srcPPP = addText(srcPPP, toPath, subText, getLeftForCenter((20 * subText.length() + 10), wideth), hgt);
        }

        logger.info("************* addText out");
    }

    /**
     * 给图片增加文字
     * @param srcPath   源图片路径
     */
    public static String addText(String srcPath, String toPath, String text, int x, int y) {
        logger.info("************* sub addText in , srcPath:{}, text:{}", srcPath, text);

        long s = System.currentTimeMillis();
        try {

        int pointSize = 20;
        int labenHeight = 26;
        int labelWidth = 20 * text.length() + 10;

        //将文本写入临时文件中
        String textFileName = getRodomFileName("txt");
        String textPath = JokeConstants.TEM_DIR +   textFileName;
        FileUtil.string2File(text, textPath);


        IMOperation op = new IMOperation();

        op.size(labelWidth, labenHeight);

        op.addImage("xc:none");
        op.font(JokeConstants.MSYH).pointsize(pointSize).stroke("black").strokewidth(4).annotate(0,0,5,20,"@"+textPath).blur(0d,8d).fill("white").stroke("none").annotate(0, 0, 5, 20, "@"+textPath);


        ConvertCmd convert = new ConvertCmd();

        String pngLabelFileName = getRodomFileName("png");
        String pngLabelPath = JokeConstants.TEM_DIR  + pngLabelFileName;
        op.addImage(pngLabelPath);

        //linux下不要设置此值，不然会报错
        //convert.setSearchPath(imageMagickPath);

        logger.info("************* new png , pngLabelPath:{}", pngLabelPath);
        convert.run(op);

        op = new IMOperation();
            logger.info("************* new png complete, pngLabelPath:{}", pngLabelPath);

        op.addImage(pngLabelPath);
        op.geometry(labelWidth,labenHeight, x, y);
        op.addImage(srcPath);
        op.addImage(toPath);

        CompositeCmd compositeCmd = new CompositeCmd();
        compositeCmd.run(op);

        }catch (Exception e) {
             logger.warn("addText", e);
        }

        long e = System.currentTimeMillis();
        System.out.println(e-s);

        logger.info("************* sub addText out , toPath:{}", toPath);
        return toPath;
    }

    private static int getLeftForCenter(int contentSize, int contaterSize) {
        return (contaterSize - contentSize) / 2;
    }

    private static String[] getTexts(String text, int size) {
        int length = text.length();
        int arrSize = (length + (size - 1))/size;

        String[] arr = new String[arrSize];
        for (int i = 0; i < arrSize; i++){
            arr[i] = text.substring(i * size, (i+1) * size > length ? length : (i+1) * size);
        }

        return arr;
    }

    private static String getRodomFileName(String fileExtName) {
        String str = "f_" + System.nanoTime();

        return str + "." + fileExtName;
    }


/*        ConvertCmd convert = new ConvertCmd();

        op.addImage(toPath);
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
 
        convert.run(op);
    }*/
     
     
    public static void main(String[] args) throws Exception{
        //cutImage("K:\\img\\src.jpg", "K:\\img\\src_cut.jpg", 50, 50, 900, 1600);
        //zoomImage(450 ,800, "K:\\img\\src.jpg", "K:\\img\\src_zoom.jpg");
        //addImgText("K:\\img\\ttt.jpg", "K:\\img\\ttt_cut4.jpg");
        //addText("K:\\img\\ttt.jpg", "K:\\img\\ttt_cut5.jpg", "李卫林的终极测试", 100, 700);
        //addText("K:\\img\\ttt.jpg", "K:\\img\\ttt_cut6.jpg", "李卫林的终极测试李卫林的终极测试李卫林的终极测试李卫林的终极测试李卫林的终极测试林的终极测试李卫林的终极测试李卫林的终极测试李卫林的终极测试李卫");

        List<String> paths = new ArrayList<String>();
        paths.add("K:\\img\\src_440.jpg");
       // paths.add("K:\\img\\src2.jpg");
        paths.add( "K:\\img\\ttt.jpg");
        paths.add("K:\\img\\ttt_cut5.jpg");
        paths.add("K:\\img\\ttt_cut6.jpg");

        appendImgs(paths, "K:\\img\\test.jpg");
    }
}

