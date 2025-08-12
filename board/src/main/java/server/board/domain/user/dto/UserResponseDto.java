package server.board.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import server.board.domain.user.entity.User;

@Getter
@ToString
@Schema(description = "유저 정보 응답 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    @Schema(description = "유저 ID", example = "1")
    private Long id;

    @Schema(description = "이메일", example = "appcenter17@inu.ac.kr")
    private String email;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "파트", example = "베이직")
    private String part;

    @Schema(description = "기수", example = "17.5")
    private Double generation;

    @Builder
    private UserResponseDto(Long id, String email, String name, String part, Double generation) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.part = part;
        this.generation = generation;
    }

    public static UserResponseDto create(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .part(user.getPart().getLabel())
                .generation(user.getGeneration())
                .build();
    }
}