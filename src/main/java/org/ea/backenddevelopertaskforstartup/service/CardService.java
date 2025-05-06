package org.ea.backenddevelopertaskforstartup.service;

import org.ea.backenddevelopertaskforstartup.model.Card;
import org.ea.backenddevelopertaskforstartup.repository.CardRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public Card createCard(Card card) {
        return repository.save(card);
    }

    public List<Card> getAllCards() {
        return repository.findAll();
    }

    @Cacheable(value = "cards", key = "#id")
    public Card getCardById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + id));
    }

    public Card updateCard(Long id, Card card) {
        Card oldCard = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + id));

        oldCard.setExpireDate(card.getExpireDate());
        oldCard.setCardNumber(card.getCardNumber());
        oldCard.setCvv(card.getCvv());

        return repository.save(oldCard);
    }

    public void deleteCard(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + id)));
    }
}
