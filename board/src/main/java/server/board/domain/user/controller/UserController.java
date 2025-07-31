package server.board.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.board.domain.user.dto.UserResponseDto;
import server.board.domain.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    public final UserService userService;

    // 전체 유저 조회(/api/users)
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsersInfo() {

    }

    // 파트 유저 조회(/api/users/{parts})
    @GetMapping("/{parts}")
    public ResponseEntity<List<UserResponseDto>> getPartsUsersInfo() {

    }

    // 마이페이지 조회(/api/users/me)
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo() {

    }

}
