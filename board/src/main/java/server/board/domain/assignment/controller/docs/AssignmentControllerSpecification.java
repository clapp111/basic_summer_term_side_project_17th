package server.board.domain.assignment.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.board.domain.assignment.dto.AssignmentCreateRequestDto;
import server.board.domain.assignment.dto.AssignmentResponseDto;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.global.exception.error.ErrorResponse;

import java.util.List;

@Tag(name = "AssignmentController", description = "Assignment management Controller")
public interface AssignmentControllerSpecification {
    @Operation(summary = "search all assignments", description = "과제 조회 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "✅ 전체 과제 조회 성공"),
            @ApiResponse(responseCode = "404", description = "❌ 전체 과제 조회 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    ),
                                    @ExampleObject(
                                            name = "과제 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"과제 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping
    public ResponseEntity<List<AssignmentResponseDto>> getAllAssignmentsInfo(
            @PageableDefault Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(value = "sort", defaultValue = "createdAt") String options);


    @Operation(summary = "submit assignment", description = "과제 제출 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "✅ 과제 제출 성공"),
            @ApiResponse(responseCode = "400", description = "❌ 과제 제출 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유효성 검사 실패",
                                            value = "{\"error\" : \"400\", \"message\" : \"유효성 검사에 실패하였습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "❌ 과제 제출 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<?> submitAssignment(@AuthenticationPrincipal UserDetailsImpl  userDetails,
                                              @Valid @RequestBody AssignmentCreateRequestDto assignmentCreateRequestDto);


    @Operation(summary = "modify assignment", description = "과제 수정 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "✅ 과제 수정 성공"),
            @ApiResponse(responseCode = "400", description = "❌ 과제 수정 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유효성 검사 실패",
                                            value = "{\"error\" : \"400\", \"message\" : \"유효성 검사에 실패하였습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "❌ 과제 수정 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "과제 수정 실패",
                                            value = "{\"error\" : \"403\", \"message\" : \"과제를 수정할 권한이 없습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "❌ 과제 수정 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{assignmentId}")
    public ResponseEntity<?> modifyAssignment(@PathVariable Long assignmentId,
                                              @AuthenticationPrincipal UserDetailsImpl  userDetails,
                                              @Valid @RequestBody AssignmentCreateRequestDto assignmentCreateRequestDto);


    @Operation(summary = "delete assignment", description = "과제 삭제 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "✅ 과제 삭제 성공"),
            @ApiResponse(responseCode = "403", description = "❌ 과제 삭제 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "과제 삭제 실패",
                                            value = "{\"error\" : \"403\", \"message\" : \"과제를 삭제할 권한이 없습니다\"}"
                                    )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "❌ 과제 삭제 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    ),
                                    @ExampleObject(
                                            name = "과제 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"과제 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long assignmentId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails);


    @Operation(summary = "add recommendation to assignment", description = "과제 추천 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "✅ 과제 추천 성공"),
            @ApiResponse(responseCode = "404", description = "❌ 과제 추천 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    ),
                                    @ExampleObject(
                                            name = "과제 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"과제 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "❌ 과제 추천 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "중복 추천",
                                            value = "{\"error\" : \"409\", \"message\" : \"중복으로 추천할 수 없습니다.\"}"
                                    )
                            },schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/{assignmentId}/recommendation")
    public ResponseEntity<?> addRecommendation(@PathVariable Long assignmentId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails);


    @Operation(summary = "delete recommendation to assignment", description = "과제 추천 취소 시 api")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "✅ 과제 추천 취소 성공"),
            @ApiResponse(responseCode = "404", description = "❌ 과제 추천 취소 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    ),
                                    @ExampleObject(
                                            name = "과제 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"과제 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{assignmentId}/recommendation")
    public ResponseEntity<?> deleteRecommendation(@PathVariable Long assignmentId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails);
}
