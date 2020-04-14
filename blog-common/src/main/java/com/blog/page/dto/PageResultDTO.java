package com.blog.page.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/14 11:22
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDTO<T> {
    /** 总条数 */
    private Long total;
    /** 总页数 */
    private Integer totalPage;
    /** 当前页数据 */
    private List<T> items;

    public PageResultDTO ( Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

}
