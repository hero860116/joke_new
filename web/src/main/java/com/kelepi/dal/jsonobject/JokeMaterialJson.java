package com.kelepi.dal.jsonobject;

import java.util.List;

/**
 * User: liWeiLin
 * Date: 13-9-16 下午10:06
 */
public class JokeMaterialJson {
    private List<JokeTextJson> text;
    private List<PageJson> page;

    public List<JokeTextJson> getText() {
        return text;
    }

    public void setText(List<JokeTextJson> text) {
        this.text = text;
    }

    public List<PageJson> getPage() {
        return page;
    }

    public void setPage(List<PageJson> page) {
        this.page = page;
    }
}
