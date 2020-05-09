package com.blog.module.business.service.impl;

import com.blog.exception.BaseException;
import com.blog.module.business.domain.Observe;
import com.blog.module.business.domain.User;
import com.blog.module.business.domain.bo.ObserveNodeBO;
import com.blog.module.business.domain.bo.ObserveUserBo;
import com.blog.module.business.mapper.ObserveMapper;
import com.blog.module.business.mapper.UserMapper;
import com.blog.module.business.service.ObserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description：评论信息的业务处理
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
@Service
public class ObserveServiceImpl implements ObserveService {

    @Autowired
    private ObserveMapper observeMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 功能描述：新增博客评论
     *
     * @param user    游客信息
     * @param observe 评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveObserve ( User user, Observe observe ) {
        //新增游客信息
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new BaseException("新增评论信息失败(游客信息增加失败)！");
        }
        //新增评论信息
        observe.setObserverId(user.getId());
        count = observeMapper.insertSelective(observe);
        if (count == 0) {
            throw new BaseException("新增评论信息失败！");
        }
    }

    /**
     * 删除评论
     *
     * @param observeId 评论id
     * @author RenShiWei
     * Date: 2020/4/15 8:24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteObserve ( Long observeId ) {
        int count = observeMapper.deleteByPrimaryKey(observeId);
        if (count == 0) {
            throw new BaseException("删除评论信息失败！");
        }
    }

    /**
     * 功能描述：修改评论
     *
     * @param observe 修改的评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:27
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateObserve ( Observe observe ) {
        int count = observeMapper.updateByPrimaryKeySelective(observe);
        if (count == 0) {
            throw new BaseException("修改评论信息失败！");
        }
    }

    /**
     * 功能描述：根据博客id，查询此博客的所有评论信息
     *
     * @param blogId 博客id
     * @return 博客的评论信息
     * @author RenShiWei
     * Date: 2020/4/15 8:29
     */
    @Override
    public List<ObserveNodeBO> queryObserveByBlogId ( Long blogId ) {
        //所有未处理的一级评论集合
        List<ObserveNodeBO> firstObserveList = observeMapper.queryFirstObserveList(blogId);
        //所有未处理的二级评论集合
        List<ObserveNodeBO> secondObserveList = observeMapper.querySecondObserveList(blogId);

        //将二级评论用链表的方式添加到一级评论
        List<ObserveNodeBO> list = addAllNode(firstObserveList, secondObserveList);

        //控制台打印评论回复
        show(list);

        //返回处理后的评论信息
        return list;
    }

    /**
     * 功能描述：根据评论id查询用户信息
     *
     * @param observeId 评论id
     * @return 评论信息，携带用户信息
     * @author RenShiWei
     * Date: 2020/5/9 18:42
     */
    @Override
    public ObserveUserBo queryObserveUserById ( Long observeId ) {
        Observe observe=observeMapper.selectByPrimaryKey(observeId);
        User user=userMapper.selectByPrimaryKey(observe.getObserverId());
        ObserveUserBo observeUserBo=new ObserveUserBo();
        observeUserBo.setObserve(observe);
        observeUserBo.setUser(user);
        return observeUserBo;
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
    private boolean addNode ( List<ObserveNodeBO> firstList, ObserveNodeBO observeNode ) {
        //循环添加
        for (ObserveNodeBO node : firstList) {
            //判断留言的上一段是否是这条留言（判断这条回复，是否是当前评论的回复）
            if (node.getId().equals(observeNode.getLastId())) {
                //是，添加，返回true
                node.getNextNodes().add(observeNode);
                return true;
            } else {
                //否则递归继续判断
                if (node.getNextNodes().size() != 0) {
                    if (addNode(node.getNextNodes(), observeNode)) {
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
    private List<ObserveNodeBO> addAllNode ( List<ObserveNodeBO> firstList, List<ObserveNodeBO> thenList ) {
        while (thenList.size() != 0) {
            int size = thenList.size();
            for (int i = 0; i < size; i++) {
                if (addNode(firstList, new ObserveNodeBO(thenList.get(i)))) {
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
    private void show ( List<ObserveNodeBO> list ) {
        for (ObserveNodeBO node : list) {
            System.out.println(node.getObserverId() + " 用户回复了" + node.getLastId() + "：" + node.getObserveContent());
            //递归打印回复信息
            if (node.getNextNodes().size() != 0) {
                show(node.getNextNodes());
            }
        }
    }

}
