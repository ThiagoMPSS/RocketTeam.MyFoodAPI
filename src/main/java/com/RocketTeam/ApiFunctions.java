package com.RocketTeam;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.RocketTeam.Dao.EnderecoDao;
import com.RocketTeam.Dao.IDao;
import com.RocketTeam.Dao.RestauranteDao;
import com.RocketTeam.Dao.TelefoneDao;
import com.RocketTeam.Infos.RestauranteInfos;
import com.RocketTeam.Models.Endereco;
import com.RocketTeam.Models.ModelDefault;
import com.RocketTeam.Models.Restaurante;
import com.RocketTeam.Models.Telefone;
import com.RocketTeam.Models.Restaurante.Colunas;

public class ApiFunctions {
	
	private static IDao GetDao(String Tabela) {
		switch (Tabela.toLowerCase()) {
			case "endereco":
				return new EnderecoDao();
			case "telefone":
				return new TelefoneDao();
			case "restaurante":
				return new RestauranteDao();
		}
		return null; 
	}
	
	public static ModelDefault GetModel(String Tabela, JSONObject objs) {
		switch (Tabela.toLowerCase()) {
			case "telefone":{
				long id = -1;
				if (objs.containsKey(Telefone.Colunas.Id.toUpperCase()))
					id = Long.parseLong((String) objs.get(Telefone.Colunas.Id.toUpperCase()));
				long rest_id = -1;
				if (objs.containsKey(Telefone.Colunas.Rest_Id.toUpperCase()))
					rest_id = Long.parseLong((String) objs.get(Telefone.Colunas.Rest_Id.toUpperCase()));
				int DDD = Integer.parseInt((String) objs.get(Telefone.Colunas.DDD.toUpperCase()));
				int Numero = Integer.parseInt((String) objs.get(Telefone.Colunas.Numero.toUpperCase()));
				System.out.println("Telefone:");
				System.out.println("	Id:" +  id);
				System.out.println("	rest_id:" + rest_id);
				System.out.println("	DDD:" + DDD);
				System.out.println("	Numero:" + Numero);
				
				return new Telefone(id, rest_id, DDD, Numero);
			}
			case "endereco":{	
				long id = -1;
				if (objs.containsKey(Endereco.Colunas.Id.toUpperCase()))
					id = Long.parseLong((String) objs.get(Endereco.Colunas.Id.toUpperCase()));
				long rest_id = -1;
				if (objs.containsKey(Endereco.Colunas.Rest_id.toUpperCase()))
					rest_id = Long.parseLong((String) objs.get(Endereco.Colunas.Rest_id.toUpperCase()));
				String logradouro = (String) objs.get(Endereco.Colunas.Logradouro.toUpperCase());
				long nr = Long.parseLong((String) objs.get(Endereco.Colunas.Nr.toUpperCase()));
				String bairro = (String) objs.get(Endereco.Colunas.Bairro.toUpperCase());
				String cidade = (String) objs.get(Endereco.Colunas.Cidade.toUpperCase());
				String estado = (String) objs.get(Endereco.Colunas.Estado.toUpperCase());
				System.out.println("Endereço:");
				System.out.println("	Id:" + id);
				System.out.println("	Rest_ID:" + rest_id);
				System.out.println("	Logradouro:" + logradouro);
				System.out.println("	NR:" + nr);
				System.out.println("	Bairro:" + bairro);
				System.out.println("	Cidade:" + cidade);
				System.out.println("	Estado:" + estado);
				
				if (id > -1)
					return new Endereco(id, rest_id, logradouro, nr, bairro, cidade, estado);
				else if (rest_id > -1)
					return new Endereco(rest_id, logradouro, nr, bairro, cidade, estado);
				else
					return new Endereco(logradouro, nr, bairro, cidade, estado);
			}
			case "restaurante":{
				long id = -1;
				if (objs.containsKey(Restaurante.Colunas.Id.toUpperCase())) {
					id = Long.parseLong((String) objs.get(Restaurante.Colunas.Id.toUpperCase()));
				    System.out.println(id);
				}
			    String cnpj = (String) objs.get(Restaurante.Colunas.cnpj.toUpperCase());
			    String email = (String) objs.get(Restaurante.Colunas.email.toUpperCase());
			    String nm_fantasia = (String) objs.get(Restaurante.Colunas.nm_fantasia.toUpperCase());
			    String ds_especialidade = (String) objs.get(Restaurante.Colunas.ds_especialidade.toUpperCase());
			    String hr = (String)objs.get(Restaurante.Colunas.hr_abertura.toUpperCase());
			    Time hr_abertura;
			    if (hr.split(":").length < 3)
			    	hr_abertura = Time.valueOf(hr + ":00");
			    else
			    	hr_abertura = Time.valueOf(hr);
			    Time hr_fechamento;
			    hr = (String) objs.get(Restaurante.Colunas.hr_fechamento.toUpperCase());
			    if (hr.split(":").length < 3)
			    	hr_fechamento = Time.valueOf(hr + ":00");
			    else
			    	hr_fechamento = Time.valueOf(hr);
			    int nr_funcionarios = Integer.parseInt((String) objs.get(Restaurante.Colunas.nr_funcionarios.toUpperCase()));
			    boolean retirada_presencial = Boolean.parseBoolean((String) objs.get(Colunas.retirada_presencial.toUpperCase()));
			    boolean entrega_propria = Boolean.parseBoolean((String) objs.get(Colunas.entrega_propria.toUpperCase()));
			    System.out.println("Restaurante:");
			    System.out.println("	CNPJ:" + cnpj);
			    System.out.println("	EMAIL:" + email);
			    System.out.println("	NM Fantasia:" + nm_fantasia);
			    System.out.println("	DS Especialidade:" + ds_especialidade);
			    System.out.println("	HR Abertura:" + hr_abertura);
			    System.out.println("	HR Fechamento:" + hr_fechamento);
			    System.out.println("	NR Funcionarios:" + nr_funcionarios);
			    System.out.println("	Retirada Presencial:" + retirada_presencial);
			    System.out.println("	Entrega Propria:" + entrega_propria);
				
			    if (id > -1)
			    	return new Restaurante(id, cnpj, email, nm_fantasia, ds_especialidade, hr_abertura, hr_fechamento,
							nr_funcionarios, retirada_presencial, entrega_propria);
			    else
			    	return new Restaurante(cnpj, email, nm_fantasia, ds_especialidade, hr_abertura, hr_fechamento,
							nr_funcionarios, retirada_presencial, entrega_propria);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static RestauranteInfos GetModelRestInfos(JSONObject objs) {
		JSONObject restaurante = (JSONObject) objs.get("Restaurante");
		JSONArray tels = (JSONArray) objs.get("Telefones");
		JSONArray ends = (JSONArray) objs.get("Enderecos");
		System.out.println(restaurante);
		
		List<Telefone> telsList = new ArrayList<Telefone>();
		List<Endereco> endsList = new ArrayList<Endereco>();
		
		tels.forEach(e -> telsList.add((Telefone) GetModel("Telefone", (JSONObject) e)));
		ends.forEach(e -> endsList.add((Endereco) GetModel("Endereco", (JSONObject) e)));
		
		Telefone[] telsArray = new Telefone[telsList.size()];
		Endereco[] endsArray = new Endereco[endsList.size()];
		telsList.toArray(telsArray);
		endsList.toArray(endsArray);
		
		return new RestauranteInfos((Restaurante) GetModel("Restaurante", restaurante),
									telsArray, endsArray);
	}
	
	public static boolean Add(String Tabela, JSONObject objs) throws Exception {
		IDao dao = GetDao(Tabela);
		ModelDefault model = GetModel(Tabela, objs);
		return dao.add(model) != null;
	}
	
	public static boolean AddInfos(String Tabela, JSONObject objs) throws Exception {
		RestauranteInfos rest = GetModelRestInfos(objs);
		return rest.add();
	}
	
	public static boolean Update(String Tabela, JSONObject objs) throws Exception {
		IDao dao = GetDao(Tabela);
		ModelDefault model = GetModel(Tabela, objs);
		return dao.update(model);
	}
	
	public static boolean UpdateInfos(String Tabela, JSONObject objs) throws Exception {
		RestauranteInfos rest = GetModelRestInfos(objs);
		return rest.update();
	}
	
	public static ModelDefault Get(String Tabela, long Pk) throws Exception {
		return GetDao(Tabela).get(Pk);
	}
	
	public static RestauranteInfos GetInfos(String Tabela, long Pk) throws Exception {
		return RestauranteInfos.Get(Tabela, Pk);
	}

	public static ModelDefault[] GetAll(String Tabela) throws Exception {
		return GetDao(Tabela).getAll();
	}
	
	public static RestauranteInfos[] GetAllInfos(String Tabela) throws Exception {
		return RestauranteInfos.GetAll(Tabela);
	}
	
	public static ModelDefault[] GetArray(String Tabela, long Fk) throws Exception {
		return GetDao(Tabela).getByFk(Fk);
	}
	
	public static boolean Delete(String Tabela, long Pk, long Fk) throws Exception {
		IDao dao = GetDao(Tabela);
		
		if (Pk > -1) {
			if (Tabela.toLowerCase().equals("restaurante")) {
				System.out.println("Delete Restaurante");
				boolean deletado = new TelefoneDao().deleteByFk(Pk);
				deletado = deletado ? new EnderecoDao().deleteByFk(Pk) : false;
				return deletado ? new RestauranteDao().delete(Pk) : false;
			}
			return dao.delete(Pk);
		}else
			return dao.delete(Fk);
	}
	
}
