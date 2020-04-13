package com.blog.page.dto;

import lombok.Data;

import java.util.List;

/**
 * Description： 分页数据封装
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 19:54
 **/

@Data
public class PageResultDto<T> {
        /** 总条数 */
        private Long total;
        /** 总页数 */
        private Integer totalPage;
        /** 当前页数据 */
        private List<T> items;

        public PageResultDto () {
        }

        public PageResultDto ( Long total, List<T> items) {
            this.total = total;
            this.items = items;
        }

        public PageResultDto ( Long total, Integer totalPage, List<T> items) {
            this.total = total;
            this.totalPage = totalPage;
            this.items = items;
        }

}
