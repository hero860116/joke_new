package com.kelepi.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class FileUtil {
    private static final Log log = LogFactory.getLog(FileUtil.class);

    /**
     * 复制文件或者目录,复制前后文件完全一样。
     *
     * @param resFilePath 源文件路径
     * @param distFolder  目标文件夹
     * @IOException 当操作发生异常时抛出
     */
    public static void copyFile(String resFilePath, String distFolder) throws IOException {
        File resFile = new File(resFilePath);
        File distFile = new File(distFolder);
        if (resFile.isDirectory()) {
            FileUtils.copyDirectoryToDirectory(resFile, distFile);
        } else if (resFile.isFile()) {
            FileUtils.copyFileToDirectory(resFile, distFile, true);
        }
    }

    /**
     * 删除一个文件或者目录
     *
     * @param targetPath 文件或者目录路径
     * @IOException 当操作发生异常时抛出
     */
    public static void deleteFile(String targetPath) throws IOException {
        File targetFile = new File(targetPath);
        if (targetFile.isDirectory()) {
            FileUtils.deleteDirectory(targetFile);
        } else if (targetFile.isFile()) {
            targetFile.delete();
        }
    }

    /**
     * 移动文件或者目录,移动前后文件完全一样,如果目标文件夹不存在则创建。
     *
     * @param resFilePath 源文件路径
     * @param distFolder  目标文件夹
     * @IOException 当操作发生异常时抛出
     */
    public static void moveFile(String resFilePath, String distFolder) throws IOException {
        File resFile = new File(resFilePath);
        File distFile = new File(distFolder);
        if (resFile.isDirectory()) {
            FileUtils.moveDirectoryToDirectory(resFile, distFile, true);
        } else if (resFile.isFile()) {
            FileUtils.moveFileToDirectory(resFile, distFile, true);
        }
    }


    /**
     * 读取文件或者目录的大小
     *
     * @param distFilePath 目标文件或者文件夹
     * @return 文件或者目录的大小，如果获取失败，则返回-1
     */
    public static long genFileSize(String distFilePath) {
        File distFile = new File(distFilePath);
        if (distFile.isFile()) {
            return distFile.length();
        } else if (distFile.isDirectory()) {
            return FileUtils.sizeOfDirectory(distFile);
        }
        return -1L;
    }

    /**
     * 判断一个文件是否存在
     *
     * @param filePath 文件路径
     * @return 存在返回true，否则返回false
     */
    public static boolean isExist(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     *
     * @param res      原字符串
     * @param filePath 文件路径
     * @return 成功标记
     */
    public static boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
/*                        File distFile = new File(filePath);
                        if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
                        bufferedReader = new BufferedReader(new StringReader(res));
                        bufferedWriter = new BufferedWriter(new FileWriter(distFile));
                        char buf[] = new char[1024];         //字符缓冲区
                        int len;
                        while ((len = bufferedReader.read(buf)) != -1) {
                                bufferedWriter.write(buf, 0, len);
                        }
                        bufferedWriter.flush();
                        bufferedReader.close();
                        bufferedWriter.close();*/

            FileOutputStream fos = new FileOutputStream(filePath);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(res);
            out.close();
            fos.close();
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean getUrlFile(String urlPath, String toFilePath) {
       boolean flag = true;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            //BufferedInputStream in = new BufferedInputStream(url.openStream());

            //保存的图片文件名
            File file = new File(toFilePath);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
           // BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));

            byte[] buf = new byte[2048];

           int count = 0;
            while ((count = in.read(buf)) > 0) {
                out.write(buf, 0, count);
            }

            in.close();
            out.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag = false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            flag = false;
        }


        return flag;
    }

    public static void main(String[] args) {
        getUrlFile("http://tp1.sinaimg.cn/1622004412/180/5630578935/1", "K:/t.jpg");
    }
}