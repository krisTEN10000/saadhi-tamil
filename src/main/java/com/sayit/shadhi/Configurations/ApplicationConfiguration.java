package com.sayit.shadhi.Configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ApplicationConfiguration {



    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access.key}")
    private String accessKey;

    @Value("${minio.secreat.key}")
    private String secretKey;

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return  new StringRedisSerializer();
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        LettuceConnectionFactory lettuceConnectionFactory= new LettuceConnectionFactory("localhost", 9003);
        return lettuceConnectionFactory;
    }


    @Bean
    public RedisTemplate redisTemplate(StringRedisSerializer serializer , LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate< ? , ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
