package com.kelepi.biz.manager;

import magick.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JmagickHelps {


    static{
        //不能漏掉这个，不然jmagick.jar的路径找不到
        System.setProperty("jmagick.systemclassloader","no");
    }

    /**
     * 压缩图片
     * @param filePath 源文件路径
     * @param toPath   缩略图路径
     */
    public static boolean createThumbnail(String filePath, String toPath, int widethTo) throws MagickException{
        ImageInfo info = null;
        MagickImage image = null;
        Dimension imageDim = null;
        MagickImage scaled = null;
        try{
            info = new ImageInfo(filePath);
            image = new MagickImage(info);
            imageDim = image.getDimension();
            int wideth = imageDim.width;
            int height = imageDim.height;

            if (wideth < widethTo) {
                return false;
            }

            height = widethTo * height / wideth;
            wideth = widethTo;
            scaled = image.scaleImage(wideth, height);//小图片文件的大小.
            scaled.setFileName(toPath);
            scaled.writeImage(info);
        }finally{
            if(scaled != null){
                scaled.destroyImages();
            }

            if (image != null) {
                image.destroyImages();
            }
        }

        return true;
    }

    /**
     * 水印(图片logo)
     * @param filePath  源文件路径
     * @param toImg     修改图路径
     * @param logoPath  logo图路径
     * @throws MagickException
     */
    public static void initLogoImg(String filePath, String toImg, String logoPath) throws MagickException {
        ImageInfo info = new ImageInfo();
        MagickImage fImage = null;
        MagickImage sImage = null;
        MagickImage fLogo = null;
        MagickImage sLogo = null;
        Dimension imageDim = null;
        Dimension logoDim = null;
        try {
            fImage = new MagickImage(new ImageInfo(filePath));
            imageDim = fImage.getDimension();
            int width = imageDim.width;
            int height = imageDim.height;
            if (width > 660) {
                height = 660 * height / width;
                width = 660;
            }
            sImage = fImage.scaleImage(width, height);

            fLogo = new MagickImage(new ImageInfo(logoPath));
            logoDim = fLogo.getDimension();
            int lw = width / 8;
            int lh = logoDim.height * lw / logoDim.width;
            sLogo = fLogo.scaleImage(lw, lh);

            sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,  width-(lw + lh/10), height-(lh + lh/10));
            sImage.setFileName(toImg);
            sImage.writeImage(info);
        } finally {
            if(sImage != null){
                sImage.destroyImages();
            }
        }
    }

    /**
     * 水印(文字)
     &nbsp;* @param filePath 源文件路径
     * @param toImg    修改图路径
     * @param text     名字(文字内容自己随意)
     * @throws MagickException
     */
    public static void initTextToImg(String filePath, String toImg,  String text) throws MagickException{
        ImageInfo info = new ImageInfo(filePath);
        if (filePath.toUpperCase().endsWith("JPG") || filePath.toUpperCase().endsWith("JPEG")) {
            info.setCompression(CompressionType.JPEGCompression); //压缩类别为JPEG格式
            info.setPreviewType(PreviewType.JPEGPreview); //预览格式为JPEG格式
            info.setQuality(95);
        }
        MagickImage aImage = new MagickImage(info);
        Dimension imageDim = aImage.getDimension();
        int wideth = imageDim.width;
        int height = imageDim.height;
        if (wideth > 660) {
            height = 660 * height / wideth;
            wideth = 660;
        }
        int a = 0;
        int b = 0;
        String[] as = text.split("");
        for (String string : as) {
            if(string.matches("[\u4E00-\u9FA5]")){
                a++;
            }
            if(string.matches("[a-zA-Z0-9]")){
                b++;
            }
        }
        int tl = a*12 + b*6 + 300;
        MagickImage scaled = aImage.scaleImage(wideth, height);
        if(wideth > tl && height > 5){
            DrawInfo aInfo = new DrawInfo(info);
            aInfo.setFill(PixelPacket.queryColorDatabase("white"));
           // aInfo.setUnderColor(new PixelPacket(0,0,0,100));
            aInfo.setBorderColor(PixelPacket.queryColorDatabase("black"));

            aInfo.setPointsize(30);

            //解决中文乱码问题,自己可以去随意定义个自己喜欢字体，我在这用的微软雅黑
            String fontPath = "C:/WINDOWS/Fonts/MSYH.TTF";
            //                String fontPath = "/usr/maindata/MSYH.TTF";
            aInfo.setFont(fontPath);
            aInfo.setTextAntialias(true);
            aInfo.setOpacity(0);
            aInfo.setText("　" + text + "于　" + "　可乐皮");
            aInfo.setGeometry("+" + (wideth-tl) + "+" + (height-5));
            scaled.annotateImage(aInfo);
        }
        scaled.setFileName(toImg);
        scaled.writeImage(info);
        scaled.destroyImages();
    }


/*    *//**
     * 切图
     * @throws MagickException
     *//*
    public static void cutImg(String imgPath, String toPath, int w, int h, int x, int y) throws MagickException {
        ImageInfo infoS = null;
        MagickImage image = null;
        MagickImage cropped = null;
        Rectangle rect = null;
        try {
            infos = new ImageInfo(imgpath);
            image = new magickimage(infos);
            rect = new rectangle(x, y, w, h);
            cropped = image.cropimage(rect);
            cropped.setfilename(topath);
            cropped.writeimage(infos);

        } finally {
            if (cropped != null) {
                cropped.destroyImages();
            }
        }
    }*/
    public static void main(String[] args) {
        try {
            initTextToImg("K:\\img\\src.jpg", "K:\\img\\src.jpg", "李卫林");
        } catch (MagickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
 