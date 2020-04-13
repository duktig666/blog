package com.blog.page.vo;

import lombok.Data;

/**
 * 功能描述：接受前台传来的分页信息
 *
 * @author RenShiWei
 * Date: 2020/4/13 10:56
 **/
@Data
public class PageVo {

    /** 当前页 */
    private Integer currentPage;

    /** 当前页显示数据的条数 */
    private Integer rows;

    /** 排序方式 */
    private String sort;

}

