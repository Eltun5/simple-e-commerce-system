package org.ea.backenddevelopertaskforstartup.repository;

import org.ea.backenddevelopertaskforstartup.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
