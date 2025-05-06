package org.ea.backenddevelopertaskforstartup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ea.backenddevelopertaskforstartup.model.Card;
import org.ea.backenddevelopertaskforstartup.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cards")
@Tag(name = "Card API", description = "Endpoints for managing cards")
public class CardController {
    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create a new card",
            description = "Creates and returns a new card entity"
    )
    @PostMapping
    public ResponseEntity<Card> create(@RequestBody Card card) {
        return ResponseEntity.ok(service.createCard(card));
    }

    @Operation(
            summary = "Get all cards",
            description = "Retrieves a list of all cards"
    )
    @GetMapping
    public ResponseEntity<List<Card>> getAll() {
        return ResponseEntity.ok(service.getAllCards());
    }

    @Operation(
            summary = "Get card by ID",
            description = "Fetches a specific card by its ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCardById(id));
    }

    @Operation(
            summary = "Update card",
            description = "Updates the card with the given ID using the provided card data"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id,
                                       @RequestBody Card card) {
        return ResponseEntity.ok(service.updateCard(id, card));
    }

    @Operation(
            summary = "Delete card",
            description = "Deletes the card with the specified ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
