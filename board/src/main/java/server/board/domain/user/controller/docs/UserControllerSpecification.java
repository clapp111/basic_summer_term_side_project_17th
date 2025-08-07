package server.board.domain.user.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import server.board.domain.user.dto.UserResponseDto;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.global.exception.error.ErrorResponse;

import java.util.List;

@Tag(name = "UserController", description = "User management Controller")
public interface UserControllerSpecification {

    @Operation(summary = "search all users", description = "모든 유저 조회 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "✅ 전체 유저 조회 성공"),
            @ApiResponse(responseCode = "404", description = "❌ 전체 유저 조회 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsersInfo();


    @Operation(summary = "search part users", description = "파트 유저 조회 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "✅ 파트 유저 조회 성공"),
            @ApiResponse(responseCode = "400", description = "❌ 파트 유저 조회 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                @ExampleObject(
                                        name = "유효하지 않은 파트",
                                        value = "{\"error\" : \"400\", \"message\" : \"유효하지 않은 파트입니다\"}"
                                )
                            }, schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "❌ 파트 유저 조회 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping("/{parts}")
    public ResponseEntity<List<UserResponseDto>> getPartUsersInfo(@PathVariable String parts);


    @Operation(summary = "search me", description = "본인 조회 시 사용되는 api")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "✅ 본인 조회 성공"),
            @ApiResponse(responseCode = "404", description = "❌ 본인 조회 실패",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "유저 조회 실패",
                                            value = "{\"error\" : \"404\", \"message\" : \"유저 조회에 실패하였습니다\"}"
                                    )

                            }, schema = @Schema(implementation = ErrorResponse.class)))

    })
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails);
}
