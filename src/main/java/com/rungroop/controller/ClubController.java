package com.rungroop.controller;

import com.rungroop.dto.ClubDto;
import com.rungroop.models.Club;
import com.rungroop.models.UserEntity;
import com.rungroop.security.SecurityUtil;
import com.rungroop.service.ClubService;
import com.rungroop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService ;
    private UserService userService;
    @Autowired
    public ClubController (ClubService clubService , UserService userService){
        this.userService = userService ;
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
    public String listClubs(Model model){


        UserEntity user = new UserEntity();
       List<ClubDto> clubs =  clubService.findAllClubs();
       String username = SecurityUtil.getSessionUse();
       if(username != null){
           user =  userService.findByUsername(username);
           model.addAttribute("user",user);
       }
        model.addAttribute("user",user);
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
        model.addAttribute("club",club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId , @Valid @ModelAttribute("club") ClubDto club , BindingResult bindingResult , Model model){
        if(!bindingResult.hasErrors()){
            club.setId(clubId);
            clubService.updateClub(club);
            return "redirect:/clubs";
        }

        model.addAttribute("club" , club);
        return "clubs-edit";
    }
    @GetMapping("clubs/{clubId}")
    public String getDetail(@PathVariable("clubId") Long clubId , Model model){
        ClubDto clubDto  = clubService.findClubById(clubId);
        System.out.println(clubDto);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }
    @PostMapping("/clubs/search")
    public String searchClub(@RequestParam("query") String query, Model model){
        List<ClubDto> clubs =  clubService.searchClubs(query);
        model.addAttribute("clubs" , clubs);
        return "clubs-list";
    }

}
