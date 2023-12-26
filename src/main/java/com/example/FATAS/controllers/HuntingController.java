package com.example.FATAS.controllers;

import com.example.FATAS.dtos.HuntingDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import com.example.FATAS.services.HuntingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/huntings")
public class HuntingController {

    private final HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }


    @PostMapping
    public ResponseEntity<HuntingResponseDto> saveHunting(@Valid @RequestBody HuntingDto huntingDto) {
        HuntingResponseDto savedHunting = huntingService.huntFish(huntingDto);
        return new ResponseEntity<>(savedHunting, HttpStatus.CREATED);
    }

    @GetMapping
    public List<HuntingResponseDto> getHuntings(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return huntingService.getAllHuntings(pageable);
    }

    @DeleteMapping("/{huntingId}")
    public ResponseEntity<String> deleteHunting(@PathVariable Integer huntingId) {
        huntingService.deleteHuntingById(huntingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Hunting deleted successfully");
    }


}