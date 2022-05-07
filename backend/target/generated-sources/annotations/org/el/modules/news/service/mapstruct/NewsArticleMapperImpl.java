package org.el.modules.news.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.el.modules.news.domain.NewsArticle;
import org.el.modules.news.service.dto.NewsArticleDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T01:06:17+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NewsArticleMapperImpl implements NewsArticleMapper {

    @Override
    public NewsArticle toEntity(NewsArticleDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewsArticle newsArticle = new NewsArticle();

        newsArticle.setId( dto.getId() );
        newsArticle.setCreateBy( dto.getCreateBy() );
        newsArticle.setCreateTime( dto.getCreateTime() );
        newsArticle.setUpdateBy( dto.getUpdateBy() );
        newsArticle.setUpdateTime( dto.getUpdateTime() );
        newsArticle.setTitle( dto.getTitle() );
        newsArticle.setExcerpt( dto.getExcerpt() );
        newsArticle.setCategory( dto.getCategory() );
        newsArticle.setImg( dto.getImg() );
        newsArticle.setContent( dto.getContent() );
        newsArticle.setAuthor( dto.getAuthor() );
        newsArticle.setViews( dto.getViews() );

        return newsArticle;
    }

    @Override
    public NewsArticleDto toDto(NewsArticle entity) {
        if ( entity == null ) {
            return null;
        }

        NewsArticleDto newsArticleDto = new NewsArticleDto();

        newsArticleDto.setId( entity.getId() );
        newsArticleDto.setCreateBy( entity.getCreateBy() );
        newsArticleDto.setCreateTime( entity.getCreateTime() );
        newsArticleDto.setUpdateBy( entity.getUpdateBy() );
        newsArticleDto.setUpdateTime( entity.getUpdateTime() );
        newsArticleDto.setTitle( entity.getTitle() );
        newsArticleDto.setExcerpt( entity.getExcerpt() );
        newsArticleDto.setCategory( entity.getCategory() );
        newsArticleDto.setImg( entity.getImg() );
        newsArticleDto.setContent( entity.getContent() );
        newsArticleDto.setAuthor( entity.getAuthor() );
        newsArticleDto.setViews( entity.getViews() );

        return newsArticleDto;
    }

    @Override
    public List<NewsArticle> toEntity(List<NewsArticleDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsArticle> list = new ArrayList<NewsArticle>( dtoList.size() );
        for ( NewsArticleDto newsArticleDto : dtoList ) {
            list.add( toEntity( newsArticleDto ) );
        }

        return list;
    }

    @Override
    public List<NewsArticleDto> toDto(List<NewsArticle> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsArticleDto> list = new ArrayList<NewsArticleDto>( entityList.size() );
        for ( NewsArticle newsArticle : entityList ) {
            list.add( toDto( newsArticle ) );
        }

        return list;
    }
}
