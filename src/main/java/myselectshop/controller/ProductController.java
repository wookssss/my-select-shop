package myselectshop.controller;

import lombok.RequiredArgsConstructor;
import myselectshop.dto.ProductMypriceRequestDto;
import myselectshop.dto.ProductRequestDto;
import myselectshop.dto.ProductResponseDto;
import myselectshop.entity.ApiUseTime;
import myselectshop.entity.User;
import myselectshop.repository.ApiUseTimeRepository;
import myselectshop.security.UserDetailsImpl;
import myselectshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProduct(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(),
                page-1, size, sortBy, isAsc
        );
    }

    @PostMapping("/products/{productId}/folder")
    public void addFolder(@PathVariable Long productId,
                          @RequestParam Long folderId,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        productService.addFolder(productId, folderId, userDetails.getUser());
    }

    @GetMapping("/folders/{folderId}/products")
    public Page<ProductResponseDto> getProductInFolder(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @PathVariable Long folderId, @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return productService.getProductsInFolder(
                folderId, page-1, size, sortBy, isAsc, userDetails.getUser()
        );
    }

}
