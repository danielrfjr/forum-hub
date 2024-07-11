package com.forumhub.dto;

import javax.validation.constraints.NotBlank;

public class TopicRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String curso;

    // Getters and setters
}
