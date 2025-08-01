package server.board.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {

    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    @Size(max = 50, message = "입력된 이메일의 길이가 너무 깁니다.")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "패스워드는 필수입니다.")
    @Size(max = 100, message = "입력된 비밀번호의 길이가 너무 깁니다.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 20, message = "입력된 이름의 길이가 너무 깁니다.")
    private String name;

    private String part;

    private Double generation;

}
