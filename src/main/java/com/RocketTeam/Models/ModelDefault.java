package com.RocketTeam.Models;

public abstract class ModelDefault {	
    //Metodo responsavel por retornar o valor da chave primaria
    public abstract Object getPk();
    public abstract String toString();
    public abstract String toJson();
}
