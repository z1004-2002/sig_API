package com._gi.sig.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._gi.sig.dto.AuthUser;
import com._gi.sig.dto.UserDto;
import com._gi.sig.models.User;
import com._gi.sig.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(User user) {
        return toUserDto(userRepository.save(user));
    }

    public UserDto authentication(AuthUser authUser) throws Exception{
        List<User> users = userRepository.findByLogin(authUser.getLogin());
        if (users.size()==0) {
            throw new Exception("Utilisateur " + authUser.getLogin() + " non existent");
        }else{
            if (!users.get(0).getPassword().equals(authUser.getPassword())) {
                throw new Exception("érreur du mot de passe");
            }
        }
        return toUserDto(users.get(0));
    }

    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(this::toUserDto).toList();
    }

    public UserDto updatePassword(AuthUser authUser) throws Exception{
        List<User> users = userRepository.findByLogin(authUser.getLogin());
        if (users.size()==0) {
            throw new Exception("Utilisateur " + authUser.getLogin() + " non existent");
        }
        User user = users.get(0);
        user.setPassword(authUser.getPassword());
        return toUserDto(user);
    }

    public String deleteUser(UUID id) throws Exception{
        if(!userRepository.findById(id).isPresent()){
            throw new Exception("user not foud");
        }
        userRepository.deleteById(id);
        return "Utilisateur suprimée";
    }

    public UserDto getUser(UUID id){
        return toUserDto(userRepository.findById(id).orElse(null));
    }

    private UserDto toUserDto(User user){
        return UserDto.builder()
        .id(user.getId())
        .login(user.getLogin())
        .phone(user.getPhone())
        .role(user.getRole())
        .build();
    }
}
