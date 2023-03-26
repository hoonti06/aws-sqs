package me.hoonti06.sqsconsumer;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SQSListener {

  @SqsListener(value = "${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
  public void receiveMessage(@Payload String payload, @Headers Map<String, String> headers, Acknowledgment acknowledgment) {
    log.info("payload: {}", payload);
    log.info("headers {}", headers);
    acknowledgment.acknowledge();
  }
}
