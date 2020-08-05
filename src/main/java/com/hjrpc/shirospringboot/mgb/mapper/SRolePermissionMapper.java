package com.hjrpc.shirospringboot.mgb.mapper;

import com.hjrpc.shirospringboot.mgb.model.SRolePermission;
import com.hjrpc.shirospringboot.mgb.model.SRolePermissionExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SRolePermissionMapper {
    int countByExample(SRolePermissionExample example);

    int deleteByExample(SRolePermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SRolePermission record);

    int insertSelective(SRolePermission record);

    List<SRolePermission> selectByExample(SRolePermissionExample example);

    SRolePermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SRolePermission record, @Param("example") SRolePermissionExample example);

    int updateByExample(@Param("record") SRolePermission record, @Param("example") SRolePermissionExample example);

    int updateByPrimaryKeySelective(SRolePermission record);

    int updateByPrimaryKey(SRolePermission record);

    List<SRolePermission> selectByRoles(@Param("roles")Set<String> roles);
}