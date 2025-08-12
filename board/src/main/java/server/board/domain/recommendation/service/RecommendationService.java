package server.board.domain.recommendation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.board.domain.assignment.dto.AssignmentResponseDto;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.assignment.repository.AssignmentRepository;
import server.board.domain.recommendation.entity.Recommendation;
import server.board.domain.recommendation.repository.RecommendationRepository;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.global.exception.error.RestApiException;

import static server.board.global.exception.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    public final AssignmentRepository assignmentRepository;
    public final RecommendationRepository recommendationRepository;

    @Transactional
    public AssignmentResponseDto addRecommendation(Long assignmentId, UserDetailsImpl userDetails) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RestApiException(ASSIGNMENT_NOT_FOUND));

        // 이미 추천했다면 중복 추천 불가
        if(recommendationRepository.findByUserIdAndAssignmentId(userDetails.getUser().getId(), assignmentId).isPresent()){
            throw new RestApiException(ALREADY_RECOMMEND);
        }

        // 추천 엔티티 생성
        Recommendation recommendation = Recommendation.create(userDetails.getUser(), assignment);
        recommendationRepository.save(recommendation);

        return AssignmentResponseDto.create(assignment, Boolean.TRUE);
    }

    @Transactional
    public void deleteRecommendation(Long assignmentId, UserDetailsImpl userDetails) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RestApiException(ASSIGNMENT_NOT_FOUND));

        // 추천하지 않았다면 추천 취소 불가
        Recommendation recommendation = recommendationRepository.findByUserIdAndAssignmentId(userDetails.getUser().getId(), assignmentId)
                .orElseThrow(() -> new RestApiException(RECOMMENDATION_NOT_FOUND));

        // 추천 엔티티 삭제
        recommendationRepository.delete(recommendation);
    }

}
