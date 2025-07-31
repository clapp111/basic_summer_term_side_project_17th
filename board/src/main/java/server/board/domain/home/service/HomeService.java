package server.board.domain.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.board.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final UserRepository userRepository;

}
