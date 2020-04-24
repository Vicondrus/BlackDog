package com.vetshop.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * The type Item dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@ToString
@Getter
@Setter
@Data
public class ItemDTO implements DTO {

    private int itemId;

    private String name;

    private double quantity;
}
