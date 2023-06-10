package neordinary.oteam.service;

import lombok.RequiredArgsConstructor;
import neordinary.oteam.domain.user.User;
import neordinary.oteam.domain.user.UserRepository;
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
}
