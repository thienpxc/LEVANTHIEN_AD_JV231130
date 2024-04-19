package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cartItems;
    public CartService() {
        this.cartItems = new ArrayList<>();
    }
    public void displayProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void addToCart(Product product, int quantity) {

        if (product.getStock() < quantity) {
            System.out.println("Số lượng sản phẩm trong kho không đủ.");
            return;
        }


        CartItem existingCartItem = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                existingCartItem = cartItem;
                break;
            }
        }

        if (existingCartItem != null) {

            int newQuantity = existingCartItem.getQuantity() + quantity;
            existingCartItem.setQuantity(newQuantity);
            product.setStock(product.getStock() - quantity);
        } else {

            int cartItemId = generateCartItemId();
            double price = product.getProductPrice();
            CartItem newCartItem = new CartItem(cartItemId, product, price, quantity);
            cartItems.add(newCartItem);
            product.setStock(product.getStock() - quantity);
        }

        System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
    }

    private int generateCartItemId() {
        return cartItems.size() + 1;
    }
    public  void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }

        System.out.println("Danh sách giỏ hàng:");
        System.out.printf("%-15s %-30s %-15s %-15s%n", "CartItemId", "Tên sản phẩm", "Số lượng", "Giá");

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int cartItemId = cartItem.getCartItemId();
            int quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();

            System.out.printf("%-15s %-30s %-15d %-15.2f%n", cartItemId, product.getProductName(), quantity, price);
        }
    }
    public void updateQuantity(int cartItemIdStr, int newQuantity) {
        try {
            int cartItemId = cartItemIdStr;
            CartItem cartItem = findCartItemById(cartItemId);
            if (cartItem == null) {
                System.out.println("Không tìm thấy sản phẩm trong giỏ hàng với CartItemId " + cartItemId);
                return;
            }

            Product product = cartItem.getProduct();
            int currentQuantity = cartItem.getQuantity();
            int stockChange = newQuantity - currentQuantity;

            if (product.getStock() < stockChange) {
                System.out.println("Số lượng sản phẩm trong kho không đủ để cập nhật.");
                return;
            }

            cartItem.setQuantity(newQuantity);
            product.setStock(product.getStock() - stockChange);
            System.out.println("Số lượng sản phẩm đã được cập nhật trong giỏ hàng.");
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập một số nguyên.");
        }
    }

    private CartItem findCartItemById(int cartItemId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                return cartItem;
            }
        }
        return null;
    }
    public void removeFromCart(int cartItemId) {
        CartItem cartItem = findCartItemById(cartItemId);
        if (cartItem == null) {
            System.out.println("Không tìm thấy sản phẩm trong giỏ hàng với CartItemId " + cartItemId);
            return;
        }

        Product product = cartItem.getProduct();
        int quantity = cartItem.getQuantity();

        cartItems.remove(cartItem);
        product.setStock(product.getStock() + quantity);

        System.out.println("Đã xóa sản phẩm khỏi giỏ hàng.");
    }
    public void clearCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            product.setStock(product.getStock() + quantity);
        }

        cartItems.clear();
        System.out.println("Đã xóa tất cả sản phẩm khỏi giỏ hàng.");
    }

}
