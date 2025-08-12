package server.board.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.board.global.exception.error.CustomErrorCode;
import server.board.global.exception.error.RestApiException;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Part {

    Basic("basic"),
    Server("server"),
    Web("web"),
    Android("android"),
    IOS("ios"),
    Design("design");

    private final String label;

    public static Part from(String part){
        return Arrays.stream(values())
                .filter(p -> p.getLabel().equals(part))
                .findAny()
                .orElseThrow(() -> new RestApiException(CustomErrorCode.INVALID_PART_VALUE));
    }

}