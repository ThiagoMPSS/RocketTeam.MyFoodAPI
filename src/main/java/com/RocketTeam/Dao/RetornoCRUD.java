package com.RocketTeam.Dao;

import java.sql.Connection;

public class RetornoCRUD<T> {
	T Retorno;
	Connection Conn;
	
	public RetornoCRUD(T Retorno, Connection Conn) {
		this.Retorno = Retorno;
		this.Conn = Conn;
	}
	
	public T GetRetorno() {
		return Retorno;
	}
	
	public Connection getConn() {
		return Conn;
	}
}
