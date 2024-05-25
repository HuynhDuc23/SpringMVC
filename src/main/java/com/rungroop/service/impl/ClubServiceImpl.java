package com.rungroop.service.impl;

import com.rungroop.dto.ClubDto;
import com.rungroop.models.Club;
import com.rungroop.repository.ClubRepository;
import com.rungroop.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository ;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club)->mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public void saveClub(Club club) {
        clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long Id) {
        Club club =  clubRepository.findById(Id).get() ;
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long Id) {
        clubRepository.deleteById(Id);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map((club)->mapToClubDto(club)).collect(Collectors.toList());
    }

    private Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder().Id(clubDto.getId())
                .content(clubDto.getContent())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }

    public ClubDto mapToClubDto(Club club) {
        ClubDto clubDto =  ClubDto.builder()
                .Id(club.getId())
                .title(club.getTitle())
                .content(club.getContent())
                .photoUrl(club.getPhotoUrl())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
}
