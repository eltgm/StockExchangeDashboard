package ru.sultanyarov.stockexchangedashboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sultanyarov.stockexchangedashboard.domain.User;

@Mapper
public interface UserMapper {

    @Mapping(target = "name", source = "username")
    @Mapping(target = "id", source = "userId")
    ru.sultanyarov.stockexchangedashboard.user.dto.User domainUserToDto(User user);
}
