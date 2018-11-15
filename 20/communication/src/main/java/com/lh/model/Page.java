package com.lh.model;

/**
 * 分页封装类
 * @author LH
 */

public class Page {
    private int totalRows = 0; // 总记录数
    private int totalPages = 0; // 总的页数
    private int pageSize = 10; // 每页显示的记录数
    private int currentPage = 1; // 当前页
    private boolean hasPrevious = false; // 是否有上一页
    private boolean hasNext = false; // 是否有下一页
    private int firstResult = 0; //数据库查询的开始记录

    public Page() {
    }

    /**
     * 初始化page类
     *
     * @param totalRows
     *            总的记录数
     * @param pageSize
     *            每页最多显示的信息数
     */
    public void init(int totalRows, int pageSize) {
        this.totalRows = totalRows;
        this.pageSize = pageSize;
        totalPages = ((totalRows + pageSize) - 1) / pageSize;
        refresh(); // 刷新当前页面信息
    }

    /**
     * 得到当前页
     *
     * @return 当前页
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页
     *
     * @param currentPage
     *            当前页
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        refresh();
    }

    /**
     * 得到每页显示的记录数
     *
     * @return
     *
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页显示的记录数
     *
     * @param pageSize
     *            每页显示的记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        refresh();
    }

    /**
     * 得到总的页数
     *
     * @return Returns 总的页数
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 设置总的页数
     *
     * @param totalPages
     *            总页数
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        refresh();
    }

    /**
     * 得到总的记录数
     *
     * @return 总的记录数
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总的记录
     *
     * @param totalRows
     *            总的记录数
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        refresh();
    }

    /**
     * 㧈把把页面㧈㧈宋 㧈把把把设置 设置页面为首页，并把是否有上一页设为false,刷新当前页面
     */

    public void first() {
        currentPage = 1;
        this.setHasPrevious(false);
        refresh();
    }

    /**
     * 上一页，并刷新当前页面
     */
    public void previous() {
        currentPage--;
        refresh();
    }

    /**
     * 下一页，并刷新当前页面
     */
    public void next() {

        if (currentPage < totalPages) {
            currentPage++;
        }
        refresh();
    }

    /**
     * 尾页并刷新当前页面
     */
    public void last() {
        currentPage = totalPages;
        this.setHasNext(false);
        refresh();
    }

    /**
     * 是否有下一页
     *
     * @return 是否有下一页
     */
    public boolean isHasNext() {
        return hasNext;
    }

    /**
     * 设置下一页
     *
     * @param hasNext
     *            下一页
     */
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * 是否有上一页
     *
     * @return 是否有上一页
     */

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    /**
     * 设置是否有上一页
     *
     * @param hasPrevious
     *            上一页
     */
    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    /**
     * 刷新当前页面
     */
    public void refresh() {
        if (totalPages <= 1) {
            hasPrevious = false;
            hasNext = false;
        } else if (currentPage == 1) {
            hasPrevious = false;
            hasNext = true;
        } else if (currentPage == totalPages) {
            hasPrevious = true;
            hasNext = false;
        } else {
            hasPrevious = true;
            hasNext = true;
        }
    }

    /**
     * 得到该页面的第一条记录
     *
     * @return 得到显示页面的第一条记录
     */

    public int getFirstResult() {
        return firstResult;
    }

    /**
     * 设置页面的第一条记录
     *
     * @param 第一条记录的位置
     */
    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

}
