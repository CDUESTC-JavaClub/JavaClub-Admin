package club.cduestc.admin.realm;

import club.cduestc.admin.dao.AdminMapper;
import club.cduestc.admin.dao.UserAccount;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;

public class AdminRealm extends AuthorizingRealm {
    @Resource
    AdminMapper mapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = (String) getAvailablePrincipal(principals);
        UserAccount account = mapper.getAccountByStudentId(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<String>(){{this.add(account.getRole());}});
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userId = (String) authenticationToken.getPrincipal();
        UserAccount account = mapper.getAccountByStudentId(userId);
        if(account == null) throw new AccountException("登陆失败，用户名或密码错误！");
        String password = account.getPassword();
        return new SimpleAuthenticationInfo(userId, password, getName());
    }
}
