package server.board.domain.assignment.dto;

import lombok.*;
import server.board.domain.assignment.entity.Assignment;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentResponseDto {

    private Long id;
    private String title;
    private String content;
    private String link;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private Integer recommendations;

    @Builder
    private AssignmentResponseDto(Long id, String title, String content, String link,
                                 LocalDate createdAt, LocalDate modifiedAt, Integer recommendations) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.recommendations = recommendations;
    }

    public static AssignmentResponseDto create(Assignment assignment) {
        return AssignmentResponseDto.builder()
                .id(assignment.getId())
                .title(assignment.getTitle())
                .content(assignment.getContent())
                .link(assignment.getLink())
                .createdAt(assignment.getCreatedAt())
                .modifiedAt(assignment.getModifiedAt())
                .recommendations(assignment.getRecommendations())
                .build();
    }
}
