package com.api.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class LoggingGatewayFilterFactory extends 
  AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    final Logger logger =
      LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			// Pre-processing
			if (config.isPreLogger()) {
				logger.info("Pre GatewayFilter logging: {}, method:{}, url:{}, body:{}", config.getBaseMessage(), exchange.getRequest().getMethod(), exchange.getRequest().getPath(),exchange.getRequest().getBody().toString() );
			}
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				// Post-processing
				if (config.isPostLogger()) {
					logger.info("Post GatewayFilter logging: {} status code: {}", config.getBaseMessage(), exchange.getResponse().getStatusCode());
				}
			}));
		};
    }

    public static class Config {
    	  private String baseMessage;
    	    private boolean preLogger;
    	    private boolean postLogger;
			public String getBaseMessage() {
				return baseMessage;
			}
			public void setBaseMessage(String baseMessage) {
				this.baseMessage = baseMessage;
			}
			public boolean isPreLogger() {
				return preLogger;
			}
			public void setPreLogger(boolean preLogger) {
				this.preLogger = preLogger;
			}
			public boolean isPostLogger() {
				return postLogger;
			}
			public void setPostLogger(boolean postLogger) {
				this.postLogger = postLogger;
			}

    }
}
