package com.wzp.module.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {


    /**
     * JdkSerializationRedisSerializer: 默认序列化方式，优点反序列化时不需要提供类型信息，但必须实现Serializable接口,在redis中使用二进制储存,所以序列化后的字符串比较小
     *                                  但在redis中没有可读性
     * Jackson2JsonRedisSerializer: 使用Jackson将对象序列化成json字符串,优点是速度快,不需要实现Serializable,但在构造函数中需要提供类型参数,供反序列化使用
     * @return
     */
   @Bean
   @ConditionalOnProperty(name = "spring.redis.host")
   public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
       redisTemplate.setConnectionFactory(factory);
       // value 序列化方式
       Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
       ObjectMapper objectMapper = new ObjectMapper();
       // json序列化时，不仅是根据get方法来序列化的，而是实体类中所有的有返回值的方法都会将返回的值序列化
       // 但是反序列化时是根据set方法来实现的，所以当实体类中有非get，set方法的方法有返回值时，反序列化时就会出错。
       // 例如security里的UserDetail,为解决此问题增加下面配置
       objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

       objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
       // 下面如果不指定则存入redis中的json数据为纯json，例如[{"id":72,"uuid":"c4d7fc52-4096-4c79-81ef-32cb1b87fd28","type":2}]
       // 如果指定则为带类型的json，例如["java.util.ArrayList",[{"@class":"com.model.app","id":72,"uuid":"c4d7fc52-4096-4c79-81ef-32cb1b87fd28","type":2}]]
       objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
       jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
       // key 序列化方式
       StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
       redisTemplate.setKeySerializer(stringRedisSerializer);
       // hash的key也采用String的序列化方式
       redisTemplate.setHashKeySerializer(stringRedisSerializer);
       // value序列化方式采用jackson
       redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
       // hash的value序列化方式采用jackson
       redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
       redisTemplate.afterPropertiesSet();
       return redisTemplate;
   }
}
