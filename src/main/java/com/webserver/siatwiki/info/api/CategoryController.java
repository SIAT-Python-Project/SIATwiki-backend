package com.webserver.siatwiki.info.api;

import com.webserver.siatwiki.info.dto.CategoryDto;
import com.webserver.siatwiki.info.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/api/info/category")
    public ResponseEntity<List<CategoryDto.CategoryResponseDto>> getCategories() {
        List<CategoryDto.CategoryResponseDto> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
