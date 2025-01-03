package br.com.tudonamala.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tudonamala.backend.exception.user.UserNotFoundException;
import br.com.tudonamala.backend.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        if (userRepository.findByEmail(email) == null) {
            throw new UserNotFoundException("Usuário inexistente ou senha inválida.");
        } else {
            return userRepository.findByEmail(email);
        }
    }
    
}
