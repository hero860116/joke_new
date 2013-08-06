package com.kelepi.dal.constants;

import java.util.Arrays;
import java.util.List;

public class TasteConstants {

    /**
     * 退订最大时间  (秒数)
     * 也是结束订单的打印时间
     */
    public static final NumberConstants<Integer> unsubscribeIntervalNumber = createNumber(2*60, "2分钟");

    /**
     * 及时送餐，等价预定的偏移时间
     */
    public static final NumberConstants<Integer> MIGRATIONSECENDS = createNumber(40*60, "40分钟");

    /**
     * 进行中得订餐，离餐到多少时间后停止订餐(秒数)
     */
    public static final int inOrderOverseconds = 20 * 60;

    /**
     * 最后一个人点餐，订单的最大超车辆
     */
    public static final int maxOrderExtendSize = 5;

    /**
     * 起步距离，2.5公里收取3元外送费
     */
    public static final double STARTING_DISTANCE = 2.5;

    /**
     * 超过2公里部分，每公里加收2元外送费
     */
    public static final double CONST_EVERY_MILE= 2;

    /**
     * 最近5天的历史记录
     */
    public static final int recentOrderDays = 5;

    /**
     * 监督电话
     */
    public static String supervisionPhone = "400-0571-157";

    /**
     * 客服电话
     */
    public static String customerPhone = "400-0571-157";

    /**
     * 网站地址
     */
    public static final String webSite = "www.juwaimai.com";


    /**
     * 邮件信息
     */
    //public static final String MAIL_HOST = "smtp.gmail.com";
    //public static final int MAIL_PORT = 465;
    //public static final String MAIL_USERNAME  = "juwaimai@gmail.com";
    //public static final String MAIL_PASSWORD = "517juwaimai";
    public static final String MAIL_HOST = "smtp.163.com";
    public static final int MAIL_PORT =25;
    public static final String MAIL_USERNAME  = "juwaimai@163.com";
    public static final String MAIL_PASSWORD = "517juwaimai";

    /**
     * sina微博登录信息
     */
    public static final String SINA_LOGINURI = "https://api.t.sina.com.cn/oauth2/authorize";
    public static final String SINA_GETUID = "https://api.weibo.com/2/account/get_uid.json";
    public static final String SINA_GETACCESSTOKEN = "https://api.weibo.com/oauth2/access_token";
    public static final String SINA_SHOWUSER = "https://api.weibo.com/2/users/show.json";
    public static final String SINA_CLIENT_ID = "2974140750";
    public static final String SINA_CLIENT_SECRET = "471e4dfb41e0e45991ef7c2a7340aac5";
    public static final String SINA_REDIRECT_URI = "http://www.juwaimai.com/sinaweibo.htm";


    /**
     * ip解析信息
     */
    public static final String IP_DATANAME = "qqwry.dat";
    //public static final String IP_DATAPATH = "C:/ip";
    public static final String IP_DATAPATH = "/home/kelepi/data";

    /**
     * 食物列表分页数量
     */
    public static final int PAGESIZE_FOOD = 20;

    /**
     * 搜索食物列表分页数量
     */
    public static final int PAGESIZE_SEARCH_FOOD = 20;

    /**
     * 及时送餐默认时间码
     */
    public static final String TIMELY_SEND = "00:00";


    /**
     * 默认分隔符 (空格)
     */
    public static final String SEPARATOR = " ";

    public static final String SINAWEIBO_INIT_PASSWORD = "123456";

    public static final String MIFAN = "米饭";

    /**
     * 非主菜，辅菜类,计菜倒数是使用
     */
    public static final List<String> NO_FOOD_LIST = Arrays.asList("米饭", "荷包蛋");

    public static final String NONE_COVER_IMAGE_PATH = "/statics/images/none.jpg";

    public String getSupervisionPhone() {
        return supervisionPhone;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

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
