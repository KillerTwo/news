package org.el.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.modules.system.domain.Dict;
import org.el.modules.system.service.dto.DictSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:37+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class DictSmallMapperImpl implements DictSmallMapper {

    @Override
    public Dict toEntity(DictSmallDto dto) {
        if ( dto == null ) {
            return null;
        }

        Dict dict = new Dict();

        dict.setId( dto.getId() );

        return dict;
    }

    @Override
    public DictSmallDto toDto(Dict entity) {
        if ( entity == null ) {
            return null;
        }

        DictSmallDto dictSmallDto = new DictSmallDto();

        dictSmallDto.setId( entity.getId() );

        return dictSmallDto;
    }

    @Override
    public List<Dict> toEntity(List<DictSmallDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Dict> list = new ArrayList<Dict>( dtoList.size() );
        for ( DictSmallDto dictSmallDto : dtoList ) {
            list.add( toEntity( dictSmallDto ) );
        }

        return list;
    }

    @Override
    public List<DictSmallDto> toDto(List<Dict> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DictSmallDto> list = new ArrayList<DictSmallDto>( entityList.size() );
        for ( Dict dict : entityList ) {
            list.add( toDto( dict ) );
        }

        return list;
    }
}
