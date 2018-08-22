package test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tjj .
 */
public class HelloShiro {
    private static final Logger LOGGER=LoggerFactory.getLogger(HelloShiro.class);

    public static void main(String[] args) {
        //初始化SecurityManager
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shirodemo.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前用户
        Subject subject=SecurityUtils.getSubject();
        //登陆
        UsernamePasswordToken token=new UsernamePasswordToken("shiro","201314");
        try{
        subject.login(token);
        }catch (AuthenticationException e){
            LOGGER.error("login failure");
            return;
        }
        LOGGER.info("登陆成功 Hello "+subject.getPrincipal());
        //注销
        subject.logout();

    }
}
