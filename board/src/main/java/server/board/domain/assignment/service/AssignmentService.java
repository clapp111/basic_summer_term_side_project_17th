package server.board.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.board.domain.assignment.dto.AssignmentCreateRequestDto;
import server.board.domain.assignment.dto.AssignmentResponseDto;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.assignment.repository.AssignmentRepository;
import server.board.domain.recommendation.repository.RecommendationRepository;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.global.exception.error.RestApiException;

import java.util.ArrayList;
import java.util.List;

import static server.board.global.exception.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final RecommendationRepository recommendationRepository;

    @Transactional(readOnly = true)
    public List<AssignmentResponseDto> findAllOrderByOption(Pageable pageable, String options, UserDetailsImpl userDetails) {
        Page<Assignment> assignmentPage;

        // 추천순 정렬
        if ("recommendation".equals(options)) {
            // Pageable 에서 정렬 조건을 제외하고 페이징 정보만 사용한 새로운 Pageable 객체 생성
            Pageable newPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            assignmentPage = assignmentRepository.findAllOrderByRecommendation(newPageable);
        }
        // 날짜순(default) 정렬
        else assignmentPage = assignmentRepository.findAllOrderByCreatedAt(pageable);

        List<AssignmentResponseDto> assignmentResponseDtoList = new ArrayList<>();
        for (Assignment assignment : assignmentPage.getContent()){
            assignmentResponseDtoList.add(AssignmentResponseDto.create(assignment, checkRecommended(userDetails, assignment)));
        }
        return assignmentResponseDtoList;
    }

    @Transactional
    public AssignmentResponseDto submit(AssignmentCreateRequestDto assignmentCreateRequestDto, UserDetailsImpl userDetails) {
        Assignment assignment = Assignment.create(assignmentCreateRequestDto, userDetails.getUser());
        assignmentRepository.save(assignment);
        return AssignmentResponseDto.create(assignment, Boolean.FALSE);
    }

    @Transactional
    public AssignmentResponseDto modify(Long assignmentId, AssignmentCreateRequestDto assignmentCreateRequestDto, UserDetailsImpl userDetails) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RestApiException(ASSIGNMENT_NOT_FOUND));

        if(!assignment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new RestApiException(ASSIGNMENT_MODIFY_FORBIDDEN);
        }
        assignment.modify(assignmentCreateRequestDto);

        return AssignmentResponseDto.create(assignment, checkRecommended(userDetails, assignment));
    }

    @Transactional
    public void delete(Long assignmentId, UserDetailsImpl userDetails) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RestApiException(ASSIGNMENT_NOT_FOUND));
        if(!assignment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new RestApiException(ASSIGNMENT_DELETE_FORBIDDEN);
        }
        assignmentRepository.delete(assignment);
    }

    // 사용자가 해당 과제에 추천을 했는지 여부를 확인하는 메서드
    private Boolean checkRecommended(UserDetailsImpl userDetails, Assignment assignment){
        Boolean isRecommended = Boolean.FALSE;
        if (recommendationRepository.findByUserIdAndAssignmentId(userDetails.getUser().getId(), assignment.getId()).isPresent()){
            isRecommended = Boolean.TRUE;
        }
        return isRecommended;
    }
}
