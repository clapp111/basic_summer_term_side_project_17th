package server.board.domain.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import server.board.domain.assignment.entity.Assignment;

import java.time.format.DateTimeFormatter;

@Getter
@ToString
@Schema(description = "과제 정보 응답 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignmentResponseDto {

    @Schema(description = "유저 ID", example = "1")
    private Long id;

    @Schema(description = "제목", example = "TITLE")
    private String title;

    @Schema(description = "작성자", example = "홍길동")
    private String writer;

    @Schema(description = "내용", example = "CONTENT")
    private String content;

    @Schema(description = "링크", example = "https://my-assignmnet-1.com")
    private String link;

    @Schema(description = "생성일", example = "2025-08-05 14:43:11")
    private String createdAt;

    @Schema(description = "수정일", example = "2025-08-10 21:45:23")
    private String modifiedAt;

    @Schema(description = "추천수", example = "8")
    private Integer recommendations;

    @Schema(description = "추천 여부", example = "YES")
    private Boolean isRecommended;

    @Builder
    private AssignmentResponseDto(Long id, String title, String writer, String content, String link,
                                  String createdAt, String modifiedAt,Integer recommendations, Boolean isRecommended) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.link = link;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.recommendations = recommendations;
        this.isRecommended = isRecommended;
    }

    public static AssignmentResponseDto create(Assignment assignment, Boolean isRecommended) {
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
                .isRecommended(isRecommended)
                .build();
    }
}
