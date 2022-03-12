package com.manzano.fundspring.fundamentosSpring.Configurations;

import com.manzano.fundspring.fundamentosSpring.Bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Esto nos remarca que esta clase se vuelve un java configuration, así se puede auxiliar con anotaciones o incluso
* un archivo XML para la configuración de los Beans*/
@Configuration
public class MyConfigurationBeans {

    /*Aqui definimos como al acudir a una instancia de la interface, esta nos regresa una instancia de la
    * implementación, y aqui se puede configurar a cual queremos acudir*/
    @Bean
    public MyBean beanOperation(){
        //return new MyBeanImpl(); //esta se habilita para la primer implementación
        return new MyBeanImpl2(); //esta habilita la segunda implementación

        /*Asi en este Java config definimos que implementación toma, esto tambien se puede hacer con la anotación
        * @Qualifier
        * */
    }

    @Bean
    public MyOperation beanMyOperation(){
        return new MyOperationImp(); //En esta configuración recuerda retornamos el constructor de la clase que impl
                                    //nuestra dependencia.
    }

    /*Implementaremos nuestra dependencia anidada, deberemos ver como se implementa ese constructor con base
    * en la interfaz que estemos utilizando */
    @Bean
    public MyBeanWithDependency beanOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImp(myOperation) ;
    }
}
