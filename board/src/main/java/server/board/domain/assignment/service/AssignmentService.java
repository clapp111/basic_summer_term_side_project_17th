package server.board.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.board.domain.assignment.repository.AssignmentRepository;
import server.board.domain.recommendation.repository.RecommendationRepository;
import server.board.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;
    private final RecommendationRepository recommendationRepository;

}
