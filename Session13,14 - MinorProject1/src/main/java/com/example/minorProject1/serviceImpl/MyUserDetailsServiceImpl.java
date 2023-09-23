package com.example.minorProject1.serviceImpl;

import com.example.minorProject1.models.MyUser;
import com.example.minorProject1.repository.MyUserRepositoryInterf;
import com.example.minorProject1.repositoryImpl.MyUserCacheRepository;
import com.example.minorProject1.requestDto.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Autowired
    MyUserRepositoryInterf myUserRepositoryInterf;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${users.authority.student}")
    String studentAuthority;

    @Value("${users.authority.admin}")
    String adminAuthority;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = myUserCacheRepository.get(username);

        if(myUser == null){
            myUser = myUserRepositoryInterf.findByUsername(username);
            if(myUser!=null)
                myUserCacheRepository.set(myUser);
        }
        return myUser;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest){
        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null){
            myUserBuilder.authority(studentAuthority);
        }else{
            myUserBuilder.authority(adminAuthority);
        }
        return myUserRepositoryInterf.save(myUserBuilder.build());
    }
}
