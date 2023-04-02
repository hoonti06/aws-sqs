package me.hoonti06.sqsconsumer;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.test.sqs.SqsTest;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

// Testcontainers 사용
@Testcontainers
@SqsTest(SQSListener.class)
class SqsListenerTest3 {


  @Container
  static LocalStackContainer localStack =
      new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.14.3"))
          .withClasspathResourceMapping("/localstack/init-aws.sh",
              "/docker-entrypoint-initaws.d/init-aws.sh", BindMode.READ_ONLY)
          .withServices(SQS)
          .waitingFor(Wait.forLogMessage(".*Initialized\\.\n", 1));

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("cloud.aws.sqs.endpoint", () -> localStack.getEndpointOverride(SQS).toString());
    registry.add("cloud.aws.credentials.access-key", () -> localStack.getAccessKey());
    registry.add("cloud.aws.credentials.secret-key", () -> localStack.getSecretKey());
    registry.add("cloud.aws.region.static", () -> localStack.getRegion());
    registry.add("cloud.aws.queue.name", () -> "test-order-queue");
  }

  @Autowired
  private QueueMessagingTemplate queueMessagingTemplate;

  @Test
  void shouldStoreIncomingPurchaseOrderInDatabase() {

    Map<String, Object> messageHeaders = Map.of("contentType", "application/json");

    String payload = ""
        + "{\n"
        + "     \"customer_name\": \"hoon\",\n"
        + "     \"order_amount\": 42\n"
        + "}";
    queueMessagingTemplate
        .send("test-order-queue", new GenericMessage<>(payload, messageHeaders));

//    await()
//        .atMost(Duration.ofSeconds(3))
//        .untilAsserted(() -> verify(sqsListener).processOrder(any(), any()));
  }

}

