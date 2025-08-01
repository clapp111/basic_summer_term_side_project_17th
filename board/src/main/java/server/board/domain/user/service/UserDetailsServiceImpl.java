package server.board.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import server.board.domain.user.entity.User;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.domain.user.repository.UserRepository;
import server.board.global.exception.error.RestApiException;

import static server.board.global.exception.error.CustomErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) {
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new RestApiException(USER_NOT_FOUND));

        return new UserDetailsImpl(user);
    }
}
