package com.kuxoca.loto;

import com.kuxoca.loto.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class MyDemoWebLotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyDemoWebLotoApplication.class, args);
    }

}
