package org.adaschool.api.service.product;

import org.adaschool.api.repository.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory implementation of ProductsService
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-12
 */
@Service
public class ProductsServiceMap implements ProductsService {

    private final Map<String, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        String id = product.getId();
        if (id == null || id.isEmpty()) {
            id = String.valueOf(idGenerator.getAndIncrement());
            Product newProduct = new Product(id, product.getName(), product.getDescription(),
                                            product.getCategory(), product.getPrice());
            newProduct.setTags(product.getTags());
            newProduct.setImageUrl(product.getImageUrl());
            products.put(id, newProduct);
            return newProduct;
        }
        products.put(id, product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> all() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void deleteById(String id) {
        products.remove(id);
    }

    @Override
    public Product update(Product product, String productId) {
        products.put(productId, product);
        return product;
    }
}
