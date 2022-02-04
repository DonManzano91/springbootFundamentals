package com.manzano.fundspring.fundamentosSpring.Configurations;

import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithProperties;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithPropertiesImp;
import com.manzano.fundspring.fundamentosSpring.Pojos.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(UserPojo.class)/*Esta anotación en el archivo de configuración general nos permite
                                                utilizar una clase/pojo como dependencia configurable a partir
                                                del archivo de configuración application/properties*/

@PropertySource("classpath:connection.properties")/*Enlace a nuestro archivo de configuración que nos permite
                                                    tomar los parametros ahi descritos*/
public class GeneralConfiguration {

    @Value("${value.name}")
    private String nombre;

    @Value("${value.apellido}")
    private String apellido;

    @Value("${value.random}")
    private String random;

    @Value("${jdbc-url}")
    private String jdbcUrl;

    @Value("${driver}")
    private String driver;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public MyBeanWithProperties function(){
        return new MyBeanWithPropertiesImp(nombre, apellido);
    }

    /*Al implementarse como dependencia podemos generar una instancia de db con los datos asociados, es la ventaja de
    * tener esta clase de configuración definida aqui*/
    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
