package ru.itis.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.itis.model.RoleEntity;

import java.util.Set;

@Mapper(componentModel = "spring", uses = PrivilegeMapper.class, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper {

    @Mapping(target = "roleDescription", source = "role.description")
    RoleResponse toResponse(RoleEntity role);

    Set<RoleResponse> toResponse(Set<RoleEntity> roles);
}
