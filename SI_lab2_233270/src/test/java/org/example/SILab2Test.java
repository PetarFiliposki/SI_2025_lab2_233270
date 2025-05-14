package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void testEveryStatement() {
        // null lista
        Exception ex1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "987654321016"));
        assertTrue(ex1.getMessage().contains("allItems list can't be null!"));

        // null ime
        Item unnamed = new Item(null, 2, 200, 0.1);
        Exception ex2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(Collections.singletonList(unnamed), "4444555566667777"));
        assertEquals("Invalid item!", ex2.getMessage());

        // nevaliden br. na karticka
        Item normalItem = new Item("Milk", 1, 50, 0);
        Exception ex3 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(Arrays.asList(normalItem), "1234abcd5678efgh"));
        assertEquals("Invalid character in card number!", ex3.getMessage());

        // nevalidena dolzina na karticka
        Exception ex4 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(Arrays.asList(normalItem), "5678"));
        assertEquals("Invalid card number!", ex4.getMessage());

        // ne se validni
        Item greenTea = new Item("Coctails", 12, 310, 0.25);
        double total = SILab2.checkCart(Arrays.asList(greenTea), "8888777766665555");
        assertEquals(2760.0, total, 0.01);
    }
    @Test
    void testMultipleCondition() {
        // Price > 300
        Item premiumItem = new Item("Pizza", 1, 350, 0.0);
        SILab2.checkCart(Arrays.asList(premiumItem), "1111000022223333");

        // Discount > 0
        Item promoItem = new Item("Green", 2, 80, 0.15);
        SILab2.checkCart(Arrays.asList(promoItem), "4444555566667777");

        // Quantity > 10
        Item bulkOrder = new Item("Mouse", 12, 90, 0.0);
        SILab2.checkCart(Arrays.asList(bulkOrder), "8888999900001111");

        // nitu eden da e validen
        Item regularItem = new Item("Yellow", 1, 70, 0.0);
        SILab2.checkCart(Arrays.asList(regularItem), "9999888877776666");
    }
}