package org.example.webflux.config;

import org.example.webflux.model.llmclient.LlmModel;
import org.example.webflux.model.llmclient.LlmType;
import org.example.webflux.service.llmclient.LlmWebClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class CommonConfig {

    @Bean
    public Map<LlmType, LlmWebClientService> getLlmWebClientServiceMap(List<LlmWebClientService> llmWebClientServiceList) {
        return llmWebClientServiceList.stream().collect(Collectors.toMap(service -> service.getLlmType(), Function.identity()));
    }
}
