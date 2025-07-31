package server.board.domain.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.board.domain.assignment.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
