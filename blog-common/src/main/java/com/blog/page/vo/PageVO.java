package com.blog.page.vo;

import lombok.Data;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/14 11:24
 **/
@Data
public class PageVO {

    /** 当前页 */
    private Integer currentPage;

    /** 当前页显示数据的条数 */
    private Integer rows;

    /** 排序方式 */
    private String sort;

}