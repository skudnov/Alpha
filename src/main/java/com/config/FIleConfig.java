package com.config;

import com.dao.FileCrud;
import com.dao.ICrud;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com")
public class FIleConfig {
    @Bean("FileCrud")
    public ICrud CrudConfig() {
        return new FileCrud();
    }
}
