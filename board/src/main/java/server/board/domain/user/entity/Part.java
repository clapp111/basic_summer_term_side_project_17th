package server.board.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import server.board.global.exception.error.CustomErrorCode;
import server.board.global.exception.error.RestApiException;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Part {

    Basic("베이직"),
    Server("서버"),
    Web("웹"),
    Android("안드로이드"),
    IOS("IOS"),
    Design("디자인");

    private final String label;

    public static Part from(String part){
        return Arrays.stream(values())
                .filter(p -> p.getLabel().equals(part))
                .findAny()
                .orElseThrow(() -> new RestApiException(CustomErrorCode.INVALID_PART_VALUE));
    }

}