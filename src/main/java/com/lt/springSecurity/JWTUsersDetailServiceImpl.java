package com.lt.springSecurity;

import com.lt.dao.RoleDao;
import com.lt.dao.UserDao;
import com.lt.entity.BRole;
import com.lt.entity.BUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LT on 2017/8/8.
 */
@Component
public class JWTUsersDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        JWTUser jwtUser;
        Map<String, Object> params = new HashMap<>();
        params.put("sysUserName", userName);
        List<BUser> users = userDao.query(params);
        if (users == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
        } else {
            BUser bUser = users.get(0);
            Date date = new Date(System.currentTimeMillis());
            jwtUser = new JWTUser(bUser.getUserId(), bUser.getSysUserName(), bUser.getPassword(), bUser.getEmail(),
                    getAuthorityByUser(bUser.getUserId()), date);
        }

        return jwtUser;
    }

    /**
     * 得到用户的所有角色
     *
     * @param userId 用户ID
     * @return List<GrantedAuthority>
     */
    public List<GrantedAuthority> getAuthorityByUser(String userId) {
        List<BRole> roles = roleDao.getRoleByUser(userId);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(role -> grantedAuthorities.add((GrantedAuthority) () -> role.getRoleCode()));
        return grantedAuthorities;
    }

}
