package com.kelepi.biz.manager;

import magick.*;

import java.awt.*;
import java.io.File;
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
            //String fontPath = "C:/WINDOWS/Fonts/MSYH.TTF";
            //                String fontPath = "/usr/maindata/MSYH.TTF";
            //aInfo.setFont(fontPath);
            //aInfo.setfon
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

    public String cut()
    {
        String fileName = "";//图片路径+图片名称
        String txt_width = "";//缩放后宽
        String txt_height = "";//缩放后高
        String txt_top = "";//x坐标
        String txt_left = "";//y坐标
        String txt_DropWidth = "";//剪切宽
        String txt_DropHeight = "";//剪切高
        String filePath = "";


        String cutFilePath = "";
        File fileList = new File("");
        if(!fileList.exists())
        {
            fileList.mkdirs();
        }
        try {
            System.setProperty("jmagick.systemclassloader","no");
            ImageInfo info = new ImageInfo(filePath);

            MagickImage image = new MagickImage(info);
            MagickImage cropped = null;
            MagickImage scaleImg = image.scaleImage(Integer.valueOf(txt_width), Integer.valueOf(txt_height));//缩放图片
            Rectangle rect = new Rectangle (Integer.valueOf(txt_left),Integer.valueOf(txt_top),Integer.valueOf(txt_DropWidth),Integer.valueOf(txt_DropHeight));
            cropped = scaleImg.cropImage(rect);//剪切图片
            cropped.setFileName(cutFilePath);
            cropped .writeImage(info);

        } catch (MagickException e) {
            e.printStackTrace();
        }
        return "cut";
    }

    public String watermark()
    {
        String fileName = "";//图片路径+名字
        String logoFileName = "";//水印图片历经+名字
        Integer txt_width = 0;//缩放后宽
        Integer txt_height = 0;//缩放后高
        Integer txt_top = 0;//x坐标
        Integer txt_left = 0;//y坐标
        Integer txt_DropWidth = 0;//剪切宽度
        Integer txt_DropHeight = 0;//剪切高度
        String filePath = "";
        String logoImagPath = "";
        String flag = "";

        String cutFilePath = "";
        File fileList = new File("");
        if(!fileList.exists())
        {
            fileList.mkdirs();
        }
        try {
            System.setProperty("jmagick.systemclassloader","no");
            ImageInfo info = new ImageInfo(filePath);

            MagickImage image = new MagickImage(info);
            MagickImage cropped = null;
            MagickImage fLogo = null;
            MagickImage sLogo = null;
            Dimension logoDim = null;

            MagickImage scaleImg = image.scaleImage(txt_width, txt_height);
            Rectangle rect = new Rectangle (txt_left,txt_top,txt_DropWidth,txt_DropHeight);
            cropped = scaleImg.cropImage(rect);
            fLogo = new MagickImage(new ImageInfo(logoImagPath));
            logoDim = fLogo.getDimension();
            int lw = txt_DropWidth / 4;
            int lh = logoDim.height * lw / logoDim.width;
            sLogo = fLogo.scaleImage(lw, lh);
            //水印出现在左上方
            if(flag.equals("leftTop"))
            {
                cropped.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
                        lh / 10, lh / 10);
            }
            else if(flag.equals("rightTop"))
            {
                cropped.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
                        txt_DropWidth - (lw + lh / 10), lh / 10);
            }
            else if(flag.equals("middle"))
            {
                cropped.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
                        (txt_DropWidth - lw)/2, (txt_DropHeight-lh)/2);
            }
            else if(flag.equals("leftBottom"))
            {
                cropped.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
                        lh / 10, txt_DropHeight - (lh + lh / 10));
            }
            else
            {
                cropped.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
                        txt_DropWidth - (lw + lh / 10), txt_DropHeight - (lh + lh / 10));
            }
            cropped.setFileName(cutFilePath);
            cropped.writeImage(info);

        } catch (MagickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "cut";
    }

    //锐化
    public void sharpen(String filePath,String savePath)
    {
        ImageInfo info = null;
        MagickImage image = null;
        MagickImage sharpened = null;
        try {
            info = new ImageInfo(filePath);
            image = new MagickImage(info);
            sharpened = image.sharpenImage(1.0, 5.0);
            sharpened.setFileName(savePath);

            sharpened.writeImage(info);
        } catch (MagickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (image != null) {
                image.destroyImages();
            }
        }
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
            initTextToImg("K:\\img\\src.jpg", "K:\\img\\src1.jpg", "李卫林");
        } catch (MagickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
 