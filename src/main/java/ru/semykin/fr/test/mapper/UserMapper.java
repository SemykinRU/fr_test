package ru.semykin.fr.test.mapper;

import org.mapstruct.Mapper;
import ru.semykin.fr.test.dto.UserDto;
import ru.semykin.fr.test.entity.User;


import java.util.List;

@Mapper
public interface UserMapper {

    UserDto toUserDto(User entity);

    User toUserEntity(UserDto dto);

    List<UserDto> toUserDtoList(Iterable<User> entityIterable);

    List<User> toUserEntityList(Iterable<UserDto> dtoIterable);
}
