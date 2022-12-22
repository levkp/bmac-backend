package com.bmac.warehouse.adapters.in.api;

import com.bmac.warehouse.adapters.in.api.dto.ItemDto;
import com.bmac.warehouse.ports.in.LoadEveryItemUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private final LoadEveryItemUseCase loadEveryItem;

    @Autowired
    public ItemController(LoadEveryItemUseCase loadEveryItem) {
        this.loadEveryItem = loadEveryItem;
    }

    @GetMapping("/api/item")
    public ResponseEntity<List<ItemDto>> loadAll() {
        List<ItemDto> dtos = loadEveryItem.load()
                .stream()
                .map(item -> new ItemDto(
                        item.getId(),
                        item.getName(),
                        item.getMinimum(),
                        item.getMaximum(),
                        item.getTemperature(),
                        item.getUnit()))
                .toList();
        return ResponseEntity.status(dtos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(dtos);
    }
}
