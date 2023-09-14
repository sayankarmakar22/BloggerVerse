package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthorRepo authorRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author savedAuthor = authorRepo.findByusername(username);
        var auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(savedAuthor.getRole()));

        return new User(savedAuthor.getUsername(), savedAuthor.getPassword(), auth);
    }
}
