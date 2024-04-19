package Exam_Advance_1.ra.run;

import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.model.Catalog;
import Exam_Advance_1.ra.model.Product;
import Exam_Advance_1.ra.service.CartService;
import Exam_Advance_1.ra.service.CatalogService;
import Exam_Advance_1.ra.service.ProductService;

import java.util.Scanner;

public class BookManagement {
    private static CatalogService catalogService = new CatalogService();
    private static ProductService productService = new ProductService();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Dành cho người dùng");
            System.out.println("4. Thoát");
            System.out.println("Nhập lựa chọn của bạn: ");
            int data = scanner.nextInt();
            scanner.nextLine();
            switch (data) {
                case 1:
                    catalogManagementMenu(scanner);
                    break;
                case 2:
                    productManagementMenu(scanner);
                    break;
                case 3:
                    userMenu(scanner);
                    break;
                case 4:
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void catalogManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (lưu ý không xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");

            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewCatalog(scanner);
                    break;
                case 2:
                    displayAllCatalogs();
                    break;
                case 3:
                    editCatalogName(scanner);
                    break;
                case 4:
                    deleteCatalogById(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void addNewCatalog(Scanner scanner) {
        System.out.print("Nhập số danh mục cần thêm: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin danh mục thứ " + (i + 1) + ":");
            System.out.print("Nhập mã danh mục: ");
            int catalogId = scanner.nextInt();

            scanner.nextLine();
            System.out.print("Nhập tên danh mục: ");
            String catalogName = scanner.nextLine();
            if (catalogName.trim().isEmpty()) {
                System.out.println("Tên sản phẩm không được để trống.");
                catalogName = scanner.nextLine();
            }

            System.out.print("Nhập mô tả: ");
            String descriptions = scanner.nextLine();
            if (descriptions.trim().isEmpty()) {
                System.out.println("Tên sản phẩm không được để trống.");
                descriptions = scanner.nextLine();
            }

            Catalog catalog = new Catalog(catalogId, catalogName, descriptions);
            catalogService.save(catalog);
        }
    }

    private static void displayAllCatalogs() {
        System.out.println("Danh sách các danh mục:");
        for (Catalog catalog : catalogService.getAll()) {
            System.out.println(catalog.toString());
        }
    }

    private static void editCatalogName(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần sửa: ");
        int catalogId = scanner.nextInt();
        Catalog catalog = catalogService.findById(catalogId);
        if (catalog == null) {
            System.out.println("Không tìm thấy danh mục có mã " + catalogId);
            return;
        }
        scanner.nextLine();
        System.out.print("Nhập tên danh mục mới: ");
        String catalogName = scanner.nextLine();
        System.out.print("Nhập mô tả mới: ");
        String descriptions = scanner.nextLine();
        catalog.setCatalogName(catalogName);
        catalog.setDescriptions(descriptions);
        System.out.println("Đã cập nhật tên danh mục thành công.");
    }

    private static void deleteCatalogById(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int catalogId = scanner.nextInt();
        catalogService.delete(catalogId);
    }


    private static void productManagementMenu(Scanner scanner) {
        while (true) {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. Quay lại");

            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProducts(scanner);
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    sortProductsByPrice();
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProductByName(scanner);
                    break;
                case 6:
                    editProduct(scanner);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void addProducts(Scanner scanner) {
        System.out.println("Nhập số sản phẩm cần thêm: ");
        int number = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Nhập thông tin sản phẩm thứ " + (i + 1) + ":");
            System.out.print("Nhập mã sản phẩm: ");
            String productId = scanner.next();
            while (!validate(productId, "P\\d{4}")) {
                System.out.println("Mã sản phẩm phải bắt đầu bằng chữ P và theo sau là 4 ký tự số.");
                productId = scanner.next();
            }
            scanner.nextLine();
            System.out.print("Nhập tên sản phẩm: ");
            String productName = scanner.nextLine();
            if (productName.trim().isEmpty()) {
                System.out.println("Tên sản phẩm không được để trống.");
                productName = scanner.nextLine();
            }
            System.out.print("Nhập giá sản phẩm: ");
            double productPrice = Double.parseDouble(scanner.nextLine());
            if (productPrice <= 0) {
                System.out.println("Giá sản phẩm phải lớn hơn 0.");
                productPrice = Double.parseDouble(scanner.nextLine());
            }
            System.out.print("Nhập mô tả: ");
            String description = scanner.nextLine();
            System.out.print("Nhập số lượng tồn kho: ");
            int stock = scanner.nextInt();
            if (stock < 10) {
                System.out.println("Số lượng tồn kho phải ít nhất là 10.");
                stock = scanner.nextInt();
            }
            System.out.println("Danh sách các danh mục: ");
            if (catalogService.getAll().isEmpty()) {
                System.out.println("Chưa có danh mục nào được thêm.Vui lòng thêm danh mục trước");
                return;
            }
            for (Catalog catalog : catalogService.getAll()) {
                System.out.println(catalog.toString());
            }
            System.out.print("Nhập mã danh mục: ");
            int catalogId = scanner.nextInt();
            Catalog catalog = catalogService.findById(catalogId);
            if (catalog == null) {
                System.out.println("Không tìm thấy danh mục có mã " + catalogId);
                return;
            }
            Product product = new Product(productId, productName, productPrice, description, stock, catalog);
            productService.save(product);
        }

    }

    private static void displayProducts() {
        System.out.println("Danh sách các sản phẩm:");
        for (Product product : productService.getAll()) {
            System.out.println(product.toString());
        }
    }

    private static void sortProductsByPrice() {
        productService.getAll().sort((p1, p2) -> (int) (p2.getProductPrice() - p1.getProductPrice()));
    }

    private static void searchProductByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String productName = scanner.next();
        for (Product product : productService.getAll()) {
            if (product.getProductName().contains(productName)) {
                System.out.println(product.toString());
            }
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.next();
        productService.delete(productId);
    }

    private static void editProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần sửa: ");
        String productId = scanner.next();
        Product product = productService.findById(productId);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm có mã " + productId);
            return;
        }
        scanner.nextLine();
        System.out.print("Nhập tên sản phẩm mới: ");
        String productName = scanner.nextLine();
        while (productName.trim().isEmpty()) {
            System.out.println("Tên sản phẩm không được để trống.");
            productName = scanner.nextLine();
        }
        System.out.print("Nhập giá sản phẩm mới: ");
        double productPrice = scanner.nextDouble();
        while (productPrice <= 0) {
            System.out.println("Giá sản phẩm phải lớn hơn 0.");
            productPrice = scanner.nextDouble();
        }
        scanner.nextLine();
        System.out.print("Nhập mô tả mới: ");
        String description = scanner.nextLine();
        System.out.print("Nhập số lượng tồn kho mới: ");
        int stock = scanner.nextInt();
        while (stock < 10) {
            System.out.println("Số lượng tồn kho phải lớn hơn hoặc bằng 10.");
            stock = scanner.nextInt();
        }
        System.out.print("Nhập mã danh mục mới: ");
        int catalogId = scanner.nextInt();
        Catalog catalog = catalogService.findById(catalogId);
        if (catalog == null) {
            System.out.println("Không tìm thấy danh mục có mã " + catalogId);
            return;
        }
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setDescription(description);
        product.setStock(stock);
        product.setCatalog(catalog);
    }

    public static boolean validate(String Str, String regex) {
        if (Str == null) {
            return false;
        }
        return Str.matches(regex);
    }

    private static void userMenu(Scanner scanner) {
        CartService cartService = new CartService();
        while (true) {
            System.out.println("**************************MENU-USER**************************");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm vào giỏ hàng");
            System.out.println("3. Xem tất cả sản phẩm trong giỏ hàng");
            System.out.println("4. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa 1 sản phẩm trong giỏ hàng");
            System.out.println("6. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("7. Quay lại");

            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    cartService.displayProducts(productService.getAll());
                    break;
                case 2:
                    System.out.print("Nhập mã sản phẩm cần thêm: ");
                    String productId = scanner.next();
                    Product product = productService.findById(productId);
                    if (product != null) {
                        System.out.print("Nhập số lượng sản phẩm: ");
                        int quantity = scanner.nextInt();
                        cartService.addToCart(product, quantity);
                    } else {
                        System.out.println("Không tìm thấy sản phẩm có mã " + productId);
                    }
                    break;
                case 3:
                    cartService.displayCart();
                    break;
                case 4:
                    System.out.println("Nhập mã sản phẩm cần thay đổi số lượng: ");
                    String cartItemId = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Nhập số lượng mới: ");
                    String quantity = scanner.nextLine();
                    cartService.updateQuantity(Integer.parseInt(cartItemId), Integer.parseInt(quantity));
                    break;
                case 5:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    cartItemId = scanner.next();
                    cartService.removeFromCart(Integer.parseInt(cartItemId));
                    break;
                case 6:
                    cartService.clearCart();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }


}


