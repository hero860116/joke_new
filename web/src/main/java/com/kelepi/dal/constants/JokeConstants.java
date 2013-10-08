package com.kelepi.dal.constants;

import java.util.Arrays;
import java.util.List;

public class JokeConstants {

    public static final int FRONT_PAGE_SZIE = 10;

 /*   public static final String TEM_DIR = "/home/joke/joke/tmp/";
    public static final String MSYHBD = "/home/joke/joke/msyhbd.ttf";
    public static final String MSYH = "/home/joke/joke/msyh.ttf";
    public static final String JOKE_HEAD_IMAGE = "/home/joke/joke/head.jpg";
    public static final String JOKE_FOOTER_IMAGE = "/home/joke/joke/footer.jpg";
    public static final String DEFATUL_USER_FACE_IMG = "/home/joke/joke/defaultuserface.jpg";
    public static final String IMAGE_MAGICK_PATH = null*/;

   public static final String TEM_DIR = "G:/img/tmp/";
   public static final String MSYHBD = "G:/msyhbd.ttf";
    public static final String MSYH = "G:/msyh.ttf";
    public static final String JOKE_HEAD_IMAGE = "G:/img/head.jpg";
    public static final String JOKE_FOOTER_IMAGE = "G:/img/footer.jpg";
    public static final String DEFATUL_USER_FACE_IMG = "";
    public static final String IMAGE_MAGICK_PATH = "G:/Program Files/ImageMagick-6.3.9-Q8";

    /**
     * 创建常量对象
     * @param <T>
     * @param value
     * @param message
     * @return
     */
    private static <T> NumberConstants<T> createNumber(T value, String message) {
        NumberConstants<T> number = new NumberConstants<T>();
        number.setMessage(message);
        number.setValue(value);
        return number;
    }
}
