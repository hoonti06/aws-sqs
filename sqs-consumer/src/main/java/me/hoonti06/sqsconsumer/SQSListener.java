package me.hoonti06.sqsconsumer;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSListener {

  // Acknowledgment acknowledgment는 안 먹힘
  @SqsListener("${cloud.aws.queue.name}")
  public void processOrder(@Payload String payload, @Headers Map<String, String> payloadHeaders) {
    log.info("payload: {}", payload);
    log.info("headers {}", payloadHeaders);
  }
}
