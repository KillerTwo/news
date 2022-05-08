package org.el.logging.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.logging.domain.Log;
import org.el.logging.service.dto.LogSmallDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class LogSmallMapperImpl implements LogSmallMapper {

    @Override
    public Log toEntity(LogSmallDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Log log = new Log();

        log.setDescription( dto.getDescription() );
        log.setRequestIp( dto.getRequestIp() );
        log.setAddress( dto.getAddress() );
        log.setBrowser( dto.getBrowser() );
        log.setTime( dto.getTime() );
        log.setCreateTime( dto.getCreateTime() );

        return log;
    }

    @Override
    public LogSmallDTO toDto(Log entity) {
        if ( entity == null ) {
            return null;
        }

        LogSmallDTO logSmallDTO = new LogSmallDTO();

        logSmallDTO.setDescription( entity.getDescription() );
        logSmallDTO.setRequestIp( entity.getRequestIp() );
        logSmallDTO.setTime( entity.getTime() );
        logSmallDTO.setAddress( entity.getAddress() );
        logSmallDTO.setBrowser( entity.getBrowser() );
        logSmallDTO.setCreateTime( entity.getCreateTime() );

        return logSmallDTO;
    }

    @Override
    public List<Log> toEntity(List<LogSmallDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Log> list = new ArrayList<Log>( dtoList.size() );
        for ( LogSmallDTO logSmallDTO : dtoList ) {
            list.add( toEntity( logSmallDTO ) );
        }

        return list;
    }

    @Override
    public List<LogSmallDTO> toDto(List<Log> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LogSmallDTO> list = new ArrayList<LogSmallDTO>( entityList.size() );
        for ( Log log : entityList ) {
            list.add( toDto( log ) );
        }

        return list;
    }
}
