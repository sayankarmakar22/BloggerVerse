package com.sayan.BlogApplication.Services.Implementations;

import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.Viewer;
import com.sayan.BlogApplication.Repository.AuthorRepo;
import com.sayan.BlogApplication.Repository.ViewerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private ViewerRepo viewerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(authorRepo.existsByusername(username)){
            Author savedAuthor = authorRepo.findByusername(username);
            var auth = new ArrayList<GrantedAuthority>();
            auth.add(new SimpleGrantedAuthority(savedAuthor.getRole()));

            return new User(savedAuthor.getUsername(), savedAuthor.getPassword(), auth);
        }

        Viewer savedViewer = viewerRepo.findByusername(username);

        var auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(savedViewer.getRole()));
        return new User(savedViewer.getUsername(), savedViewer.getPassword(), auth);
    }
}
