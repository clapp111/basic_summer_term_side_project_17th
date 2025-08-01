package server.board.domain.home.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.board.domain.home.dto.LoginRequestDto;
import server.board.domain.user.dto.UserCreateRequestDto;
import server.board.domain.user.entity.User;
import server.board.domain.user.repository.UserRepository;
import server.board.global.exception.error.RestApiException;
import server.board.global.jwt.JwtToken;
import server.board.global.jwt.JwtTokenProvider;

import static server.board.global.exception.error.CustomErrorCode.DUPLICATE_USER;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional(readOnly = true)
    public JwtToken signIn(@Valid LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    public void signUp(@Valid UserCreateRequestDto userCreateRequestDto) {
        // 중복되는 이메일(유저)가 있다면 중복 에러 발생
        if (userRepository.existsByEmail(userCreateRequestDto.getEmail())) {
            throw new RestApiException(DUPLICATE_USER);
        }
        userRepository.save(User.create(
                userCreateRequestDto,
                bCryptPasswordEncoder.encode(userCreateRequestDto.getPassword())
        ));
    }
}
