package org.el.modules.news.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.modules.news.domain.NewsTag;
import org.el.modules.news.service.dto.NewsTagDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NewsTagMapperImpl implements NewsTagMapper {

    @Override
    public NewsTag toEntity(NewsTagDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewsTag newsTag = new NewsTag();

        newsTag.setId( dto.getId() );
        newsTag.setCreateBy( dto.getCreateBy() );
        newsTag.setCreateTime( dto.getCreateTime() );
        newsTag.setUpdateBy( dto.getUpdateBy() );
        newsTag.setUpdateTime( dto.getUpdateTime() );
        newsTag.setTagName( dto.getTagName() );
        newsTag.setTagCode( dto.getTagCode() );

        return newsTag;
    }

    @Override
    public NewsTagDto toDto(NewsTag entity) {
        if ( entity == null ) {
            return null;
        }

        NewsTagDto newsTagDto = new NewsTagDto();

        newsTagDto.setId( entity.getId() );
        newsTagDto.setCreateBy( entity.getCreateBy() );
        newsTagDto.setCreateTime( entity.getCreateTime() );
        newsTagDto.setUpdateBy( entity.getUpdateBy() );
        newsTagDto.setUpdateTime( entity.getUpdateTime() );
        newsTagDto.setTagName( entity.getTagName() );
        newsTagDto.setTagCode( entity.getTagCode() );

        return newsTagDto;
    }

    @Override
    public List<NewsTag> toEntity(List<NewsTagDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsTag> list = new ArrayList<NewsTag>( dtoList.size() );
        for ( NewsTagDto newsTagDto : dtoList ) {
            list.add( toEntity( newsTagDto ) );
        }

        return list;
    }

    @Override
    public List<NewsTagDto> toDto(List<NewsTag> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsTagDto> list = new ArrayList<NewsTagDto>( entityList.size() );
        for ( NewsTag newsTag : entityList ) {
            list.add( toDto( newsTag ) );
        }

        return list;
    }
}
