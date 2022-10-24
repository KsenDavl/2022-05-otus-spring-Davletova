package ru.otus.spring.davlks.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.security.dao.UserDao;
import ru.otus.spring.davlks.security.dto.UserDto;
import ru.otus.spring.davlks.security.entity.User;
import ru.otus.spring.davlks.security.enums.RoleNames;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username);
    }

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(RoleNames.getAuthoritiesById(userDto.getRoleNameId()));
        return user;
    }
}
