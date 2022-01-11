package com.manzano.fundspring.fundamentosSpring;

import com.manzano.fundspring.fundamentosSpring.Bean.MyBean;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithDependency;
import com.manzano.fundspring.fundamentosSpring.Component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosSpringApplication implements CommandLineRunner {

	//Se busca inyectar la interfaz como dependencia en este caso
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	/*Cuando tenemos que la dependencia esta implementada en dos o mas clases/objetos/beans, se debe definir cual
	 de esas implementaciones es la que se va a utilizar*/
	public FundamentosSpringApplication(@Qualifier("componentImplementTwo") ComponentDependency componentDependency,
										MyBean myBean,
										MyBeanWithDependency myBeanWithDependency) {
		this.componentDependency = componentDependency;
		this.myBean = myBean; //Se pasa la dependencia como parte del constructor para que se instancie el momento
		this.myBeanWithDependency = myBeanWithDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosSpringApplication.class, args);
	}

	//Metodo implementado de la intf CommandLineRunner, nos ayuda a ejecutar aquellos elementos definidos en codigo
	//de esta forma estamos utilizando la dependencia inyectada dentro de otro objeto y otra clase.
	@Override
	public void run(String... args) throws Exception {
		/*Cuando ya tienes inyectada la dependencia, lo que haces para la ejecución es llamar a su implementación*/
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
	}



}
