package com.example.Levelup_Backend.service;

import com.example.Levelup_Backend.Dto.BoletaRequestDTO;
import com.example.Levelup_Backend.model.Boleta;
import com.example.Levelup_Backend.model.BoletaItem;
import com.example.Levelup_Backend.model.Product;
import com.example.Levelup_Backend.model.User;
import com.example.Levelup_Backend.repository.BoletaRepository;
import com.example.Levelup_Backend.repository.ProductRepository;
import com.example.Levelup_Backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Boleta crearBoleta(BoletaRequestDTO dto, String username) {

        Boleta boleta = new Boleta();
        boleta.setPurchaseDate(LocalDateTime.now());
        boleta.setTotal(dto.getTotal());

        var items = dto.getItems().stream().map(i -> {
            Product product = productRepository.findById(i.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Producto no encontrado: " + i.getProductId()
                    ));

            // Validar stock
            if (product.getStock() < i.getAmount()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Stock insuficiente para producto " + product.getId() +
                                ". Disponible: " + product.getStock() + ", solicitado: " + i.getAmount()
                );
            }

            // Decrementar stock y guardar producto
            product.setStock(product.getStock() - i.getAmount());
            productRepository.save(product);

            BoletaItem item = new BoletaItem();
            item.setAmount(i.getAmount());
            item.setPrice(i.getPrice());
            item.setProductId(i.getProductId());
            item.setSubtotal(i.getSubtotal());
            item.setBoleta(boleta);
            return item;
        }).collect(Collectors.toList());

        boleta.setItems(items);

        // Asociar usuario (si viene username)
        if (username != null) {
            User user = userRepository.findByEmail(username).orElse(null);
            boleta.setUser(user);
        }

        return boletaRepository.save(boleta);
    }

    public java.util.List<Boleta> obtenerBoletas() {
        return boletaRepository.findAll();
    }
}