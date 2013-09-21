package com.kelepi.util;

/**
 * User: liWeiLin
 * Date: 13-9-20 下午3:27
 */
public class PathUtil {
    public static String getBaseDir(String path) {
        int index = path.lastIndexOf("/");
        if (index == -1) {
            index = path.lastIndexOf("\\");
        }

        if (index == -1) {
            return null;
        }

        String baseDir = path.substring(0, index);
        return baseDir;
    }

    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        if (index == -1) {
            index = path.lastIndexOf("\\");
        }

        if (index == -1) {
            return path;
        }

        String fileName = path.substring(index+1);

        return  fileName;
    }

    public static String getNakedPath(String path) {
        int index = path.indexOf(":");
        if (index == -1) {
            return path;
        }

        int index1 = path.indexOf("/",index + 3);
        if (index == -1) {
            return null;
        }

        String fileName = path.substring(index1);

        return  fileName;
    }

    public static String getExtension(String name) {
        int index = name.lastIndexOf(".");

        return name.substring(index + 1);
    }
}
