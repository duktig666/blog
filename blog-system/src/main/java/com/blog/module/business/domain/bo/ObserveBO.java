package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.Blogger;
import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.Visitor;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：封装一条评论，可能用到的信息
 *
 * @author RenShiWei
 * Date: 2020/4/15 10:50
 */
@Data
public class ObserveBO {

    /**
     * 评论信息
     */
    private Observe observe;

    /**
     * 评论者是游客，返回游客信息
     */
    private Visitor visitor;

    /**
     * 评论者是博主，返回博主信息
     */
    private Blogger blogger;


    /**
     * 一级评论信息的二级回复
     */
    private List<ObserveBO> secondObserveList;

    /**
     * 功能描述：将二级评论集合的二级评论添加至对应的一级评论
     * @param firstObserveList 未处理的一级评论集合
     * @param secondObserveList 未处理的二级评论集合
     * @return 一级评论集合（二级评论处理过的）
     * @author RenShiWei
     * Date: 2020/4/15 11:09
     */
    public static List<ObserveBO> addSecondReply ( List<ObserveBO> firstObserveList, List<ObserveBO> secondObserveList ) {
        //遍历一级评论
        for (ObserveBO firstObserve : firstObserveList) {
            //遍历二级评论
            for (ObserveBO secondObserve : secondObserveList) {
                if (secondObserve.getObserve().getLastId().equals(firstObserve.getObserve().getId())){
                    //如果二级评论的lastId等于一级评论的id，那么二级评论，追加到一级评论的集合
                    firstObserve.getSecondObserveList().add(secondObserve);
                    //二级评论集合移除此条回复
                    secondObserveList.remove(secondObserve);
                }
            }
        }
        return firstObserveList;
    }

}