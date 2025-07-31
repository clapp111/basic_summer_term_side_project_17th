package server.board.domain.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.board.domain.recommendation.entity.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {
}
