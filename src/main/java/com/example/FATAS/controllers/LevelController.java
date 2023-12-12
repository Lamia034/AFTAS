package com.example.FATAS.controllers;

import com.example.FATAS.dtos.LevelDto;
import com.example.FATAS.dtos.LevelResponseDto;
import com.example.FATAS.services.LevelService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<LevelResponseDto> saveLevel(@RequestBody LevelDto levelDto) {
        LevelResponseDto savedLevel = levelService.saveLevel(levelDto);
        return new ResponseEntity<>(savedLevel, HttpStatus.CREATED);
    }

    @GetMapping
    public List<LevelResponseDto> getLevels(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return levelService.getAllLevels(pageable);
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<String> deleteLevel(@PathVariable Integer levelId) {
        levelService.deleteLevelById(levelId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Level deleted successfully");
    }

    @PutMapping("/{levelId}")
    public ResponseEntity<LevelResponseDto> updateLevel(
            @PathVariable Integer levelId,
            @RequestBody LevelDto updatedLevelDto) {

        LevelResponseDto updatedLevel = levelService.updateLevel(levelId, updatedLevelDto);

        if (updatedLevel != null) {
            return ResponseEntity.ok(updatedLevel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

