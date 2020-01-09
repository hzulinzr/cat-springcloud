package com.lin.tools;

/**
 * @author lzr
 * @date 2019-11-25 11:31:23
 */
public class Page {

    private Integer page;
    private Integer rows;
    private Integer index;

    public Page() {
        this.page = 1;
        this.rows = 10;
    }

    public Integer getPage() {
        return null == page ? 0 : (page - 1) * this.rows;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getIndex() {
        if (null == this.index) {
            this.index = getPage() + 1;
        }
        return this.index ++;
    }
}
