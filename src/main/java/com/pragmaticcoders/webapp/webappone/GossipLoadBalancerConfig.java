package com.pragmaticcoders.webapp.webappone;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ServerList;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GossipLoadBalancerConfig {

    @Bean
	@ConditionalOnMissingBean
	public ServerList<?> ribbonServerList(IClientConfig config) {
		ConsulServerList serverList = new ConsulServerList(client, properties);
		serverList.initWithNiwsConfig(config);
		return serverList;
	}

	@Bean
	public ServerListFilter<Server> ribbonServerListFilter() {
		return new HealthServiceServerListFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public IPing ribbonPing() {
		return new ConsulPing();
	}
}
