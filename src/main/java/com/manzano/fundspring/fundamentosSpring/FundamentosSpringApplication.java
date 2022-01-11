package com.manzano.fundspring.fundamentosSpring;

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

	/*Cuando tenemos que la dependencia esta implementada en dos o mas clases/objetos/beans, se debe definir cual
	 de esas implementaciones es la que se va a utilizar*/
	public FundamentosSpringApplication(@Qualifier("componentImplementTwo") ComponentDependency componentDependency) {
		this.componentDependency = componentDependency;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosSpringApplication.class, args);
	}

	//Metodo implementado de la intf CommandLineRunner, nos ayuda a ejecutar aquellos elementos definidos en codigo
	//de esta forma estamos utilizando la dependencia inyectada dentro de otro objeto y otra clase.
	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
	}



}
