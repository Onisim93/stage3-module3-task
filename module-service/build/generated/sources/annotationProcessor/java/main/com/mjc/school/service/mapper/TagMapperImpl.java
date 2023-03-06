package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.TagDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-06T19:57:38+0400",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public TagDto toDto(TagModel tagModel) {
        if ( tagModel == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( tagModel.getId() );
        tagDto.setName( tagModel.getName() );
        tagDto.setNews( newsMapper.toListDto( tagModel.getNews() ) );

        return tagDto;
    }

    @Override
    public TagModel toModel(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        TagModel tagModel = new TagModel();

        tagModel.setId( tagDto.getId() );
        tagModel.setName( tagDto.getName() );

        return tagModel;
    }

    @Override
    public List<TagDto> toListDto(List<TagModel> tagModelList) {
        if ( tagModelList == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( tagModelList.size() );
        for ( TagModel tagModel : tagModelList ) {
            list.add( toDto( tagModel ) );
        }

        return list;
    }

    @Override
    public List<TagModel> toListModel(List<TagDto> tagDtoList) {
        if ( tagDtoList == null ) {
            return null;
        }

        List<TagModel> list = new ArrayList<TagModel>( tagDtoList.size() );
        for ( TagDto tagDto : tagDtoList ) {
            list.add( toModel( tagDto ) );
        }

        return list;
    }
}
