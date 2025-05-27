package com.backend.pilates.components;

import com.backend.pilates.model.Professor;
import com.backend.pilates.repositories.ProfessorRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ProfessorRepository professorRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(ProfessorRepository professorRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.professorRepository = professorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();

        Professor professorToLogin = professorRepository.findByEmail(email);

        if (professorToLogin == null) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if (!bCryptPasswordEncoder.matches(password, professorToLogin.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String rolesNames = professorToLogin.getRole().getName().toString();

        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(rolesNames));

        return new UsernamePasswordAuthenticationToken(professorToLogin.getEmail(), null, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
