package server.board.domain.assignment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentCreateRequestDto {

    private String title;

    private String content;

    private String link;

}
