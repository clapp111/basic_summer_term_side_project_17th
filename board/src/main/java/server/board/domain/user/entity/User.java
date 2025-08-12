package server.board.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.board.domain.assignment.entity.Assignment;
import server.board.domain.recommendation.entity.Recommendation;
import server.board.domain.user.dto.UserCreateRequestDto;
import server.board.domain.user.enums.Part;

import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "part")
    private Part part;

    @Column(name = "generation")
    private Double generation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Assignment> assignmentList =  new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recommendation> recommendationList =  new ArrayList<>();

    @Builder
    private User(Long id, String email, String password, String name, Part part, Double generation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.part = part;
        this.generation = generation;
    }

    public static User create(UserCreateRequestDto userCreateRequestDto, String encodedPassword){
        return User.builder()
                .email(userCreateRequestDto.getEmail())
                .password(encodedPassword)
                .name(userCreateRequestDto.getName())
                .part(Part.from(userCreateRequestDto.getPart()))
                .generation(userCreateRequestDto.getGeneration())
                .build();
    }
}
