package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IGenericService<Product,String> {
    private List<Product> products;
    public ProductService(){
        this.products = new ArrayList<>();
    }
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
    }
}
