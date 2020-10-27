package com.nativa.ngp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nativa.ngp.entity.UserEntity;
import com.nativa.ngp.exception.UserException;
import com.nativa.ngp.repository.IUserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

	private IUserRepository userRepository;

	@Autowired
	public CustomUserDetailService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Email inexistente."));

		List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
				authorityListUser);
	}

}
