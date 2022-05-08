package org.el.modules.news.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.modules.news.domain.NewsArticleTag;
import org.el.modules.news.service.dto.NewsArticleTagDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:37+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NewsArticleTagMapperImpl implements NewsArticleTagMapper {

    @Override
    public NewsArticleTag toEntity(NewsArticleTagDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewsArticleTag newsArticleTag = new NewsArticleTag();

        newsArticleTag.setId( dto.getId() );
        newsArticleTag.setArticleId( dto.getArticleId() );
        newsArticleTag.setTagId( dto.getTagId() );

        return newsArticleTag;
    }

    @Override
    public NewsArticleTagDto toDto(NewsArticleTag entity) {
        if ( entity == null ) {
            return null;
        }

        NewsArticleTagDto newsArticleTagDto = new NewsArticleTagDto();

        newsArticleTagDto.setId( entity.getId() );
        newsArticleTagDto.setArticleId( entity.getArticleId() );
        newsArticleTagDto.setTagId( entity.getTagId() );

        return newsArticleTagDto;
    }

    @Override
    public List<NewsArticleTag> toEntity(List<NewsArticleTagDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsArticleTag> list = new ArrayList<NewsArticleTag>( dtoList.size() );
        for ( NewsArticleTagDto newsArticleTagDto : dtoList ) {
            list.add( toEntity( newsArticleTagDto ) );
        }

        return list;
    }

    @Override
    public List<NewsArticleTagDto> toDto(List<NewsArticleTag> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsArticleTagDto> list = new ArrayList<NewsArticleTagDto>( entityList.size() );
        for ( NewsArticleTag newsArticleTag : entityList ) {
            list.add( toDto( newsArticleTag ) );
        }

        return list;
    }
}
