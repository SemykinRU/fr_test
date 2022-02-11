package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import ru.semykin.fr.test.dto.UserResponseDto;
import ru.semykin.fr.test.entity.UserResponse;

import java.util.List;

@Mapper
public interface UserResponseMapper {

    UserResponseDto toUserResponseDto(UserResponse entity);

    UserResponse toUserResponseEntity(UserResponseDto dto);

    List<UserResponseDto> toUserResponseDtoList(Iterable<UserResponse> entityIterable);

    List<UserResponse> toUserResponseEntityList(Iterable<UserResponseDto> dtoIterable);
}
