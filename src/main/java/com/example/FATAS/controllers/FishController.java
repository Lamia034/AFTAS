package com.example.FATAS.controllers;

import com.example.FATAS.dtos.FishDto;
import com.example.FATAS.dtos.FishResponseDto;
import com.example.FATAS.dtos.MemberResponseDto;
import com.example.FATAS.services.FishService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fishes")
public class FishController {

    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
    @PostMapping
    public ResponseEntity<FishResponseDto> saveFish(@RequestBody FishDto fishDto) {
        FishResponseDto savedFish = fishService.saveFish(fishDto);
        return new ResponseEntity<>(savedFish, HttpStatus.CREATED);
    }
    @GetMapping
    public List<FishResponseDto> getFishes(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fishService.getAllFishes(pageable);
    }

    @GetMapping("/{fishId}")
    public ResponseEntity<FishResponseDto> getFishById(@PathVariable String fishId) {
        FishResponseDto fish = fishService.getFishById(fishId);
        return ResponseEntity.ok(fish);
    }


    @DeleteMapping("/{fishId}")
    public ResponseEntity<String> deleteFish(@PathVariable String fishId) {
        fishService.deleteFishById(fishId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Fish deleted successfully");
    }
    @PutMapping("/{fishId}")
    public ResponseEntity<FishResponseDto> updateFish(
            @PathVariable String fishId,
            @RequestBody FishDto updatedFishDto) {

        FishResponseDto updatedFish = fishService.updateFish(fishId, updatedFishDto);

        if (updatedFish != null) {
            return ResponseEntity.ok(updatedFish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
