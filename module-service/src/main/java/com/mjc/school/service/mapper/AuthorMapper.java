package com.mjc.school.service.mapper;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.repository.model.AuthorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    AuthorDto toDto(AuthorModel authorModel);
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    AuthorModel toModel(AuthorDto authorDto);

    List<AuthorDto> toListDto(List<AuthorModel> authorModelList);
}
