package com.forumhub.controller;

import com.forumhub.dto.TopicRequest;
import com.forumhub.dto.TopicResponse;
import com.forumhub.model.Topic;
import com.forumhub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody @Valid TopicRequest topicRequest, Authentication authentication) {
        Topic topic = new Topic(topicRequest.getTitulo(), topicRequest.getMensagem(), topicRequest.getCurso(), authentication.getName());
        topicRepository.save(topic);
        return ResponseEntity.ok(topic);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicRequest topicRequest) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.setTitulo(topicRequest.getTitulo());
            topic.setMensagem(topicRequest.getMensagem());
            topic.setCurso(topicRequest.getCurso());
            topicRepository.save(topic);
            return ResponseEntity.ok(topic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if (topicRepository.existsById(id)) {
            topicRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
