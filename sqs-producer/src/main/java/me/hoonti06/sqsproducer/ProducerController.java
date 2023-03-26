package me.hoonti06.sqsproducer;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

  private final SqsEventProducer sqsEventProducer;

  @PostMapping("/sendMessage")
  public ResponseEntity<String> sendMessage(@RequestBody JsonNode jsonNode) {
    sqsEventProducer.publishEvent(jsonNode);
    return ResponseEntity.ok().build();
  }
}
