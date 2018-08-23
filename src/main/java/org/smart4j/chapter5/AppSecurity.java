package org.smart4j.chapter5;

import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.plugin.security.SmartSecurity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用安全控制
 * @author tjj .
 * jdbcRealm.authenticationQuery=select password from user where  username=?
jdbcRealm.userRolesQuery=select r.role_name from user u, user_role ur,role r where u.id = ur.user_id and r.id=ur.role_id and u.username=?
jdbcRealm.permissionsQuery=select p.permission_name from role r,role_permission rp, permission p where r.id=rp.role_id and p.id=rp.permission_id and role_name= ?

 */
public class AppSecurity implements SmartSecurity{
    @Override
    public String getPassword(String username) {
        String sql="select password from user where  username=?";
        return DatabaseHelper.query(sql,username);
    }

    @Override
    public Set<String> getRoleNameSet(String username) {
        String sql="select r.role_name from user u, user_role ur,role r where u.id = ur.user_id and r.id=ur.role_id and u.username=?";

        return DatabaseHelper.querySet(sql,username);
    }

    @Override
    public Set<String> getPermissionSet(String roleName) {
        String sql="select p.permission_name from role r,role_permission rp, permission p where r.id=rp.role_id and p.id=rp.permission_id and role_name= ?\n";
        return DatabaseHelper.querySet(sql,roleName);
    }
}
