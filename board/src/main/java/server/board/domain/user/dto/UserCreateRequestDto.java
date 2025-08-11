package server.board.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "유저 생성 요청 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {

    @Email
    @Schema(description = "이메일", example = "appcenter17@inu.ac.kr")
    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Schema(description = "패스워드", example = "Appcenter1234@")
    @NotBlank(message = "패스워드는 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Schema(description = "파트", example = "basic")
    private String part;

    @Schema(description = "기수", example = "17.5")
    private Double generation;

}
