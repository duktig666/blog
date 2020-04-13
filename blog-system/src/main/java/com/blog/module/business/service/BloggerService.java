package com.blog.module.business.service;

import com.blog.module.business.domain.Blogger;

/**
 * Description：
 *
 * @author jiaoqianjin
 * Date: 2020/4/11 11:13
 **/
public interface BloggerService {
    /**
     * 功能描述：新增博主信息
     * @param blogger
     * @author jiaoqianjin
     * Date: 2020/4/13 8:57
     */
    void saveBlogger(Blogger blogger);

   /**
    * 功能描述：根据博主id查询博主信息
    *
    * @param bloggerId
    * @return 查询到的博主信息
    * @author jiaoqianjin
    * Date: 2020/4/13 9:49
    */

    Blogger queryBlogger(Long bloggerId);

    /**
     * 功能描述：根据博主id 修改对应博主信息
     *
     * @param blogger
     * @author jiaoqianjin
     * Date: 2020/4/13 10:09
     */
    void updateBlogger(Blogger blogger);

    /**
     * 功能描述：根据博主id 删除对应博主信息
     *
     * @param bloggerId
     * @author jiaoqianjin
     * Date: 2020/4/13 10:11
     */
    void deleteBlogger(Long bloggerId);
}
