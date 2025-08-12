package server.board.domain.assignment.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.board.domain.assignment.controller.docs.AssignmentControllerSpecification;
import server.board.domain.assignment.dto.AssignmentCreateRequestDto;
import server.board.domain.assignment.dto.AssignmentResponseDto;
import server.board.domain.assignment.service.AssignmentService;
import server.board.domain.recommendation.service.RecommendationService;
import server.board.domain.user.entity.UserDetailsImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assignments")
public class AssignmentController implements AssignmentControllerSpecification {

    public final AssignmentService assignmentService;
    public final RecommendationService recommendationService;

    // 전체 과제 조회(/api/assignments?sort={options})
    @GetMapping
    public ResponseEntity<List<AssignmentResponseDto>> getAllAssignmentsInfo(
            @PageableDefault Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl  userDetails,
            @RequestParam(value = "sort", defaultValue = "createdAt") String options) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.findAllOrderByOption(pageable, options, userDetails));
    }

    // 과제 제출(/api/assignments)
    @PostMapping
    public ResponseEntity<AssignmentResponseDto> submitAssignment(@AuthenticationPrincipal UserDetailsImpl  userDetails,
                                              @Valid @RequestBody AssignmentCreateRequestDto assignmentCreateRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.submit(assignmentCreateRequestDto, userDetails));
    }

    // 과제 수정(/api/assignments/{assignmentId})
    @PutMapping("/{assignmentId}")
    public ResponseEntity<AssignmentResponseDto> modifyAssignment(@PathVariable Long assignmentId,
                                              @AuthenticationPrincipal UserDetailsImpl  userDetails,
                                              @Valid @RequestBody AssignmentCreateRequestDto assignmentCreateRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(assignmentService.modify(assignmentId, assignmentCreateRequestDto, userDetails));
    }

    // 과제 삭제(/api/assignments/{assignmentId})
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long assignmentId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        assignmentService.delete(assignmentId, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 과제 추천 추가(/api/assignments/{assignmentId}/recommendation)
    @PostMapping("/{assignmentId}/recommendation")
    public ResponseEntity<AssignmentResponseDto> addRecommendation(@PathVariable Long assignmentId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendationService.addRecommendation(assignmentId, userDetails));
    }

    // 과제 추천 삭제(/api/assignments/{assignmentId}/recommendation)
    @DeleteMapping("/{assignmentId}/recommendation")
    public ResponseEntity<?> deleteRecommendation(@PathVariable Long assignmentId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        recommendationService.deleteRecommendation(assignmentId, userDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
