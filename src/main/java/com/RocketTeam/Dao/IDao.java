package com.RocketTeam.Dao;

import java.sql.Connection;

import com.RocketTeam.Models.ModelDefault;

public interface IDao {
	ModelDefault get(long id) throws Exception;
	ModelDefault[] getByFk(long id) throws Exception;
	ModelDefault[] getAll() throws Exception;
	ModelDefault add(ModelDefault obj, Connection conn) throws Exception;
	ModelDefault add(ModelDefault obj) throws Exception;
	Boolean delete(long id, Connection conn) throws Exception;
	Boolean delete(long id) throws Exception;
	Boolean deleteByFk(long id, Connection conn) throws Exception;
	Boolean deleteByFk(long id) throws Exception;
	Boolean delete(Connection conn) throws Exception;
	Boolean delete() throws Exception;
	Boolean update(ModelDefault obj, Connection conn) throws Exception;
	Boolean update(ModelDefault obj) throws Exception;
	Boolean updateByFk(ModelDefault obj, Connection conn) throws Exception;
	Boolean updateByFk(ModelDefault obj) throws Exception;
	
	//Retorna o ultimo valor da chave primaria
	int getLastIndex() throws Exception;
	int getLastIndex(Connection conn) throws Exception;
}
