package com.clopezrubio.demo.config;

import com.clopezrubio.demo.common.Coach;
import com.clopezrubio.demo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
