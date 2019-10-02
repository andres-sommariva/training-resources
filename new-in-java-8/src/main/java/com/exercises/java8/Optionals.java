package com.exercises.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        Function<Address, String> getAddressString = address -> (new StringBuilder())
            .append(address.getStreet()).append(", ")
            .append(address.getCity()).append(", ")
            .append(address.getPostalCode()).append(", ")
            .append(address.getCountry())
            .toString();
        Function<User, String> getName = user -> user.getFullName().orElse(user.getUserName());
        Function<PurchaseOrderItem, String> getProduct = item -> item.getProductCode().orElse(item.getProductName());
        BiFunction<User, PurchaseOrderItem, String> getAddress = (user, item) -> !item.isShippable ? " - NON-SHIPPABLE" : " - Ship to: " + getAddressString.apply(purchaseOrder.getShippingAddress().orElse(user.getPersonalAddress()));
        BiFunction<User, PurchaseOrderItem, String> getBillingAddress = (user, item) ->  getAddressString.apply(purchaseOrder.getBillingAddress().orElse(purchaseOrder.getShippingAddress().orElse(user.getPersonalAddress())));
        //Header
        StringBuilder header = new StringBuilder();
        header
            .append("Buyer: ")
            .append(getName.apply(purchaseOrder.getBuyer()));
        purchaseOrder.getSeller().ifPresent(seller -> header.append(" - Seller: ").append(getName.apply(seller)));
        //Lines
        List<String> lines = new ArrayList<>();
        lines.add(header.toString());
        lines.addAll(purchaseOrder.getItems().stream()
            .map(item -> (new StringBuilder())
                .append(getProduct.apply(item))
                .append(getAddress.apply(purchaseOrder.getBuyer(), item))
                .append(" - Bill to: ").append(getBillingAddress.apply(purchaseOrder.getBuyer(), item))
                .toString()
            ).collect(Collectors.toList())
        );
        return lines;
    }

}
