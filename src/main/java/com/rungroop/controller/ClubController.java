package com.rungroop.controller;

import com.rungroop.dto.ClubDto;
import com.rungroop.models.Club;
import com.rungroop.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService ;
    @Autowired
    public ClubController (ClubService clubService){
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
    public String listClubs(Model model){
       List<ClubDto> clubs =  clubService.findAllClubs();
       model.addAttribute("clubs" , clubs);
       return "clubs-list";
    }
    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
          Club club = new Club();
          model.addAttribute("club" , club);
          return "clubs-create";
    }
    @PostMapping("/clubs/new")
    public String saveClub(@ModelAttribute("club") Club club){
        clubService.saveClub(club);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId , Model model){
        ClubDto club =  clubService.findClubById(clubId);
        if(club != null){
            System.out.println("OK");
        }else{
            System.out.println("NO ok");
        }

        model.addAttribute("club",club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId , @ModelAttribute("club") ClubDto club){
           club.setId(clubId);
           clubService.updateClub(club);
           return "redirect:/clubs";
    }

}