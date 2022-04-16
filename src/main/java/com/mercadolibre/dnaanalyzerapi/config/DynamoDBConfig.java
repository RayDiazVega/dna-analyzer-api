package com.mercadolibre.dnaanalyzerapi.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories("com.mercadolibre.dnaanalyzerapi.human.infrastructure.ports")
public class DynamoDBConfig {

  @Value("${amazon.aws.accesskey}")
  private String awsAccessKey;

  @Value("${amazon.aws.secretkey}")
  private String awsSecretKey;

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.SA_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(
            new BasicAWSCredentials(awsAccessKey, awsSecretKey))).build();
  }
}
