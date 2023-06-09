package me.hoonti06.sqsconsumer;

import static org.assertj.core.api.Assertions.assertThat;

import cloud.localstack.ServiceName;
import cloud.localstack.awssdkv1.TestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(services = ServiceName.SQS)
@SpringBootTest
class SqsListenerTest2 {
  @Autowired SQSListener sqsListener;

  @Test
  void test() {

    // given
    AmazonSQS sqs = TestUtils.getClientSQS();
    CreateQueueResult queue = sqs.createQueue("sample.fifo");
    SendMessageResult sendMessageResult = sqs.sendMessage(queue.getQueueUrl(), "hello sqs");

    // when
//    ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(queue.getQueueUrl());

    // then
//    assertThat(receiveMessageResult.getMessages().get(0).getBody()).isEqualTo("hello sqs");

  }


}