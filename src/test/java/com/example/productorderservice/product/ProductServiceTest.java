package com.example.productorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void Setup() {
        productService = new ProductService();
    }

    @Test
    void 상품등록() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = ProductServiceTest.DiscountPolicy.NONE;

        final AddProductRequest request = new ProductServiceTest.AddProductRequest(name, price, discountPolicy);

        productService.addProduct(request);
    }

    private enum DiscountPolicy {
        NONE
    }

    private record AddProductRequest(String name, int price, ProductServiceTest.DiscountPolicy discountPolicy) {
        public AddProductRequest {
            Assert.hasText(name, "상품명은 필수입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
            Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");
        }
    }

    private class ProductService {
        public void addProduct(final AddProductRequest request) {
            final Product product = new Product(request.name, request.price, request.discountPolicy);

            productPort.save(product);
        }

        private class Product {
            public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
                Assert.hasText(name, "상품명은 필수입니다.");
                Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
                Assert.notNull(discountPolicy, "할인 정책은 필수입니다.");

            }
        }
    }
}
