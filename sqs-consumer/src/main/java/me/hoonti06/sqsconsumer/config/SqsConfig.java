package me.hoonti06.sqsconsumer.config;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqsConfig {

  @Value("${cloud.aws.region.static}")
  private String region;

  @Value("${cloud.aws.credentials.access-key}")
  private String accessKeyId;

  @Value("${cloud.aws.credentials.secret-key}")
  private String secretAccessKey;

  @Value("${cloud.aws.queue.uri}")
  private String sqsUrl;

  @Primary
  @Bean
  public AmazonSQSAsync amazonSQSAsync() {
    return AmazonSQSAsyncClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsUrl, region))
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey)))
        .build();
  }

  @Bean
  public QueueMessagingTemplate queueMessagingTemplate() {
    return new QueueMessagingTemplate(amazonSQSAsync());
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
