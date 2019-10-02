package com.exercises.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

public class Optionals {

    @Builder @Data
    public static class PurchaseOrder {
        private User buyer;
        private Optional<User> seller;
        private Optional<Address> shippingAddress;
        private Optional<Address> billingAddress;
        private List<PurchaseOrderItem> items;
    }

    @Builder @Data
    public static class PurchaseOrderItem {
        private String productName;
        private Optional<String> productCode;
        private boolean isShippable;
    }

    @Builder @Data
    public static class User {
        private Optional<String> fullName;
        private String userName;
        private Address personalAddress;
    }

    @Builder @Data
    public static class Address {
        private String country;
        private String city;
        private String postalCode;
        private String street;

        @Override
        public String toString() {
            return street + ", " + city + ", " + postalCode + ", " + country;
        }
    }

    /**
     * Report structure should follow these guidelines:
     *
     * 1st row: Header
     * "Buyer: (${fullName} | ${userName})[ - Seller: (${fullName} | ${userName})]"
     *
     * Following rows: Order items
     * "Product: (${productCode} | ${productName}) - (Ship to: (${shippingAddress} | ${personalAddress}) | NON-SHIPPABLE) - Bill to: (${billingAddress} | ${shippingAddress} | ${personalAddress})"
     *
     * Address format should be:
     * "${street}, ${city}, ${postalCode}, ${country}"
     *
     * @param purchaseOrder
     * @return
     */
    public List<String> getPurchaseOrdersReportLines(PurchaseOrder purchaseOrder) {

        List<String> reportLines = new ArrayList<>();

        User buyer = purchaseOrder.buyer;
        Optional<User> seller = purchaseOrder.seller;
        List<PurchaseOrderItem> items = purchaseOrder.items;

        reportLines.add("Buyer: " + buyer.fullName.orElse(buyer.userName) + seller.map(s -> " - Seller: " + s.fullName.orElse(s.userName)).orElse(""));
        items.stream().forEach(item -> reportLines.add(item.productCode.orElse(item.productName)
            + " - "
            + (!item.isShippable ? "NON-SHIPPABLE"
            : "Ship to: " + purchaseOrder.shippingAddress.map(sa -> sa.toString())
            .orElse(buyer.personalAddress.toString()))
            + " - Bill to: "
            + purchaseOrder.billingAddress.map(ba -> ba.toString())
            .orElse(purchaseOrder.shippingAddress.map(sa -> sa.toString())
              .orElse(buyer.personalAddress.toString()))
          )
        );

        return reportLines;
    }
}
