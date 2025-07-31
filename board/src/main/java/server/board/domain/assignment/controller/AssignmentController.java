package server.board.domain.assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.board.domain.assignment.dto.AssignmentResponseDto;
import server.board.domain.assignment.service.AssignmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assignments")
public class AssignmentController {

    public final AssignmentService assignmentService;

    // 전체 과제 조회(/api/assignments?sort={options})
    @GetMapping
    public ResponseEntity<List<AssignmentResponseDto>> getAllAssignmentsInfo() {

    }

    // 과제 제출(/api/assignments)
    @PostMapping
    public ResponseEntity<?> submitAssignment() {

    }

    // 과제 수정(/api/assignments/{assignmentId})
    @PutMapping("/{assignmentId}")
    public ResponseEntity<?> modifyAssignment() {

    }

    // 과제 삭제(/api/assignments/{assignmentId})
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<?> deleteAssignment() {

    }

    // 과제 추천 추가(/api/assignments/{assignmentId}/recommendation)
    @PostMapping("/{assignmentId}/recommendation")
    public ResponseEntity<?> addRecommendation() {

    }

    // 과제 추천 삭제(/api/assignments/{assignmentId}/recommendation)
    @DeleteMapping("/{assignmentId}/recommendation")
    public ResponseEntity<?> deleteRecommendation() {

    }

}
