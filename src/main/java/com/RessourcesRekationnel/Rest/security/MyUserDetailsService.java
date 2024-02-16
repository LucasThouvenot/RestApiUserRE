package com.RessourcesRekationnel.Rest.security;

import com.RessourcesRekationnel.Rest.dao.UserDao;
import com.RessourcesRekationnel.Rest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDao.findByPseudo(pseudo);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("nom d'utilisateur non trouvé");
        }

        return new MyUserDetails(optionalUser.get());
    }
}
