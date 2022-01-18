package com.manzano.fundspring.fundamentosSpring.Configurations;

import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithProperties;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithPropertiesImp;
import com.manzano.fundspring.fundamentosSpring.Pojos.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserPojo.class)/*Esta anotación en el archivo de configuración general nos permite
                                                utilizar una clase/pojo como dependencia configurable a partir
                                                del archivo de configuración application/properties*/
public class GeneralConfiguration {

    @Value("${value.name}")
    private String nombre;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImp(nombre, apellido);
    }
}
