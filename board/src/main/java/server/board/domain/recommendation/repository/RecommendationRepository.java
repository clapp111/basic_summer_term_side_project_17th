package server.board.domain.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.board.domain.recommendation.entity.Recommendation;

import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {
    // 유저 ID, 과제 ID를 통해 조회
    Optional<Recommendation> findByUserIdAndAssignmentId(Long userId, Long assignmentId);
}
