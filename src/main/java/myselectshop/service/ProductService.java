package myselectshop.service;

import lombok.RequiredArgsConstructor;
import myselectshop.dto.ProductRequestDto;
import myselectshop.dto.ProductResponseDto;
import myselectshop.entity.Product;
import myselectshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto){
        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }
}
