package com.manzano.fundspring.fundamentosSpring.Configurations;

import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithProperties;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithPropertiesImp;
import com.manzano.fundspring.fundamentosSpring.Pojos.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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

    /*Al implementarse como dependencia podemos generar una instancia de db con los datos asociados, es la ventaja de
    * tener esta clase de configuración definida aqui*/
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }
}
