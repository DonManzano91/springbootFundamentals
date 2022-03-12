package com.manzano.fundspring.fundamentosSpring;

import com.manzano.fundspring.fundamentosSpring.Bean.MyBean;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithDependency;
import com.manzano.fundspring.fundamentosSpring.Bean.MyBeanWithProperties;
import com.manzano.fundspring.fundamentosSpring.Component.ComponentDependency;
import com.manzano.fundspring.fundamentosSpring.Entity.User;
import com.manzano.fundspring.fundamentosSpring.Pojos.UserPojo;
import com.manzano.fundspring.fundamentosSpring.Repository.UserRepository;
import com.manzano.fundspring.fundamentosSpring.Service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
	private UserService userService;

	/*Se inyecta el repositorio como dependencia para poder hacer uso del comportamiento db y darle persistencia a la
	* info de tipo User*/
	private UserRepository userRepository;


	/*Cuando tenemos que la dependencia esta implementada en dos o mas clases/objetos/beans, se debe definir cual
	 de esas implementaciones es la que se va a utilizar*/
	public FundamentosSpringApplication(@Qualifier("componentImplementTwo") ComponentDependency componentDependency,
										MyBean myBean,
										MyBeanWithDependency myBeanWithDependency,
										MyBeanWithProperties myBeanWithProperties,
										UserPojo userPojo,
										UserRepository userRepository,
										UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean; //Se pasa la dependencia como parte del constructor para que se instancie el momento
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;

	}

	//El metodo main ejecutara el especifivo run que se defina en la aplicación.
	public static void main(String[] args) {
		SpringApplication.run(FundamentosSpringApplication.class, args);
	}

	//Metodo implementado de la intf CommandLineRunner, nos ayuda a ejecutar aquellos elementos definidos en codigo
	//de esta forma estamos utilizando la dependencia inyectada dentro de otro objeto y otra clase.
	@Override
	public void run(String... args) throws Exception {
		/*Se llama a este metodo base que contiene la implementación de todos los ejemplos vistos hasta el video 19
		* del curso fundamentos de springboot*/
		//ejemplosAnteriores();

		//Estos siguientes utilizan la instancia de userRepository
		saveUserAtDataBase(); //Este usa la config de dataSource/h2 como db emebeida
		getInfoFromJpqlReference(); //Este usa una implementación JPQL

		/*Como utilizamos la referencia de userRepository para el getAll dentro de la transacción aqui ejecutada
		Se añadio completas ambas listas, y por eso las carga todas de golpe, aunque no hayan sido ejecutas juntas,
		por que se trabaja sobre la persistencia, no sobre un objeto particular.
		 */
		saveWithErrorTransactional();


	}

	public void saveWithErrorTransactional(){
		User user1 = new User("Alex", "alex@email.com", LocalDate.of(1991, 3, 21));
		User user2 = new User("Marce", "marce@email.com", LocalDate.of(1966, 4, 25));
		User user3 = new User("Ricardo", "ricardo@email.com", LocalDate.of(1994, 9, 18));
		User user4 = new User("Luz", "luz@email.com", LocalDate.of(1998, 11, 12));
		User user5 = new User("Shanti", "shanti@email.com", LocalDate.of(2000, 1, 9));
		List<User> listaUsuarios2 = Arrays.asList(user1, user2, user3, user4, user5);

		try {
			userService.saveTransaction(listaUsuarios2);
		} catch (Exception e){
			LOG.error("Se pudrio por la exception: " + e);
		}

		userService.getAll().stream()
				.forEach(user -> LOG.info("Usuario a guardar con transacción " + user));
	}

	public void ejemplosAnteriores(){
		try {
			/*Cuando ya tienes inyectada la dependencia, lo que haces para la ejecución es llamar a su implementación*/
			componentDependency.saludar();
			myBean.print();
			myBeanWithDependency.printWithDependency();
			System.out.println(myBeanWithProperties.function());
			System.out.println(userPojo.getMail() + " - " + userPojo.getPassword());
			LOG.info("Se implementaron correctamente las dependencias");

		} catch (Exception e){
			LOG.error("La ejecución de alguna implementación de ejemplo fallo por " + e);
		}
	}

	/*Implementaciones de User ya con operaciones CRUD en la db H2 embebida*/
	private void saveUserAtDataBase(){
		User user1 = new User("juan", "juan@email.com", LocalDate.of(1991, 12, 21));
		User user2 = new User("Janelo", "janelo@email.com", LocalDate.of(1991, 2, 22));
		User user3 = new User("juliana", "juliana@email.com", LocalDate.of(1981, 12, 23));
		User user4 = new User("julio", "julio@email.com", LocalDate.of(1998, 11, 12));
		User user5 = new User("julia", "julia@email.com", LocalDate.of(2000, 01, 9));
		List<User> listaUsuarios = Arrays.asList(user1, user2, user3, user4, user5);
		listaUsuarios.stream().forEach(userRepository::save);
		System.out.println("Se crearon usuarios");
	}

	/*En este metodo vamos consultando la información instanciada en saveUserAtDataBase() */
	private void getInfoFromJpqlReference(){
		LOG.info("Usuario por correo encontrado " +
		userRepository.findByUserEmail("juan@email.com") //caso correcto
						//userRepository.findByUserEmail("otrojuan@email.com") //caso incorrecto
				.orElseThrow(()->new RuntimeException("No se encontro el usuario"))
		);

		userRepository.findAndSort("j", Sort.by("id").descending())
				.stream().forEach(user -> LOG.info("Usuario obtenido por el sort " + user));

		userRepository.findByName("juan")
				.stream()
				.forEach(user -> LOG.info("Usuarios con el nombre juan: " + user.toString()));

		LOG.info("Query Method email y nombre: " +
				userRepository.findByEmailAndName("juan@email.com", "juan")
						.orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
				);

		userRepository.findByNameLike("%jul%").stream()
				.forEach(user -> LOG.info("Usuarios empiezan con J: " + user));

		userRepository.findByNameOrEmail(null, "juan@email.com").stream()
				.forEach(user -> LOG.info("Encuentra por nombre o correo " + user));

		userRepository.findByDateBirthBetween(LocalDate.of(1990,01,01),
				LocalDate.of(1995,01,01)).stream()
				.forEach(user -> LOG.info("Encontrados por fecha " + user));

		userRepository.findByNameLikeOrderByIdDesc("J").stream()
				.forEach(user -> LOG.info("Empiezan con J ordenados por id: " + user));

		userRepository.findByNameContainingOrderByIdDesc("juliana").stream()
				.forEach(user -> LOG.info("Contiene nombre ordenado por ID " + user));

		userRepository.getAllByBirthDateAndEmail(LocalDate.of(1991, 02, 22),
				"janelo@email.com").stream().forEach(userDto -> LOG.info(
						"Usamos JPQL, video 24 " + userDto));


	}




}
