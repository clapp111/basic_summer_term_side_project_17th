package server.board.domain.assignment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentResponseDto {

    private Long id;

    private String title;

    private String content;

    private String link;

    private LocalDate createAt;
}
