package com.microservice.orderservices;

import feign.RequestInterceptor;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RequiredArgsConstructor
public class OrderServicesApplication {

	private final BeanFactory beanFactory;

	public static void main(String[] args) {
		SpringApplication.run(OrderServicesApplication.class, args);
	}

	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return requestTemplate -> {
			JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext()
					.getAuthentication();

			requestTemplate.header("Authorization", "Bearer " + token.getToken().getTokenValue());
		};
	}

	@Bean
	public ExecutorService traceableExecutorService() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		return new TraceableExecutorService(beanFactory, executorService);
	}

}
