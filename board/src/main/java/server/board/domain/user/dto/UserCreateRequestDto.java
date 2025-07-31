package server.board.domain.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequestDto {

    private String email;

    private String password;

    private String name;

    private String part;

    private Double generation;

}
