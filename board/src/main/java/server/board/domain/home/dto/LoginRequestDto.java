package server.board.domain.home.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "로그인 요청 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {

    @Schema(description = "사용자의 이메일", example = "appcenter17@inu.ac.kr")
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @Schema(description = "사용자의 패스워드", example = "Appcenter1234@")
    @NotBlank(message = "패스워드는 필수입니다.")
    private String password;

}
