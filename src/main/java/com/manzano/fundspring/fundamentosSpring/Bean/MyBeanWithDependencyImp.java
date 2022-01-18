package com.manzano.fundspring.fundamentosSpring.Bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImp implements MyBeanWithDependency{

    /*Instanciar un objeto Logger*/

    private Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImp.class);
    /* En esta clase fuimos generando una inyección de dependencia que uso otra dependencia que nosotros ya habiamos
    * generado */

    MyOperation myOperation;

    /* Es con base en este constructor que podemos acceder a nuestras dependencias, digamos que así le damos vida
    * al objeto abstracto que creamos con la interfaz */
    public MyBeanWithDependencyImp(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {

        LOGGER.info("Estamos en el met printWithDependency");
        int resImpMyOper =  myOperation.sum(5);
        LOGGER.debug("Nivel Debug ");
        System.out.println(resImpMyOper);
        System.out.println("Mensaje del bean con dependencia");
    }
}
