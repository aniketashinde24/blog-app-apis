package com.example.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4,max = 25,message = "Category Title must be min 4 character and max 25 characters !!")
	private String categoryTitle;
	
	@NotEmpty(message = "Category description must be Entered !!")
	private String categoryDescription;
}
