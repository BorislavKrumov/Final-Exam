package com.academy.supermarket.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemsParams {

    @NotBlank(message = "Id should not be empty!")
    private String id;
    @NotEmpty(message = "The list which contains ids of items should not be empty!")
    private List<String> itemIds;

}
