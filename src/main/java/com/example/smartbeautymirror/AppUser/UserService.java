package com.example.smartbeautymirror.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입 로직

    public void signUp(User userDto){
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("이미 사용 중인 아이디입니다.");
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setNickname(userDto.getNickname());
        userRepository.save(user);
        System.out.println("User signup successfully: " + user);
    }

    //로그인 로직
    public boolean login(String email, String password) throws IllegalAccessException {
        Optional<User> userOptional = userRepository.findByEmail(email); //user가 있는 db에서 email값을 찾는 코드

        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return true; // 로그인 성공
        }
        return false;

    }
}
