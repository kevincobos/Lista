package com.cobosideas.lista.activities.manage_functions;


public class FunctionTypeMoneyAmount {
    public String name = "";
    public int number = 0;
    public Long dateFunctionCreated = 0L;
    public Long dateFunctionModify = 0L;

    public FunctionTypeMoneyAmount(String newName, int newValue){
        this.name = newName;
        this.number = newValue;
        this.dateFunctionCreated = System.currentTimeMillis();
        this.dateFunctionModify = System.currentTimeMillis();
    }
}
