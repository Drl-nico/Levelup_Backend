package com.example.Levelup_Backend.Dto;

import java.util.List;

public class BoletaRequestDTO {

    public int total;
    public List<BoletaItemDTO> items;

    public static class BoletaItemDTO {
        public Long productId;
        public int amount;
        public int price;
        public int subtotal;
    }
}
