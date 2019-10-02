package com.exercises.java8;

import java.util.*;

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
        List<String> reportLines = new ArrayList<String>();

        if (purchaseOrder.seller.isPresent()) {
            User seller = purchaseOrder.seller.get();
            if (seller.fullName.isPresent()) {
                if (purchaseOrder.buyer.fullName.isPresent()) {
                    reportLines.add(String.format("Buyer: %s - Seller: %s", purchaseOrder.buyer.fullName.get(), seller.fullName.get()));
                } else {
                    reportLines.add(String.format("Buyer: %s - Seller: %s", purchaseOrder.buyer.userName, seller.fullName.get()));
                }
            } else {
                if (purchaseOrder.buyer.fullName.isPresent()) {
                    reportLines.add(String.format("Buyer: %s - Seller: %s", purchaseOrder.buyer.fullName.get(), seller.userName));
                } else {
                    reportLines.add(String.format("Buyer: %s - Seller: %s", purchaseOrder.buyer.userName, seller.userName));
                }
            }
        } else {
            if (purchaseOrder.buyer.fullName.isPresent()) {
                reportLines.add(String.format("Buyer: %s", purchaseOrder.buyer.fullName.get()));
            } else {
                reportLines.add(String.format("Buyer: %s", purchaseOrder.buyer.userName));
            }
        }

        for (PurchaseOrderItem item : purchaseOrder.items) {
            String purchaseOrderItem = "";
            if (item.productCode.isPresent()) {
                purchaseOrderItem += item.productCode.get();
            } else {
                purchaseOrderItem += item.productName;
            }

            if (item.isShippable) {
                if (purchaseOrder.shippingAddress.isPresent()) {
                    Address shippingAddress = purchaseOrder.shippingAddress.get();
                    purchaseOrderItem += String.format(" - Ship to: %s, %s, %s, %s",
                            shippingAddress.street, shippingAddress.city, shippingAddress.postalCode, shippingAddress.country);
                } else if (purchaseOrder.seller.isPresent()) {
                    Address personalAddress = purchaseOrder.seller.get().personalAddress;
                    purchaseOrderItem += String.format(" - Ship to: %s, %s, %s, %s",
                            personalAddress.street, personalAddress.city, personalAddress.postalCode, personalAddress.country);
                }
            } else {
                purchaseOrderItem += " - NON-SHIPPABLE";
            }
            purchaseOrderItem += " - Bill to: ";
            if (purchaseOrder.billingAddress.isPresent()) {
                Address billingAddress = purchaseOrder.billingAddress.get();
                purchaseOrderItem += String.format("%s, %s, %s, %s",
                        billingAddress.street, billingAddress.city, billingAddress.postalCode, billingAddress.country);
            } else if (purchaseOrder.shippingAddress.isPresent()) {
                Address shippingAddress = purchaseOrder.shippingAddress.get();
                purchaseOrderItem += String.format("%s, %s, %s, %s",
                        shippingAddress.street, shippingAddress.city, shippingAddress.postalCode, shippingAddress.country);
            } else {
                Address personalAddress = purchaseOrder.buyer.personalAddress;
                purchaseOrderItem += String.format("%s, %s, %s, %s",
                        personalAddress.street, personalAddress.city, personalAddress.postalCode, personalAddress.country);
            }

            reportLines.add(purchaseOrderItem);
        }

        return reportLines;
    }
}
