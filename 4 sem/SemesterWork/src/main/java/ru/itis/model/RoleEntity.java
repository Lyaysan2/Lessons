package ru.itis.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.dto.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Role role;

}
