package com.example.FATAS.controllers;

import com.example.FATAS.dtos.HuntingDto;
import com.example.FATAS.dtos.HuntingResponseDto;
import com.example.FATAS.services.HuntingService;
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
    public ResponseEntity<HuntingResponseDto> saveHunting(@RequestBody HuntingDto huntingDto, @RequestParam double huntedFishWeight) {
        HuntingResponseDto savedHunting = huntingService.huntFish(huntingDto, huntedFishWeight);
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

//    @PutMapping("/{huntingId}")
//    public ResponseEntity<HuntingResponseDto> updateHunting(
//            @PathVariable Integer huntingId,
//            @RequestBody HuntingDto updatedHuntingDto) {
//
//        HuntingResponseDto updatedHunting = huntingService.updateHunting(huntingId, updatedHuntingDto);
//
//        if (updatedHunting != null) {
//            return ResponseEntity.ok(updatedHunting);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}