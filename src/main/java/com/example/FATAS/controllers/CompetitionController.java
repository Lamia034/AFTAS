package com.example.FATAS.controllers;


import com.example.FATAS.dtos.CompetitionDto;
import com.example.FATAS.dtos.CompetitionResponseDto;
import com.example.FATAS.services.CompetitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    @PostMapping
    public ResponseEntity<CompetitionResponseDto> saveCompetition(@RequestBody CompetitionDto competitionDto) {
        CompetitionResponseDto savedCompetition = competitionService.saveCompetition(competitionDto);
        return new ResponseEntity<>(savedCompetition, HttpStatus.CREATED);
    }
    @GetMapping
    public List<CompetitionResponseDto> getCompetitions(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return competitionService.getAllCompetitions(pageable);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getCompetitionsCount() {
        long count = competitionService.getCompetitionsCount();
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity<String> deleteCompetition(@PathVariable String competitionId) {
        competitionService.deleteCompetitionById(competitionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Competition deleted successfully");
    }
    @PutMapping("/{competitionId}")
    public ResponseEntity<CompetitionResponseDto> updateCompetition(
            @PathVariable String competitionId,
            @RequestBody CompetitionDto updatedCompetitionDto) {

        CompetitionResponseDto updatedCompetition = competitionService.updateCompetition(competitionId, updatedCompetitionDto);

        if (updatedCompetition != null) {
            return ResponseEntity.ok(updatedCompetition);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
