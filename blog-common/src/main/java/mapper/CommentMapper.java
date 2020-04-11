package mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 功能描述： 自定义的mapper
 * ‘@RegisterMapper’ 使自定义的mapper可以被扫描到
 *
 * @author RenShiWei
 * Date: 2020/4/11 09:38
 **/
@RegisterMapper
public interface CommentMapper<T> extends Mapper<T>, IdListMapper<T, Long>, InsertListMapper<T> {
}
