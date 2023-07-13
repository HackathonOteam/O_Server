package neordinary.oteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.oteam.domain.user.User;
import neordinary.oteam.domain.user.UserRepository;
import neordinary.oteam.global.exception.ExistNicknameException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(String name) {

        User user = User.create(name);
        return userRepository.save(user);
    }

    public void checkNickname(String name) {

        if(isValidNickName(name)){
            throw new ExistNicknameException("이미 존재하는 닉네임입니다.");
        }
    }

    private boolean isValidNickName(String nickname) {

        return !userRepository.existsByName(nickname);
    }
}
