package server.board.domain.assignment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.board.domain.assignment.dto.AssignmentCreateRequestDto;
import server.board.domain.recommendation.entity.Recommendation;
import server.board.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "assignment")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recommendation> recommendationList =  new ArrayList<>();

    @Builder
    private Assignment(Long id, String title, String content, String link,
                       LocalDateTime createdAt, LocalDateTime modifiedAt,User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.user = user;
    }

    public static Assignment create(AssignmentCreateRequestDto assignmentCreateRequestDto, User user){
        return Assignment.builder()
                .title(assignmentCreateRequestDto.getTitle())
                .content(assignmentCreateRequestDto.getContent())
                .link(assignmentCreateRequestDto.getLink())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .user(user)
                .build();
    }

    public void modify(AssignmentCreateRequestDto assignmentCreateRequestDto) {
        this.title = assignmentCreateRequestDto.getTitle();
        this.content = assignmentCreateRequestDto.getContent();
        this.link = assignmentCreateRequestDto.getLink();
        this.modifiedAt = LocalDateTime.now();
    }
}
