package server.board.domain.assignment.dto;

import lombok.*;
import server.board.domain.assignment.entity.Assignment;

import java.time.format.DateTimeFormatter;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private String link;
    private String createdAt;
    private String modifiedAt;
    private Integer recommendations;

    @Builder
    private AssignmentResponseDto(Long id, String title, String writer, String content, String link,
                                  String createdAt, String modifiedAt, Integer recommendations) {
        this.id = id;
        this.title = title;
        this.writer = writer;
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
                .writer(assignment.getUser().getName())
                .content(assignment.getContent())
                .link(assignment.getLink())
                .createdAt(assignment.getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .modifiedAt(assignment.getModifiedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .recommendations(assignment.getRecommendationList().size())
                .build();
    }
}
