package com.vetshop.dtos;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class UserDTO implements DTO{

    private int idUser;

    private String username;

    private String password;

    private String fullName;

    private TypeDTO userType;

}
