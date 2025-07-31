package server.board.domain.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.board.domain.home.service.HomeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    // 로그인(/api/sign-in)
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(){

    }

    // 회원가입(/api/sign-up)
    @PostMapping("/sign-up")
    public ResponseEntity<?>  signUp(){

    }
}
