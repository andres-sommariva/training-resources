package com.exercises.java8;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OptionalsTest {

    private static final Optionals.Address personalAddress = Optionals.Address.builder()
            .country("country")
            .city("city")
            .postalCode("postal code")
            .street("personal address")
            .build();

    private static final Optionals.Address billingAddress = Optionals.Address.builder()
            .country("country")
            .city("city")
            .postalCode("postal code")
            .street("billing address")
            .build();

    private static final Optionals.Address shippingAddress = Optionals.Address.builder()
            .country("country")
            .city("city")
            .postalCode("postal code")
            .street("shipping address")
            .build();

    private static final Optionals.User user_with_fullName = Optionals.User.builder()
            .userName("username1")
            .fullName(Optional.of("user fullname"))
            .personalAddress(personalAddress)
            .build();

    private static final Optionals.User user_without_fullName = Optionals.User.builder()
            .userName("username2")
            .fullName(Optional.empty())
            .personalAddress(personalAddress)
            .build();

    private static final List<Optionals.PurchaseOrderItem> purchaseOrderItems = Arrays.asList(
            Optionals.PurchaseOrderItem.builder()
                    .productCode(Optional.empty())
                    .productName("product without code")
                    .isShippable(true)
                    .build(),
            Optionals.PurchaseOrderItem.builder()
                    .productCode(Optional.of("CODE1"))
                    .productName("product with code")
                    .isShippable(false)
                    .build()
            );

    private Optionals optionals;

    @BeforeTest
    void setup() {
        optionals = new Optionals();
    }

    @Test
    public void testGetPurchaseOrdersReportLines_header_noSeller() {
        Optionals.PurchaseOrder purchaseOrder = Optionals.PurchaseOrder.builder()
                .buyer(user_with_fullName)
                .seller(Optional.empty())
                .shippingAddress(Optional.of(shippingAddress))
                .billingAddress(Optional.empty())
                .items(Collections.EMPTY_LIST)
                .build();

        List<String> reportLines = optionals.getPurchaseOrdersReportLines(purchaseOrder);

        assertEquals(reportLines.size(), 1, "Report should have 1 line");
        assertEquals(reportLines.get(0), "Buyer: user fullname", "Line 1 contains buyer's full name");
    }

    @Test
    public void testGetPurchaseOrdersReportLines_header_withSeller() {
        Optionals.PurchaseOrder purchaseOrder = Optionals.PurchaseOrder.builder()
                .buyer(user_with_fullName)
                .seller(Optional.of(user_without_fullName))
                .shippingAddress(Optional.of(shippingAddress))
                .billingAddress(Optional.empty())
                .items(Collections.EMPTY_LIST)
                .build();

        List<String> reportLines = optionals.getPurchaseOrdersReportLines(purchaseOrder);

        assertEquals(reportLines.size(), 1, "Report should have 1 line");
        assertEquals(reportLines.get(0), "Buyer: user fullname - Seller: username2", "Line 1 contains buyer's full name and seller's username");
    }

    @Test
    public void testGetPurchaseOrdersReportLines_items_withBillingAddress() {
        Optionals.PurchaseOrder purchaseOrder = Optionals.PurchaseOrder.builder()
                .buyer(user_with_fullName)
                .seller(Optional.of(user_without_fullName))
                .shippingAddress(Optional.of(shippingAddress))
                .billingAddress(Optional.of(billingAddress))
                .items(purchaseOrderItems)
                .build();

        List<String> reportLines = optionals.getPurchaseOrdersReportLines(purchaseOrder);

        assertEquals(reportLines.size(), 3, "Report should have 3 lines");
    }
}
