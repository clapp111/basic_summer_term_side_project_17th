package server.board.domain.assignment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.board.domain.assignment.dto.AssignmentCreateRequestDto;
import server.board.domain.recommendation.entity.Recommendation;
import server.board.domain.user.entity.User;

import java.time.LocalDate;
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

    @Column(name = "content")
    private String content;

    @Column(name = "link")
    private String link;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recommendation> recommendationList =  new ArrayList<>();

    @Builder
    private Assignment(Long id, String title, String content, String link, LocalDate createdAt, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Assignment create(AssignmentCreateRequestDto assignmentCreateRequestDto, User user){
        return Assignment.builder()
                .title(assignmentCreateRequestDto.getTitle())
                .content(assignmentCreateRequestDto.getContent())
                .link(assignmentCreateRequestDto.getLink())
                .createdAt(LocalDate.now())
                .user(user)
                .build();
    }
}
