package com.example.Levelup_Backend.service;

import com.example.Levelup_Backend.Dto.BoletaRequestDTO;
import com.example.Levelup_Backend.model.Boleta;
import com.example.Levelup_Backend.model.BoletaItem;
import com.example.Levelup_Backend.repository.BoletaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Transactional
    public Boleta crearBoleta(BoletaRequestDTO dto) {

        Boleta boleta = new Boleta();
        boleta.setPurchaseDate(LocalDateTime.now());
        boleta.setTotal(dto.total);

        var items = dto.items.stream().map(i -> {
            BoletaItem item = new BoletaItem();
            item.setAmount(i.amount);
            item.setPrice(i.price);
            item.setProductId(i.productId);
            item.setSubtotal(i.subtotal);
            item.setBoleta(boleta);
            return item;
        }).collect(Collectors.toList());

        boleta.setItems(items);

        return boletaRepository.save(boleta);
    }
    public java.util.List<Boleta> obtenerBoletas() {
        return boletaRepository.findAll();
    }
}
