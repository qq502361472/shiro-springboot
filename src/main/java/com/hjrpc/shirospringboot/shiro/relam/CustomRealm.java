package com.hjrpc.shirospringboot.shiro.relam;

import com.hjrpc.shirospringboot.mgb.mapper.SRolePermissionMapper;
import com.hjrpc.shirospringboot.mgb.mapper.SUserMapper;
import com.hjrpc.shirospringboot.mgb.mapper.SUserRoleMapper;
import com.hjrpc.shirospringboot.mgb.model.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义Realm
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    SUserMapper userMapper;
    @Autowired
    SUserRoleMapper userRoleMapper;
    @Autowired
    SRolePermissionMapper rolePermissionMapper;

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        System.out.println("读取数据库的权限");
        Set<String> roles = getRolesByUsername(username);
        Set<String> permissions = getPermissionsByRoles(roles);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByRoles(Set<String> roles) {

        List<SRolePermission> sRolePermissions = rolePermissionMapper.selectByRoles(roles);
        Set<String> strings = new HashSet<>();
        for (SRolePermission sRolePermission : sRolePermissions) {
            strings.add(sRolePermission.getPermission());
        }
        return strings;
    }

    private Set<String> getRolesByUsername(String username) {
        SUserRoleExample roleExample = new SUserRoleExample();
        SUserRoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SUserRole> sUserRoles = userRoleMapper.selectByExample(roleExample);
        HashSet<String> strings = new HashSet<>();
        for (SUserRole sUserRole : sUserRoles) {
            strings.add(sUserRole.getRoleName());
        }
        return strings;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = getPasswordByUsername(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("randomNum"));
        return authenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        SUserExample userExample = new SUserExample();
        SUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SUser> sUsers = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(sUsers)){
            return null;
        }
        return  sUsers.get(0).getPassword();
    }
}
