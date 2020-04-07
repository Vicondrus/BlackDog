package com.vetshop.dtos;

import com.vetshop.entities.RegularUser;
import com.vetshop.entities.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Type;

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

    private TypeDTO userType;


    public RegularUserDTO(RegularUser user){
        idUser = user.getIdUser();
        username = user.getUsername();
        password = user.getUsername();
        fullName = user.getFullName();
        userType = TypeDTO.valueOf(user.getUserType().toString());
    }

}
