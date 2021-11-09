package com.RocketTeam.Models;

import java.sql.Time;

public class Restaurante extends ModelDefault {
	public static class Colunas {
		public static String Id = "ID";
		public static String cnpj = "CNPJ";
		public static String email = "EMAIL";
		public static String nm_fantasia = "NM_FANTASIA";
		public static String ds_especialidade = "DS_ESPECIALIDADE";
		public static String hr_abertura = "HR_ABERTURA";
		public static String hr_fechamento = "HR_FECHAMENTO";
		public static String nr_funcionarios = "NR_FUNCIONARIOS";
		public static String retirada_presencial = "RETIRADA_PRESENCIAL";
		public static String entrega_propria = "ENTREGA_PROPRIA";
	}
	
	long id = 0;
    String cnpj = "";
    String email = "";
    String nm_fantasia = "";
    String ds_especialidade = "";
    Time hr_abertura = null;
    Time hr_fechamento = null;
    int nr_funcionarios = 0;
    boolean retirada_presencial = false;
    boolean entrega_propria = false;
    
	public Restaurante(long id, String cnpj, String email, String nm_fantasia, String ds_especialidade,
			Time hr_abertura, Time hr_fechamento, int nr_funcionarios, boolean retirada_presencial,
			boolean entrega_propria) {
		this.id = id;
		this.cnpj = cnpj;
		this.email = email;
		this.nm_fantasia = nm_fantasia;
		this.ds_especialidade = ds_especialidade;
		this.hr_abertura = hr_abertura;
		this.hr_fechamento = hr_fechamento;
		this.nr_funcionarios = nr_funcionarios;
		this.retirada_presencial = retirada_presencial;
		this.entrega_propria = entrega_propria;
	}
    
	public Restaurante(String cnpj, String email, String nm_fantasia, String ds_especialidade,
			Time hr_abertura, Time hr_fechamento, int nr_funcionarios, boolean retirada_presencial,
			boolean entrega_propria) {
		this.cnpj = cnpj;
		this.email = email;
		this.nm_fantasia = nm_fantasia;
		this.ds_especialidade = ds_especialidade;
		this.hr_abertura = hr_abertura;
		this.hr_fechamento = hr_fechamento;
		this.nr_funcionarios = nr_funcionarios;
		this.retirada_presencial = retirada_presencial;
		this.entrega_propria = entrega_propria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNm_fantasia() {
		return nm_fantasia;
	}

	public void setNm_fantasia(String nm_fantasia) {
		this.nm_fantasia = nm_fantasia;
	}

	public String getDs_especialidade() {
		return ds_especialidade;
	}

	public void setDs_especialidade(String ds_especialidade) {
		this.ds_especialidade = ds_especialidade;
	}

	public Time getHr_abertura() {
		return hr_abertura;
	}

	public void setHr_abertura(Time hr_abertura) {
		this.hr_abertura = hr_abertura;
	}

	public Time getHr_fechamento() {
		return hr_fechamento;
	}

	public void setHr_fechamento(Time hr_fechamento) {
		this.hr_fechamento = hr_fechamento;
	}

	public int getNr_funcionarios() {
		return nr_funcionarios;
	}

	public void setNr_funcionarios(int nr_funcionarios) {
		this.nr_funcionarios = nr_funcionarios;
	}

	public boolean isRetirada_presencial() {
		return retirada_presencial;
	}

	public void setRetirada_presencial(boolean retirada_presencial) {
		this.retirada_presencial = retirada_presencial;
	}

	public boolean isEntrega_propria() {
		return entrega_propria;
	}

	public void setEntrega_propria(boolean entrega_propria) {
		this.entrega_propria = entrega_propria;
	}

	@Override
	public Object getPk() {
		return getId();
	}

	@Override
	public String toString() {
		return getId() + ", " +  getCnpj() + ", " + getNm_fantasia() + ", " + getEmail() + ", " + getDs_especialidade() + ", " + 
				getNr_funcionarios() + ", " + getHr_abertura() + ", " + getHr_fechamento();
	}

	@Override
	public String toJson() {
		return ("{'" + Colunas.Id + "':'" + getId() + "'," + 
				"'" + Colunas.cnpj + "':'" + getCnpj() + "'," + 
				"'" + Colunas.email + "':'" + getEmail() + "'," +
				"'" + Colunas.nm_fantasia + "':'" + getNm_fantasia() + "'," + 
				"'" + Colunas.ds_especialidade + "':'" + getDs_especialidade() + "'," + 
				"'" + Colunas.hr_abertura + "':'" + getHr_abertura() + "'," +
				"'" + Colunas.hr_fechamento + "':'" + getHr_fechamento() +  "'," +
				"'" + Colunas.nr_funcionarios + "':'" + getNr_funcionarios() +  "'," +
				"'" + Colunas.retirada_presencial + "':'" + isRetirada_presencial() + "'," + 
				"'" + Colunas.entrega_propria + "':'" + isEntrega_propria() + "'}").replace("'", "\"");
	}

	public static String toJson(ModelDefault[] objs) {
		return toJson(objs);
	}
	
	public String toJson(Restaurante[] objs) {
		String s = "[";
		for (Restaurante r : objs) {
			s += r.toJson() + ",";
		}
		s = (s + "]").replace(",]", "]").replace("'", "\"");
		return s;
	}
	
}
