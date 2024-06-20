package com.webserver.siatwiki.info.service;

import com.webserver.siatwiki.info.dto.CategoryDto;
import com.webserver.siatwiki.info.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    public List<CategoryDto.CategoryResponseDto> getCategories() {
        List<Category> categories = List.of(Category.values());

        return categories.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private CategoryDto.CategoryResponseDto entityToDto(Category category) {
        return CategoryDto.CategoryResponseDto.builder()
                .order(category.getOrder())
                .name(category.getName())
                .krName(category.getKr())
                .build();
    }
}
