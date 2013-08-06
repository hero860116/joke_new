package com.kelepi.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("stringUtil")
public class StringUtil {

    public String truncStr(String src, int index) {
        String truncStr = src;

        if (src.length() > index) {
            truncStr = src.substring(0, index);
            truncStr = truncStr + "...";
        }

        return truncStr;
    }

    public String truncStr(String src, int index, String suffix) {
        String truncStr = src;

        if (src.length() > index) {
            truncStr = src.substring(0, index);

            if (suffix != null) {
                truncStr = truncStr + "...";
            }
        }

        return truncStr;
    }

    /**
     * 解析属性对
     *
     * @param paramStr 属性对字符串
     * @param separators 属性对分隔符，从前到后,优先级高的先传入   如："name=kelepi&age=25" 传入:&,=
     * @return
     */
    public Map<String, String> getParameterPair(String paramStr, String... separators) {
        Map<String, String> paras = new HashMap<String, String>();

        if (paramStr != null) {
            //初始化
            String[] paramArray = new String[]{paramStr.trim()};

            //初始化
            int size = 0;

            executeParse(paras, paramArray, size, separators);
        }

        return paras;
    }

    //配合解析属性对，是一个迭代方法
    private void executeParse(Map<String, String> params, String[] paramArray,  int size, String... separators) {

        int moreSize = size + 1;
        for (int i = 0; i < paramArray.length; i++) {
            String sss = paramArray[i];
            String[] paramStrs = sss.split(separators[size]);

            if (moreSize < separators.length) {
                executeParse(params, paramStrs, moreSize, separators);
            } else {
                if (paramStrs.length == 2) {
                    params.put(paramStrs[0], paramStrs[1]);
                }
                else if (paramStrs.length == 1) {
                    params.put(paramStrs[0], "");
                }

            }
        }
    }

    /**
     * 如果src不足位数，用comp补全
     * @param src
     * @param length
     * @param isLeft true:表示补在左边，false：表示补在右边
     * @return
     */
    public static String getStringCompletion(String src, int length,  String comp ,boolean isLeft) {
        int len = length - src.length();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                if (isLeft) {
                    src = comp + src;
                } else {
                    src += comp;
                }
            }
        }

        return src;
    }

    /**
     * 获得字符串的字节长度，如果是中文，认为是两个字节
     * @param str
     * @return
     */
    public static int getByteSize(String str) {
        int size = 0;

        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                String c = str.substring(i, i+1);
                if (c.matches("[\\u4e00-\\u9fa5]")){
                    size += 2;
                } else {
                    size += 1;
                }
            }
        }

        return size;
    }

    /**
     * 截取字符串，长度不够时，不会报错
     * @param src
     * @param start
     * @param end
     * @return
     */
    public static String subString(String src, int start, int end) {
        if (src == null) {
            return "";
        }

        int length = src.length();
        if (start > length) {
            return "";
        } else if (start == length) {
            return src.substring(start);
        }

        if (length < end) {
            end = length;
        }

        return src.substring(start, end);
    }
}
