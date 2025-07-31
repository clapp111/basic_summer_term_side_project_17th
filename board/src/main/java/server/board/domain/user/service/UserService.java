package server.board.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.board.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}
