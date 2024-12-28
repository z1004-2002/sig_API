package com._gi.sig.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._gi.sig.dto.BureauDto;
import com._gi.sig.dto.BureauRequest;
import com._gi.sig.dto.UserDto;
import com._gi.sig.models.Bureau;
import com._gi.sig.models.Role;
import com._gi.sig.models.User;
import com._gi.sig.repository.BureauRepository;

import jakarta.transaction.Transactional;

@Service
public class BureauService {
    @Autowired
    private BureauRepository bureauRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public BureauDto createBureau(BureauRequest request){
        UserDto user = userService.createUser(
            User.builder()
            .login(request.getScrutateur().getLogin())
            .password(request.getScrutateur().getPassword())
            .phone(request.getScrutateur().getPhone())
            .role(Role.BUREAU)
            .build()
        );
        Bureau bureau = bureauRepository.save(
            Bureau.builder()
            .matricule(request.getMatricule())
            .region(request.getRegion())
            .departement(request.getDepartement())
            .arrondisssement(request.getArrondisssement())
            .nbreElecteurs(request.getNbreElecteurs())
            .scrutateurId(user.getId())
            .build()
        );
        return toBureauDto(bureau);
    }
    public List<BureauDto> getBureaus(){
        return bureauRepository.findAll().stream().map(this::toBureauDto).toList();
    }

    public BureauDto getBureau(UUID id){
        return bureauRepository.findById(id).map(this::toBureauDto).orElse(null);
    }

    public BureauDto updateBureau(UUID id, BureauRequest request){
        Bureau bureau = bureauRepository.findById(id).orElse(null);
        if(bureau == null){
            throw new IllegalStateException("Bureau not found");
        }
        bureau.setMatricule(request.getMatricule());
        bureau.setRegion(request.getRegion());
        bureau.setDepartement(request.getDepartement());
        bureau.setArrondisssement(request.getArrondisssement());
        bureau.setNbreElecteurs(request.getNbreElecteurs());
        return toBureauDto(bureauRepository.save(bureau));
    }

    public String deleteBureau(UUID id){
        bureauRepository.deleteById(id);
        return "Bureau deleted successfully";
    }

    private BureauDto toBureauDto(Bureau bureau){
        return BureauDto.builder()
        .id(bureau.getId())
        .matricule(bureau.getMatricule())
        .arrondisssement(bureau.getArrondisssement())
        .departement(bureau.getDepartement())
        .region(bureau.getRegion())
        .nbreElecteurs(bureau.getNbreElecteurs())
        .scrutateur(
            userService.getUser(bureau.getScrutateurId())
        )
        .build();
    }
}
