package com.i4o.dms.itldis.security.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.i4o.dms.itldis.masters.usermanagement.user.domain.LoginUser;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.KubotaUserRepository;
import com.i4o.dms.itldis.masters.usermanagement.user.service.LoginUserService;
import com.i4o.dms.itldis.security.model.UserFactory;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private KubotaUserRepository kubotaUserRepository;

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LoginUser> user = this.kubotaUserRepository.findByUserName(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return UserFactory.create(loginUserService.getAuthenticatedUser(username));
        }
    }

}
