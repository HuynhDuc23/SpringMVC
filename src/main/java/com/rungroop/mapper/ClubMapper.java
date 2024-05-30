package com.rungroop.mapper;

import com.rungroop.dto.ClubDto;
import com.rungroop.models.Club;

import java.util.stream.Collectors;

public class ClubMapper {
    public static Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder().Id(clubDto.getId())
                .content(clubDto.getContent())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .createdBy(clubDto.getCreatedBy())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }

    public static ClubDto mapToClubDto(Club club) {
        ClubDto clubDto =  ClubDto.builder()
                .Id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .createdBy(club.getCreatedBy())
                .photoUrl(club.getPhotoUrl())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map(event -> EventMapper.mapToEventDto(event)).collect(Collectors.toList()) )
                .build();
        return clubDto;
    }
}
