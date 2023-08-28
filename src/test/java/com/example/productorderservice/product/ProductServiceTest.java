package com.example.productorderservice.product;

import org.junit.jupiter.api.Test;

class ProductServiceTest {

    @Test
    void 상품등록() {
        final AddProductRequest request = new AddProductRequest("상품명", 1000, DiscountPolicy.None);
        productService.addProduct(request);

    }

    private class AddProductRequest {
    }

    private enum DiscountPolicy {
        NONE
    }
}
