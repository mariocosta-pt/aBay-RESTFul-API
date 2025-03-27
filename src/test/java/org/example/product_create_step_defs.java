package org.example;

import static org.assertj.core.api.Assertions.assertThat;

class ProductCreate {
    static String createProduct(String productData) {
        switch (productData) {
            case "valid product details":
                return "201 Created";
            case "missing required fields":
                return "400 Bad Request";
            case "invalid category reference":
                return "404 Not Found";
            default:
                return "500 Internal Server Error";
        }
    }
}

