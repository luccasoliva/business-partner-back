package com.example.businesspartnerback.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String cardName;

    private String avatar;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;
}
