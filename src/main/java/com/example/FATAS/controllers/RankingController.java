package com.example.FATAS.controllers;


import com.example.FATAS.dtos.RankingDto;
import com.example.FATAS.dtos.RankingResponseDto;
import com.example.FATAS.embeddable.RankingId;
import com.example.FATAS.services.RankingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rankings")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping
    public ResponseEntity<RankingResponseDto> saveRanking(@RequestBody RankingDto rankingDto) {
        RankingResponseDto savedRanking = rankingService.saveRanking(rankingDto);
        return new ResponseEntity<>(savedRanking, HttpStatus.CREATED);
    }

    @GetMapping
    public List<RankingResponseDto> getRankings(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rankingService.getAllRankings(pageable);
    }

    @DeleteMapping("/{rankingId}")
    public ResponseEntity<String> deleteRanking(@PathVariable RankingId rankingId) {
        rankingService.deleteRankingById(rankingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Ranking deleted successfully");
    }

    @PutMapping("/{rankingId}")
    public ResponseEntity<RankingResponseDto> updateRanking(
            @PathVariable RankingId rankingId,
            @RequestBody RankingDto updatedRankingDto) {

        RankingResponseDto updatedRanking = rankingService.updateRanking(rankingId, updatedRankingDto);

        if (updatedRanking != null) {
            return ResponseEntity.ok(updatedRanking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

