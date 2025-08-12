package server.board.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    //400 Bad Request
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 인자값입니다"),
    INVALID_PART_VALUE(HttpStatus.BAD_REQUEST, 400, "유효하지 않은 파트입니다."),
    INVALID_PASSWORD_VALUE(HttpStatus.BAD_REQUEST, 400, "일치하지 않은 패스워드입니다."),

    // 403 Forbidden
    ASSIGNMENT_MODIFY_FORBIDDEN(HttpStatus.FORBIDDEN, 403, "로그인한 유저의 해당 과제 수정 권한이 없음"),
    ASSIGNMENT_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, 403, "로그인한 유저의 해당 과제 삭제 권한이 없음"),

    // 404 NOT_FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404,"유저가 존재하지 않습니다"),
    ASSIGNMENT_NOT_FOUND(HttpStatus.NOT_FOUND, 404,"과제가 존재하지 않습니다"),
    RECOMMENDATION_NOT_FOUND(HttpStatus.NOT_FOUND, 404,"추천 기록이 존재하지 않습니다."),

    // 409 CONFLICT
    DUPLICATE_USER(HttpStatus.CONFLICT, 409, "이미 가입된 이메일입니다."),
    ALREADY_RECOMMEND(HttpStatus.CONFLICT, 409, "이미 추천한 과제입니다."),

    // 500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 내부 오류 발생");

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

}
