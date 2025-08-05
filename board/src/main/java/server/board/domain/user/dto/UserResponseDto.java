package server.board.domain.user.dto;

import lombok.*;
import server.board.domain.user.entity.User;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponseDto {

    private Long id;

    private String email;

    private String name;

    private String part;

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