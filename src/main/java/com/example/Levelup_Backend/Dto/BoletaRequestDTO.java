package com.example.Levelup_Backend.Dto;

import java.util.List;

public class BoletaRequestDTO {
    private int total;
    private List<ItemDTO> items;

    // Getters y Setters
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<ItemDTO> getItems() {
        return items;
    }
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public static class ItemDTO {
        private Long productId;
        private int amount;
        private int price;
        private int subtotal;

        // Getters y Setters
        public Long getProductId() {
            return productId;
        }
        public void setProductId(Long productId) {
            this.productId = productId;
        }
        public int getAmount() {
            return amount;
        }
        public void setAmount(int amount) {
            this.amount = amount;
        }
        public int getPrice() {
            return price;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        public int getSubtotal() {
            return subtotal;
        }
        public void setSubtotal(int subtotal) {
            this.subtotal = subtotal;
        }
    }
}
