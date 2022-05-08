package org.el.modules.news.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.modules.news.domain.NewsComment;
import org.el.modules.news.service.dto.NewsCommentDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:37+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NewsCommentMapperImpl implements NewsCommentMapper {

    @Override
    public NewsComment toEntity(NewsCommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewsComment newsComment = new NewsComment();

        newsComment.setId( dto.getId() );
        newsComment.setCreateBy( dto.getCreateBy() );
        newsComment.setCreateTime( dto.getCreateTime() );
        newsComment.setUpdateBy( dto.getUpdateBy() );
        newsComment.setUpdateTime( dto.getUpdateTime() );
        newsComment.setNewsId( dto.getNewsId() );
        newsComment.setContent( dto.getContent() );

        return newsComment;
    }

    @Override
    public NewsCommentDto toDto(NewsComment entity) {
        if ( entity == null ) {
            return null;
        }

        NewsCommentDto newsCommentDto = new NewsCommentDto();

        newsCommentDto.setId( entity.getId() );
        newsCommentDto.setCreateBy( entity.getCreateBy() );
        newsCommentDto.setCreateTime( entity.getCreateTime() );
        newsCommentDto.setUpdateBy( entity.getUpdateBy() );
        newsCommentDto.setUpdateTime( entity.getUpdateTime() );
        newsCommentDto.setNewsId( entity.getNewsId() );
        newsCommentDto.setContent( entity.getContent() );

        return newsCommentDto;
    }

    @Override
    public List<NewsComment> toEntity(List<NewsCommentDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsComment> list = new ArrayList<NewsComment>( dtoList.size() );
        for ( NewsCommentDto newsCommentDto : dtoList ) {
            list.add( toEntity( newsCommentDto ) );
        }

        return list;
    }

    @Override
    public List<NewsCommentDto> toDto(List<NewsComment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsCommentDto> list = new ArrayList<NewsCommentDto>( entityList.size() );
        for ( NewsComment newsComment : entityList ) {
            list.add( toDto( newsComment ) );
        }

        return list;
    }
}
