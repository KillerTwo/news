package org.el.modules.news.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.modules.news.domain.NewsCategory;
import org.el.modules.news.service.dto.NewsCategoryDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class NewsCategoryMapperImpl implements NewsCategoryMapper {

    @Override
    public NewsCategory toEntity(NewsCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        NewsCategory newsCategory = new NewsCategory();

        newsCategory.setId( dto.getId() );
        newsCategory.setCreateBy( dto.getCreateBy() );
        newsCategory.setCreateTime( dto.getCreateTime() );
        newsCategory.setUpdateBy( dto.getUpdateBy() );
        newsCategory.setUpdateTime( dto.getUpdateTime() );
        newsCategory.setCategoryName( dto.getCategoryName() );
        newsCategory.setSort( dto.getSort() );
        newsCategory.setCategoryCode( dto.getCategoryCode() );

        return newsCategory;
    }

    @Override
    public NewsCategoryDto toDto(NewsCategory entity) {
        if ( entity == null ) {
            return null;
        }

        NewsCategoryDto newsCategoryDto = new NewsCategoryDto();

        newsCategoryDto.setId( entity.getId() );
        newsCategoryDto.setCreateBy( entity.getCreateBy() );
        newsCategoryDto.setCreateTime( entity.getCreateTime() );
        newsCategoryDto.setUpdateBy( entity.getUpdateBy() );
        newsCategoryDto.setUpdateTime( entity.getUpdateTime() );
        newsCategoryDto.setCategoryName( entity.getCategoryName() );
        newsCategoryDto.setSort( entity.getSort() );
        newsCategoryDto.setCategoryCode( entity.getCategoryCode() );

        return newsCategoryDto;
    }

    @Override
    public List<NewsCategory> toEntity(List<NewsCategoryDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsCategory> list = new ArrayList<NewsCategory>( dtoList.size() );
        for ( NewsCategoryDto newsCategoryDto : dtoList ) {
            list.add( toEntity( newsCategoryDto ) );
        }

        return list;
    }

    @Override
    public List<NewsCategoryDto> toDto(List<NewsCategory> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsCategoryDto> list = new ArrayList<NewsCategoryDto>( entityList.size() );
        for ( NewsCategory newsCategory : entityList ) {
            list.add( toDto( newsCategory ) );
        }

        return list;
    }
}
