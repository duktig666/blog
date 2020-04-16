package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：封装博客评论的BO <br>
 * 采用链表结构实现
 *
 * @author RenShiWei
 * Date: 2020/4/15 8:31
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ObserveNodeBO extends Observe {

    /**
     * 评论的用户信息
     */
    private User user;

    /**
     * 下一条回复
     */
    private List<ObserveNodeBO> nextNodes = new ArrayList<>();

    public ObserveNodeBO ( ObserveNodeBO observeNodeBo ) {
        super();
        setId(observeNodeBo.getId());
        setBlogId(observeNodeBo.getBlogId());
        setObserverId(observeNodeBo.getObserverId());
        setObserveContent(observeNodeBo.getObserveContent());
        setLastId(observeNodeBo.getLastId());
        setDelete(observeNodeBo.getDelete());
        setCreateDate(observeNodeBo.getCreateDate());
        setUpdateDate(observeNodeBo.getUpdateDate());
        this.user = observeNodeBo.getUser();
    }

}

