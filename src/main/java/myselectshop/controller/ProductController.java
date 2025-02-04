package myselectshop.controller;

import lombok.RequiredArgsConstructor;
import myselectshop.dto.ProductResponseDto;
import myselectshop.dto.ProductRequestDto;
import myselectshop.dto.ProductResponseDto;
import myselectshop.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto){
        return productService.createProduct(requestDto);
    }
}
