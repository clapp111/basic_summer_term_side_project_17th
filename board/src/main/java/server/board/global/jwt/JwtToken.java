package server.board.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class JwtToken {
    private String grantType; //JWT의 인증 타입을 나타내는 문자열 필드
    private String accessToken; // 접근 토큰을 나타내는 문자열 필드
    private String refreshToken; // 갱신 토큰을 나타내는 문자열 필드
}
