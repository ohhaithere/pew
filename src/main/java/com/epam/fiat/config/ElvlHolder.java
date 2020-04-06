package com.epam.fiat.config;

import com.epam.fiat.domain.Elvl;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElvlHolder {

  @Bean
  public Map<String, Elvl> elvlsMap() {
    return new ConcurrentHashMap<>();
  }

}
