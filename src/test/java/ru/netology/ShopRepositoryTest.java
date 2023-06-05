package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product product1 = new Product(1, "бальзам", 930);
    Product product2 = new Product(2, "батарейки", 100);
    Product product3 = new Product(3, "порошок", 654);
    Product product4 = new Product(4, "шампунь", 899);
    Product product5 = new Product(5, "краска", 700);
    Product product6 = new Product(3, "паста", 115);

    @Test
    public void shouldRemoveExistentItem() {
        ShopRepository shop = new ShopRepository();
        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.add(product4);
        shop.add(product5);
        shop.remove(3);

        Product[] expected = {product1, product2, product4, product5};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveNonExistentItem() {
        ShopRepository shop = new ShopRepository();
        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.add(product4);
        shop.add(product5);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shop.remove(6);
        });
    }

    @Test
    public void shouldAddNonExistentId() {
        ShopRepository shop = new ShopRepository();
        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.add(product4);
        shop.add(product5);

        Product[] expected = {product1, product2, product3, product4, product5};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddExistentId() {
        ShopRepository shop = new ShopRepository();
        shop.add(product1);
        shop.add(product2);
        shop.add(product3);
        shop.add(product4);
        shop.add(product5);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shop.add(product6);
        });
    }
}