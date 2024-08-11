package com.leo.jwt_springboot_demo.service;

import com.leo.jwt_springboot_demo.dto.MyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Enter into to the userDetails service---------->");
        //actually this data should data take from the data base for the time being we have used the hardcoded data
        MyUser user = new MyUser().setUserName("admin")
                .setPassWord("$2a$10$D2DtUbkYYiemtgmAa3Lc9eJh5b/N0vDXv1DuL2UjI7V8fxmC9CKPy")
                .setRole("ADMIN");
        var userObj = user;
        if (user != null) {
            return User.builder().username(userObj.getUserName())
                    .password(userObj.getPassWord())
                    .roles(getRoles(userObj))
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }

    public String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
