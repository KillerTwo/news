package org.el.modules.news.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.el.modules.news.domain.NewsLink;
import org.el.modules.news.service.dto.NewsLinkDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T01:06:17+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NewsLinkMapperImpl implements NewsLinkMapper {

    @Override
    public NewsLink toEntity(NewsLinkDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewsLink newsLink = new NewsLink();

        newsLink.setId( dto.getId() );
        newsLink.setCreateBy( dto.getCreateBy() );
        newsLink.setCreateTime( dto.getCreateTime() );
        newsLink.setUpdateBy( dto.getUpdateBy() );
        newsLink.setUpdateTime( dto.getUpdateTime() );
        newsLink.setLinkName( dto.getLinkName() );
        newsLink.setLinkUrl( dto.getLinkUrl() );

        return newsLink;
    }

    @Override
    public NewsLinkDto toDto(NewsLink entity) {
        if ( entity == null ) {
            return null;
        }

        NewsLinkDto newsLinkDto = new NewsLinkDto();

        newsLinkDto.setId( entity.getId() );
        newsLinkDto.setCreateBy( entity.getCreateBy() );
        newsLinkDto.setCreateTime( entity.getCreateTime() );
        newsLinkDto.setUpdateBy( entity.getUpdateBy() );
        newsLinkDto.setUpdateTime( entity.getUpdateTime() );
        newsLinkDto.setLinkName( entity.getLinkName() );
        newsLinkDto.setLinkUrl( entity.getLinkUrl() );

        return newsLinkDto;
    }

    @Override
    public List<NewsLink> toEntity(List<NewsLinkDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsLink> list = new ArrayList<NewsLink>( dtoList.size() );
        for ( NewsLinkDto newsLinkDto : dtoList ) {
            list.add( toEntity( newsLinkDto ) );
        }

        return list;
    }

    @Override
    public List<NewsLinkDto> toDto(List<NewsLink> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsLinkDto> list = new ArrayList<NewsLinkDto>( entityList.size() );
        for ( NewsLink newsLink : entityList ) {
            list.add( toDto( newsLink ) );
        }

        return list;
    }
}
