package com.manzano.fundspring.fundamentosSpring.Bean;

public class MyBeanWithDependencyImp implements MyBeanWithDependency{

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
        int resImpMyOper =  myOperation.sum(5);
        System.out.println(resImpMyOper);
        System.out.println("Mensaje del bean con dependencia");
    }
}
