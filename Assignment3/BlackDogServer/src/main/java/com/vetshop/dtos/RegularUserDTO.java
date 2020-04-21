package com.vetshop.dtos;

import com.vetshop.entities.RegularUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * The type Regular user dto.
 */
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


    /**
     * Instantiates a new Regular user dto.
     *
     * @param user the user
     */
    public RegularUserDTO(RegularUser user){
        idUser = user.getIdUser();
        username = user.getUsername();
        password = user.getUsername();
        fullName = user.getFullName();
        if(user.getUserType() != null)
            userType = TypeDTO.valueOf(user.getUserType().toString());
    }

}
