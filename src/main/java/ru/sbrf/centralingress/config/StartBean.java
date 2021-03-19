package ru.sbrf.centralingress.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StartBean {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier(value = "customHazelcastInstance")
    public HazelcastInstance hazelcastInstance() {

        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("MessageService");
        final String[] addresses = {"172.17.128.1" + ":" + "5701", "172.17.128.1" + ":" + "5702", "172.17.128.1" + ":" + "5703"};
        clientConfig.getNetworkConfig().addAddress(addresses);
        final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        return client;
    }

}
