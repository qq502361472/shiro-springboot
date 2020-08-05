package com.hjrpc.shirospringboot.mgb.mapper;

import com.hjrpc.shirospringboot.mgb.model.SUserRole;
import com.hjrpc.shirospringboot.mgb.model.SUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SUserRoleMapper {
    int countByExample(SUserRoleExample example);

    int deleteByExample(SUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SUserRole record);

    int insertSelective(SUserRole record);

    List<SUserRole> selectByExample(SUserRoleExample example);

    SUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SUserRole record, @Param("example") SUserRoleExample example);

    int updateByExample(@Param("record") SUserRole record, @Param("example") SUserRoleExample example);

    int updateByPrimaryKeySelective(SUserRole record);

    int updateByPrimaryKey(SUserRole record);
}