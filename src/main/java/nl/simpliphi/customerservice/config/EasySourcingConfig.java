package nl.simpliphi.customerservice.config;

import com.github.easysourcing.EasySourcingBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class EasySourcingConfig {

    @Bean
    public EasySourcingBuilder easySourcingBuilder(@Value("${easysourcing.application-id}") String appId,
                                                   @Value("${easysourcing.bootstrap-servers}") String servers) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, servers);

        return new EasySourcingBuilder(properties);
    }

}
