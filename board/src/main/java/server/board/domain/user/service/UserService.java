package server.board.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.board.domain.user.dto.UserResponseDto;
import server.board.domain.user.enums.Part;
import server.board.domain.user.entity.User;
import server.board.domain.user.repository.UserRepository;
import server.board.global.exception.error.RestApiException;

import java.util.ArrayList;
import java.util.List;

import static server.board.global.exception.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        // repository 에서 전체 유저 조회
        List<User> userList = userRepository.findAll();

        // 응답 전용 객체로 변환 및 반환
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            userResponseDtoList.add(UserResponseDto.create(user));
        }
        return userResponseDtoList;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findByPart(String part) {
        // repository 에서 파트 유저 조회
        List<User> userList = userRepository.findByPart(Part.from(part));

        // 응답 전용 객체로 변환 및 반환
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user : userList) {
            userResponseDtoList.add(UserResponseDto.create(user));
        }
        return userResponseDtoList;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RestApiException(USER_NOT_FOUND));
        return UserResponseDto.create(user);
    }
}
