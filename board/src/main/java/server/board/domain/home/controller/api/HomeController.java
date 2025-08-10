package server.board.domain.home.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.board.domain.home.controller.docs.HomeControllerSpecification;
import server.board.domain.home.dto.LoginRequestDto;
import server.board.domain.home.service.HomeService;
import server.board.domain.user.controller.docs.UserControllerSpecification;
import server.board.domain.user.dto.UserCreateRequestDto;
import server.board.domain.user.dto.UserResponseDto;
import server.board.global.jwt.JwtToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController implements HomeControllerSpecification {

    private final HomeService homeService;

    // 로그인(/api/sign-in)
    @PostMapping("/sign-in")
    public ResponseEntity<JwtToken> signIn(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(homeService.signIn(loginRequestDto));
    }

    // 회원가입(/api/sign-up)
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto>  signUp(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(homeService.signUp(userCreateRequestDto));
    }
}
