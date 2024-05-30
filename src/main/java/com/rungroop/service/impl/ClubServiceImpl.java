package com.rungroop.service.impl;

import com.rungroop.dto.ClubDto;
import com.rungroop.models.Club;
import com.rungroop.models.UserEntity;
import com.rungroop.repository.ClubRepository;
import com.rungroop.repository.UserRepository;
import com.rungroop.security.SecurityUtil;
import com.rungroop.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroop.mapper.ClubMapper.mapToClub;
import static com.rungroop.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository ;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository , UserRepository  userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club)->mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public void saveClub(Club club) {
        String username = SecurityUtil.getSessionUse();
        UserEntity user = userRepository.findByUsername(username);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long Id) {
        Club club =  clubRepository.findById(Id).get() ;
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUse();
        UserEntity user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
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


}
