package com.manzano.fundspring.fundamentosSpring;

import com.manzano.fundspring.fundamentosSpring.Bean.MyBean;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithDependency;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithProperties;
import com.manzano.fundspring.fundamentosSpring.Component.ComponentDependency;
import com.manzano.fundspring.fundamentosSpring.Pojos.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosSpringApplication implements CommandLineRunner {

	//Implementación de la clase Apache commons para el uso de logs
	private final Log LOG = LogFactory.getLog(FundamentosSpringApplication.class);

	//Se busca inyectar la interfaz como dependencia en estos casos
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	/*Cuando tenemos que la dependencia esta implementada en dos o mas clases/objetos/beans, se debe definir cual
	 de esas implementaciones es la que se va a utilizar*/
	public FundamentosSpringApplication(@Qualifier("componentImplementTwo") ComponentDependency componentDependency,
										MyBean myBean,
										MyBeanWithDependency myBeanWithDependency,
										MyBeanWithProperties myBeanWithProperties,
										UserPojo userPojo) {
		this.componentDependency = componentDependency;
		this.myBean = myBean; //Se pasa la dependencia como parte del constructor para que se instancie el momento
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;

	}

	//El metodo main ejecutara el especifivo run que se defina en la aplicación.
	public static void main(String[] args) {
		SpringApplication.run(FundamentosSpringApplication.class, args);
	}

	//Metodo implementado de la intf CommandLineRunner, nos ayuda a ejecutar aquellos elementos definidos en codigo
	//de esta forma estamos utilizando la dependencia inyectada dentro de otro objeto y otra clase.
	@Override
	public void run(String... args) throws Exception {

		try {
			/*Cuando ya tienes inyectada la dependencia, lo que haces para la ejecución es llamar a su implementación*/
			componentDependency.saludar();
			myBean.print();
			myBeanWithDependency.printWithDependency();
			System.out.println(myBeanWithProperties.function());
			System.out.println(userPojo.getMail() + " - " + userPojo.getPassword());
			LOG.info("Se implementaron correctamente las dependencias");

		} catch (Exception e){
			LOG.error("La ejecución de alguna implementación fallo por " + e);
		}

	}



}
