package com.rungroop.service;

import com.rungroop.dto.ClubDto;
import com.rungroop.models.Club;

import java.util.List;

public interface ClubService {
    public List<ClubDto> findAllClubs();
    public void saveClub(Club club);
    public ClubDto findClubById(Long Id);
    public void updateClub(ClubDto clubDto);
}
