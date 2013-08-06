package com.kelepi.dal.queryobject;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分页算法封装。 分页须设置: TotalItem（总条数）,缺省为0, 应该在dao中被设置 PageSize（每页条数），应在web层被设置
 * QueryBase 缺省为20，子类可以通过覆盖 getDefaultPageSize() 修改 CurrentPage（当前页）,缺省为1，首页，
 * 应在web层被设置 分页后，可以得到：TotalPage（总页数） FristItem(当前页开始记录位置，从1开始记数)
 * PageLastItem(当前页最后记录位置) 页面上，每页显示条数名字应为： lines ，当前页名字应为： page
 *
 * 同时加入渲染链接功能，子类覆盖getParameteres方法，返回有效的参数。
 *
 * @author shenyu
 * @author daolin
 *
 */
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 3258128059449226041L;
    private static final Integer defaultPageSize = 20;

    private Integer totalItem;
    private Integer pageSize;
    private Integer currentPage;

    // for paging
    private int startRow;
    private int endRow;

    /**
     * 第一排序
     */
    private String firstOrder;

    /**
     * 第一排序的排序方式，默认升序（与系统保持一致）
     */
    private String firstOrderSort = "asc";

    /**
     * 第二排序
     */
    private String  secondOrder;

    /**
     * 第二排序的排序方式，默认升序（与系统保持一致）
     */
    private String secondOrderSort = "asc";

    protected Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public boolean isFirstPage() {
        return this.getCurrentPage().intValue() == 1;
    }

    public int getPreviousPage() {
        int back = this.getCurrentPage().intValue() - 1;

        if (back <= 0) {
            back = 1;
        }

        return back;
    }

    public boolean isLastPage() {
        return this.getTotalPage() == this.getCurrentPage().intValue();
    }

    public int getNextPage() {
        int back = this.getCurrentPage().intValue() + 1;

        if (back > this.getTotalPage()) {
            back = this.getTotalPage();
        }

        return back;
    }

    /**
     * @return Returns the currentPage.
     */
    public Integer getCurrentPage() {
        if (currentPage == null || currentPage.intValue() == 0) {
            return 1;
        }

        return currentPage;
    }

    /**
     * @param currentPage
     *            The currentPage to set.
     */
    public void setCurrentPage(Integer page) {
        if ((page == null) || (page.intValue() <= 0)) {
            this.currentPage = null;
        } else {
            this.currentPage = page;
        }
        setStartEndRow();
    }

    private void setStartEndRow() {
        this.startRow = this.getPageSize().intValue() * (this.getCurrentPage().intValue() - 1);
        this.endRow = this.startRow + this.getPageSize().intValue();
    }

    /**
     * @return Returns the pageSize.
     */
    public Integer getPageSize() {
        if (pageSize == null) {
            return getDefaultPageSize();
        }

        return pageSize;
    }

    /**
     * @param pageSize
     *            The pageSize to set.
     */
    public void setPageSize(Integer pageSize) {
        if ((pageSize == null) || (pageSize.intValue() <= 0)) {
            this.pageSize = null;
        } else {
            this.pageSize = pageSize;
        }
        setStartEndRow();
    }

    /**
     * @return Returns the totalItem.
     */
    public Integer getTotalItem() {
        if (totalItem == null) {
            return 0;
        }

        return totalItem;
    }

    /**
     * @param totalItem The totalItem to set.
     */
    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;

        int current = this.getCurrentPage().intValue();
        int lastPage = this.getTotalPage();

        if (current > lastPage) {
            this.setCurrentPage(lastPage);
        }
    }

    public int getTotalPage() {
        int pageSize = this.getPageSize().intValue();
        int total = this.getTotalItem().intValue();
        int result = total / pageSize;

        if ((total == 0) || ((total % pageSize) != 0)) {
            result++;
        }

        return result;
    }

    public int getPageLastItem() {
        int cPage = this.getCurrentPage().intValue();
        int pgSize = this.getPageSize().intValue();
        int assumeLast = pgSize * cPage;
        int totalItem = getTotalItem().intValue();

        if (assumeLast > totalItem) {
            return totalItem;
        } else {
            return assumeLast;
        }
    }

    /**
     * @return Returns the endRow.
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * @param endRow
     *            The endRow to set.
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * @return Returns the startRow.
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * @param startRow
     *            The startRow to set.
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getFirstOrder() {
        return firstOrder;
    }

    public void setFirstOrder(String firstOrder) {
        this.firstOrder = firstOrder;
    }

    public String getSecondOrder() {
        return secondOrder;
    }

    public void setSecondOrder(String secondOrder) {
        this.secondOrder = secondOrder;
    }

    public String getFirstOrderSort() {
        return firstOrderSort;
    }

    public void setFirstOrderSort(String firstOrderSort) {
        this.firstOrderSort = firstOrderSort;
    }

    public String getSecondOrderSort() {
        return secondOrderSort;
    }

    public void setSecondOrderSort(String secondOrderSort) {
        this.secondOrderSort = secondOrderSort;
    }
}
