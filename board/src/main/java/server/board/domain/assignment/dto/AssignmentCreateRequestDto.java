package server.board.domain.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "과제 생성 요청 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentCreateRequestDto {

    @Schema(description = "제목", example = "TITLE")
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @Schema(description = "내용", example = "CONTENT")
    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @Schema(description = "링크", example = "https://my-assignmnet-1.com")
    @NotBlank(message = "링크는 필수입니다.")
    private String link;

}
