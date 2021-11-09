package com.RocketTeam.Infos;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.RocketTeam.Configs.ConnManager;
import com.RocketTeam.Dao.EnderecoDao;
import com.RocketTeam.Dao.RestauranteDao;
import com.RocketTeam.Dao.TelefoneDao;
import com.RocketTeam.Models.Endereco;
import com.RocketTeam.Models.Restaurante;
import com.RocketTeam.Models.Telefone;

public class RestauranteInfos {
	Restaurante Rest;
	Telefone[] Tels;
	Endereco[] Ends;
	
	public RestauranteInfos(Restaurante Rest, Telefone[] Tels, Endereco[] Ends) {
		this.Rest = Rest;
		this.Tels = Tels;
		this.Ends = Ends;
	}
	
	public static RestauranteInfos Get(String Tabela, long Pk) throws Exception {
		Restaurante rest = (Restaurante) new RestauranteDao().get(Pk);
		Telefone[] tels = (Telefone[]) new TelefoneDao().getByFk(Pk);
		Endereco[] ends = (Endereco[]) new EnderecoDao().getByFk(Pk);
		
		RestauranteInfos restInfos = new RestauranteInfos(rest, tels, ends);
		return restInfos;
	}
	
	public static RestauranteInfos[] GetAll(String Tabela) throws Exception {
		Restaurante[] rests = (Restaurante[]) new RestauranteDao().getAll();
		Telefone[] tels = (Telefone[]) new TelefoneDao().getAll();
		Endereco[] ends = (Endereco[]) new EnderecoDao().getAll();

		List<RestauranteInfos> ri = new ArrayList<RestauranteInfos>(); 
		
		for (Restaurante r : rests) {
			List<Telefone> ts = new ArrayList<Telefone>();
			List<Endereco> es = new ArrayList<Endereco>();
			for (Telefone t : tels) {
				if (t.getRest_id() == r.getId()) {
					ts.add(t);
				}
			}
			for (Endereco e : ends) {
				if (e.getRest_id() == r.getId()) {
					es.add(e);
				}
			}
			
			Telefone[] tsArray = new Telefone[ts.size()];
			Endereco[] esArray = new Endereco[es.size()];
			ts.toArray(tsArray);
			es.toArray(esArray);
			
			ri.add(new RestauranteInfos(r, tsArray, esArray));
		}
		
		RestauranteInfos[] riArray = new RestauranteInfos[ri.size()];
		ri.toArray(riArray);
		return riArray;
	}
	
	public Restaurante getRestaurante() {
		return Rest;
	}
	
	public Telefone[] getTelefones() {
		return Tels;
	}
	
	public Endereco[] getEnderecos() {
		return Ends;
	}
	
	public boolean add() throws Exception {
		boolean adicionado = true;
		Connection conn = ConnManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		Rest = (Restaurante) new RestauranteDao().add(Rest, conn);
		
		adicionado = adicionado ? Rest != null : false;
		if (adicionado) {
			for (Telefone tel : Tels) {
				tel.setRest_id((long)Rest.getPk());
				adicionado = adicionado ? new TelefoneDao().add(tel, conn) != null : false;
			}
			for (Endereco end : Ends) {
				end.setRest_id((long)Rest.getPk());
				adicionado = adicionado ? new EnderecoDao().add(end, conn) != null : false;
			}
		}
		
		if (!adicionado)
			conn.rollback();
		else
			conn.commit();
		
		return adicionado;
	}
	
	public boolean update() throws Exception {
		boolean adicionado = true;
		Connection conn = ConnManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		adicionado = adicionado ? new RestauranteDao().update(Rest, conn) : false;
		for (Telefone tel : Tels) {
			adicionado = adicionado ? new TelefoneDao().update(tel, conn) : false;
		}
		for (Endereco end : Ends) {
			adicionado = adicionado ? new EnderecoDao().update(end, conn) : false;
		}
		
		if (!adicionado)
			conn.rollback();
		else
			conn.commit();
		
		return adicionado;
	}
	
	public boolean delete() throws Exception {
		boolean adicionado = true;
		Connection conn = ConnManager.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		adicionado = adicionado ? new RestauranteDao().delete(Rest.getId(), conn) : false;
		for (Telefone tel : Tels) {
			adicionado = adicionado ? new TelefoneDao().delete(tel.getId(), conn) : false;
		}
		for (Endereco end : Ends) {
			adicionado = adicionado ? new EnderecoDao().delete(end.getId(), conn) : false;
		}
		
		if (!adicionado)
			conn.rollback();
		else
			conn.commit();
		
		return adicionado;
	}
	
	public String toJson() {
		return ("{'Restaurante':" + getRestaurante().toJson() + "," +
				"'Telefones':" + Telefone.toJson(getTelefones()) + "," +
				"'Enderecos':" + Endereco.toJson(getEnderecos()) + "}").replace("'", "\"");
	}
}
