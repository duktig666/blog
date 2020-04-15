package com.blog.module.business.domain.bo;

import com.blog.module.business.domain.Observe;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：封装博客评论的BO
 * 采用链表结构实现
 *
 * @author RenShiWei
 * Date: 2020/4/15 8:31
 **/
@Data
public class ObserveNodeBO {

    /**
     * 评论id
     */
    private Long id;

    /**
     * 所属博客id
     */
    private Long blogId;

    /**
     * 评论者id
     */
    private Long observerId;

    /**
     * 评论者类型
     */
    private Integer observerType;

    /**
     * 评论内容
     */
    private String observeContent;

    /**
     * 评论上一级的id
     */
    private Long lastId;

    /**
     * 是否删除
     */
    private Boolean delete;

    /**
     * 下一条回复
     */
    private List<ObserveNodeBO> nextNodes = new ArrayList<>();

    public ObserveNodeBO ( Observe observe ) {
        this.id = observe.getId();
        this.blogId = observe.getBlogId();
        this.observerId = observe.getObserverId();
        this.observerType = observe.getObserverType();
        this.observeContent = observe.getObserveContent();
        this.lastId = observe.getLastId();
        this.delete = observe.getDelete();
    }

    /**
     * 功能描述：将单个node添加到链表中
     *
     * @param firstList   第一层评论集合（链表）
     * @param observeNode 非第一层评论的回复信息
     * @return 是否添加
     * @author RenShiWei
     * Date: 2020/4/15 8:58
     */
    public static boolean addNode ( List<ObserveNodeBO> firstList, ObserveNodeBO observeNode ) {
        //循环添加
        for (ObserveNodeBO node : firstList) {
            //判断留言的上一段是否是这条留言（判断这条回复，是否是当前评论的回复）
            if (node.getId().equals(observeNode.getLastId())) {
                //是，添加，返回true
                node.getNextNodes().add(observeNode);
                System.out.println("添加了一个");
                return true;
            } else {
                //否则递归继续判断
                if (node.getNextNodes().size() != 0) {
                    if (ObserveNodeBO.addNode(node.getNextNodes(), observeNode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 功能描述：将查出来的lastId不为null的回复都添加到第一层Node集合中
     *
     * @param firstList 第一层评论集合（链表）
     * @param thenList  非第一层评论集合（链表）
     * @return 所有评论集合（非第一层评论集合对应添加到第一层评论集合，返回）
     * @author RenShiWei
     * Date: 2020/4/15 8:54
     */
    public static List<ObserveNodeBO> addAllNode ( List<ObserveNodeBO> firstList, List<Observe> thenList ) {
        while (thenList.size() != 0) {
            int size = thenList.size();
            for (int i = 0; i < size; i++) {
                if (ObserveNodeBO.addNode(firstList, new ObserveNodeBO(thenList.get(i)))) {
                    thenList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        return firstList;
    }

    /**
     * 功能描述：打印评论的链表回复信息
     *
     * @param list 评论信息（链表集合）
     * @author RenShiWei
     * Date: 2020/4/15 9:10
     */
    public static void show ( List<ObserveNodeBO> list ) {
        for (ObserveNodeBO node : list) {
            System.out.println(node.getObserverId() + " 用户回复了"+node.getLastId()+"：" + node.getObserveContent());
            //递归打印回复信息
            if (node.getNextNodes().size() != 0) {
                ObserveNodeBO.show(node.getNextNodes());
            }
        }
    }


}

