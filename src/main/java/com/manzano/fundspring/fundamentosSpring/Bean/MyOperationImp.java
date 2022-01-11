package com.manzano.fundspring.fundamentosSpring.Bean;

public class MyOperationImp implements MyOperation{
    @Override
    public int sum(int numero) {
        return numero+1;
    }
}
