package com.vetshop.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class RegularUsersListWrapperDTO {

    private List<RegularUserDTO> list;

}