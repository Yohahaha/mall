package com.yoha.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yoha.mall.common.util.JwtTokenUtil;
import com.yoha.mall.dao.UmsAdminRoleRelationDao;
import com.yoha.mall.mbg.mapper.UmsAdminMapper;
import com.yoha.mall.mbg.model.UmsAdmin;
import com.yoha.mall.mbg.model.UmsAdminExample;
import com.yoha.mall.mbg.model.UmsPermission;
import com.yoha.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        UmsAdmin user = new UmsAdmin();
        BeanUtil.copyProperties(umsAdmin, user);
        user.setCreateTime(new Date());
        user.setStatus(1);
        // 查询用户名是否存在重复
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null) {
            return null;
        }
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        adminMapper.insert(user);
        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {
            logger.error("登录异常：{}", e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }
}
