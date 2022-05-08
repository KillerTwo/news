package org.el.modules.system.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.el.modules.system.domain.Job;
import org.el.modules.system.service.dto.JobSmallDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-08T14:28:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class JobSmallMapperImpl implements JobSmallMapper {

    @Override
    public Job toEntity(JobSmallDto dto) {
        if ( dto == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( dto.getId() );
        job.setName( dto.getName() );

        return job;
    }

    @Override
    public JobSmallDto toDto(Job entity) {
        if ( entity == null ) {
            return null;
        }

        JobSmallDto jobSmallDto = new JobSmallDto();

        jobSmallDto.setId( entity.getId() );
        jobSmallDto.setName( entity.getName() );

        return jobSmallDto;
    }

    @Override
    public List<Job> toEntity(List<JobSmallDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Job> list = new ArrayList<Job>( dtoList.size() );
        for ( JobSmallDto jobSmallDto : dtoList ) {
            list.add( toEntity( jobSmallDto ) );
        }

        return list;
    }

    @Override
    public List<JobSmallDto> toDto(List<Job> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<JobSmallDto> list = new ArrayList<JobSmallDto>( entityList.size() );
        for ( Job job : entityList ) {
            list.add( toDto( job ) );
        }

        return list;
    }
}
