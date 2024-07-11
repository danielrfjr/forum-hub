package com.forumhub.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private String curso;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "ACTIVE";

    // Constructors, getters, and setters
}
