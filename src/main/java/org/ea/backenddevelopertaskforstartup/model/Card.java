package org.ea.backenddevelopertaskforstartup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.ea.backenddevelopertaskforstartup.validation.ValidExpiryDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 16, max = 16)
    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;

    @ValidExpiryDate
    @Column(name = "expire_date", nullable = false)
    private String expireDate;

    @Size(min = 3, max = 3)
    @Column(nullable = false)
    private String cvv;
}
