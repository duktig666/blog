package com.blog.pojo;

import java.util.List;

/**
 * Description： 分页数据封装
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 19:54
 **/

public class PageResult<T> {
        private Long total;// 总条数
        private Long totalPage;// 总页数
        private List<T> items;// 当前页数据

        public PageResult() {
        }

        public PageResult(Long total, List<T> items) {
            this.total = total;
            this.items = items;
        }

        public PageResult(Long total, Long totalPage, List<T> items) {
            this.total = total;
            this.totalPage = totalPage;
            this.items = items;
        }

        public Long getTotal() {
            return total;
        }

        public void setTotal(Long total) {
            this.total = total;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }

        public Long getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(Long totalPage) {
            this.totalPage = totalPage;
        }
}
