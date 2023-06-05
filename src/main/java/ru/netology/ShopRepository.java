package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private Product[] addToArray(Product[] current, Product product) {
        if (findById(product.getId()) == null) {
            Product[] tmp = new Product[current.length + 1];
            for (int i = 0; i < current.length; i++) {
                tmp[i] = current[i];
            }
            tmp[tmp.length - 1] = product;
            return tmp;
        }
        throw new AlreadyExistsException(
                "Товар с id: " + product.getId() + " уже существует");
    }

    public void add(Product product) {
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public void remove(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Товар с id: " + id + " не найден");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}