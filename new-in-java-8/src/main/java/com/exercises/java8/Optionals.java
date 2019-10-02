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
        // TODO: Implement this method (see method javadoc for details)
        //throw new NotImplementedException();

        List<String> reportLines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // Header
        sb.append("Buyer: ");
        sb.append(getUserName(purchaseOrder.getBuyer()));
        purchaseOrder.getSeller().ifPresent(user -> {
            sb.append(" - Seller: ");
            sb.append(getUserName(user));
        });
        reportLines.add(sb.toString());

        // Items
        purchaseOrder.getItems().stream()
                .map(i -> getReportLine(i, purchaseOrder))
                .forEach(r -> reportLines.add(r));

        return reportLines;
    }

    private String getUserName(User user) {
        return user.getFullName().orElse(user.getUserName());
    }

    private String getReportLine(PurchaseOrderItem purchaseOrderItem, PurchaseOrder purchaseOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append(getProductDescription(purchaseOrderItem));
        sb.append(" - ");
        sb.append(getShippingDescription(purchaseOrderItem, purchaseOrder));
        sb.append(" - Bill to: ");
        sb.append(getBillingDescription(purchaseOrderItem, purchaseOrder));
        return sb.toString();
    }

    private String getProductDescription(PurchaseOrderItem purchaseOrderItem) {
        return purchaseOrderItem.getProductCode().orElse(purchaseOrderItem.getProductName());
    }

    private String getShippingDescription(PurchaseOrderItem purchaseOrderItem, PurchaseOrder purchaseOrder) {
        StringBuilder sb = new StringBuilder();

        if(purchaseOrderItem.isShippable()) {
            sb.append("Ship to: ");
            sb.append(formatAddress(purchaseOrder.getShippingAddress().orElse(purchaseOrder.getBuyer().getPersonalAddress())));
        } else {
            sb.append("NON-SHIPPABLE");
        }

        return sb.toString();
    }

    private String getBillingDescription(PurchaseOrderItem purchaseOrderItem, PurchaseOrder purchaseOrder) {
        return Optional.of(purchaseOrder.getBillingAddress().orElse(purchaseOrder.getShippingAddress().orElse(purchaseOrder.getBuyer().getPersonalAddress())))
                .map(this::formatAddress)
                .get();
    }

    private String formatAddress(Address address) {
        return String.format("%s, %s, %s, %s", address.getStreet(), address.getCity(), address.getPostalCode(), address.getCountry());
    }

}
