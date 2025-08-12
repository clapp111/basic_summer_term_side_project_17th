package server.board.domain.user.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.board.domain.user.controller.docs.UserControllerSpecification;
import server.board.domain.user.dto.UserResponseDto;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.domain.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController implements UserControllerSpecification {

    public final UserService userService;

    // 전체 유저 조회(/api/users)
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsersInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    // 파트 유저 조회(/api/users/{parts})
    @GetMapping("/{parts}")
    public ResponseEntity<List<UserResponseDto>> getPartUsersInfo(@PathVariable String parts) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByPart(parts));
    }

    // 마이페이지 조회(/api/users/me)
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(userDetails.getUsername()));
    }

}
