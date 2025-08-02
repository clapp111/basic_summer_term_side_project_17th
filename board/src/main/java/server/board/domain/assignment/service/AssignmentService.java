package server.board.domain.assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.board.domain.assignment.dto.AssignmentCreateRequestDto;
import server.board.domain.assignment.dto.AssignmentResponseDto;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.assignment.repository.AssignmentRepository;
import server.board.domain.recommendation.repository.RecommendationRepository;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.domain.user.repository.UserRepository;
import server.board.global.exception.error.RestApiException;

import java.util.ArrayList;
import java.util.List;

import static server.board.global.exception.error.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;
    private final RecommendationRepository recommendationRepository;

    @Transactional(readOnly = true)
    public List<AssignmentResponseDto> findAllOrderByOption(Pageable pageable, String options) {
        Page<Assignment> assignmentPage;

        // 추천순 정렬
        if ("recommendations".equals(options)) {
            assignmentPage = assignmentRepository.findAllOrderByRecommendation(pageable);
        }
        // 날짜순(default) 정렬
        else {
            assignmentPage = assignmentRepository.findAllOrderByCreatedAt(pageable);
        }
        List<AssignmentResponseDto> assignmentResponseDtoList = new ArrayList<>();
        for (Assignment assignment : assignmentPage.getContent()){
            assignmentResponseDtoList.add(AssignmentResponseDto.create(assignment));
        }
        return assignmentResponseDtoList;
    }

    @Transactional
    public void submit(AssignmentCreateRequestDto assignmentCreateRequestDto, UserDetailsImpl userDetails) {
        Assignment assignment = Assignment.create(assignmentCreateRequestDto, userDetails.getUser());
        assignmentRepository.save(assignment);
    }

    @Transactional
    public void modify(Long assignmentId, AssignmentCreateRequestDto assignmentCreateRequestDto, UserDetailsImpl userDetails) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RestApiException(ASSIGNMENT_NOT_FOUND));

        if(!assignment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new RestApiException(ASSIGNMENT_MODIFY_FORBIDDEN);
        }
        assignment.modify(assignmentCreateRequestDto);
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


}
