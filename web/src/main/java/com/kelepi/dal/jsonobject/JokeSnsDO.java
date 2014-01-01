package com.kelepi.dal.jsonobject;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-19
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
public class JokeSnsDO {
    private String title;
    private String  contentImageUrl;
    private int topSize;
    private int downSize;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentImageUrl() {
        return contentImageUrl;
    }

    public void setContentImageUrl(String contentImageUrl) {
        this.contentImageUrl = contentImageUrl;
    }

    public int getTopSize() {
        return topSize;
    }

    public void setTopSize(int topSize) {
        this.topSize = topSize;
    }

    public int getDownSize() {
        return downSize;
    }

    public void setDownSize(int downSize) {
        this.downSize = downSize;
    }
}
