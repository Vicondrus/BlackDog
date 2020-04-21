package com.veterinary.dtos;

import com.veterinary.entities.Consultation;
import com.veterinary.entities.RegularUser;
import com.veterinary.entities.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class RegularUserDTO implements DTO{

    private int idUser;

    private String username;

    private String password;

    private String fullName;

    private TypeDTO type;


    public RegularUserDTO(RegularUser user){
        idUser = user.getIdUser();
        username = user.getUsername();
        password = user.getUsername();
        fullName = user.getFullName();
        if(user.getUserType()!=null)
            type = TypeDTO.valueOf(user.getUserType().toString());
    }

}
