package server.board.domain.recommendation.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.user.entity.User;

@Table(name = "recommendation")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @Builder
    private Recommendation(Long id, User user, Assignment assignment) {
        this.id = id;
        this.user = user;
        this.assignment = assignment;
    }

    public static Recommendation create(User user, Assignment assignment) {
        return Recommendation.builder()
                .user(user)
                .assignment(assignment)
                .build();
    }
}
