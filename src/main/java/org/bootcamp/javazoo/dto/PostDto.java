package org.bootcamp.javazoo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    @NotBlank (message = "The 'id' cannot be empty.")
    @Positive(message = "The 'id' must be greater than zero")
    private Integer user_id;

    @NotEmpty (message = "The 'name' cannot be empty.")
    private String date;

    @NotEmpty (message = "The 'product' cannot be empty.")
    @NotNull (message = "The 'product' cannot be empty.")
    private ProductDto product;

    @NotEmpty (message = "The 'category' cannot be empty.")
    private Integer category;

    @NotEmpty (message = "The 'price' cannot be empty.")
    @Min(0)
    @Max(value = 10000000, message = "The maximum price per product must be 10,000,000")
    private Double price;
}
