package com.dmitry.NewsClient.mapstruct;

import com.dmitry.NewsClient.dto.GetNewsOutDto;
import com.dmitry.NewsClient.entity.NewsEntity;
import com.dmitry.NewsClient.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetNewsOutDtoMapper {

    public GetNewsOutDto newsEntityOut(NewsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetNewsOutDto getNewsOutDto = new GetNewsOutDto();

        getNewsOutDto.setDescription( entity.getDescription() );
        getNewsOutDto.setId( entity.getId() );
        getNewsOutDto.setImage( entity.getImage() );
        getNewsOutDto.setUserId(entity.getUser().getId());
        List<Tag> list = entity.getTags();
        if ( list != null ) {
            getNewsOutDto.setTags( new ArrayList<Tag>( list ) );
        }
        getNewsOutDto.setTitle( entity.getTitle() );
        getNewsOutDto.setUsername( entity.getUsername() );

        return getNewsOutDto;
    }
}
