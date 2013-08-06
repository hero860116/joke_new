package com.kelepi.dal.jsonobject;

public class TreeNode {

    private Long id;
    private Long parentId;
    private String name;
    private boolean checked;
    private boolean open;
    private String url;
    private String target;



    public TreeNode(Long id, long parentId, String name) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public TreeNode(Long parentId, String name, String url, String target) {
        super();
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.target = target;
    }

    public TreeNode(Long id, Long parentId, String name, boolean open, String url, String target) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.open = open;
        this.url = url;
        this.target = target;
    }

    public TreeNode() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
