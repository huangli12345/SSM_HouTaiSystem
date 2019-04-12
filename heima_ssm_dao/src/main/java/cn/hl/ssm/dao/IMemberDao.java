package cn.hl.ssm.dao;

import cn.hl.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMemberDao {

//    @Select("select * from member where id = #{id}")
@Select("select * from member where id=#{id}")
    public Member findById(String id) throws Exception;
}
