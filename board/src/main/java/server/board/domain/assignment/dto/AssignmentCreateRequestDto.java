package server.board.domain.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentCreateRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 50, message = "입력된 제목의 길이가 너무 깁니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotBlank(message = "링크는 필수입니다.")
    private String link;

}
