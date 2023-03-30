package me.hoonti06.sqsconsumer;


import static org.assertj.core.api.Assertions.assertThat;

import cloud.localstack.ServiceName;
import cloud.localstack.awssdkv1.TestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// localstack-utils 사용
@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(services = ServiceName.SQS)
class SqsListenerTest {

  @Test
  void name() {
    // given
    AmazonSQS sqs = TestUtils.getClientSQS();
    CreateQueueResult queue = sqs.createQueue("sample-queue");

    // when
    SendMessageResult sendMessageResult = sqs.sendMessage(queue.getQueueUrl(), "hello sqs");
    ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(queue.getQueueUrl());

    // then
    assertThat(receiveMessageResult.getMessages().get(0).getBody()).isEqualTo("hello sqs");
  }
}