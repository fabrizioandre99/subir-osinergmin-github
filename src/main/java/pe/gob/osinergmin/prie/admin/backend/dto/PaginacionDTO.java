package pe.gob.osinergmin.prie.admin.backend.dto;

import java.io.Serializable;

public class PaginacionDTO implements Serializable {
    private int size;
    private int page;
    private String order;
    private String sort;

    public PaginacionDTO() {}

    public PaginacionDTO(int page, int size) {
        this.size = size;
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
