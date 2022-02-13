package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.entity.UserResponse;

import java.util.List;

@Mapper
public interface UserResponseMapper {

    @Mapping(target = "answers", ignore = true)
    UserResponseDto toUserResponseDto(UserResponse entity);

    @Mapping(target = "answers", ignore = true)
    UserResponse toUserResponseEntity(UserResponseDto dto);

    @Mapping(target = "answers", ignore = true)
    List<UserResponseDto> toUserResponseDtoList(Iterable<UserResponse> entityIterable);

    @Mapping(target = "answers", ignore = true)
    List<UserResponse> toUserResponseEntityList(Iterable<UserResponseDto> dtoIterable);
}
