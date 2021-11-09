package com.RocketTeam.Models;

public class Endereco extends ModelDefault {
	public static class Colunas {
		public static String Id =  "CD_ENDERECO";
		public static String Rest_id = "RESTAURANTE_ID";
		public static String Logradouro = "DS_LOGRADOURO";
		public static String Nr = "NR_ENDERECO";
		public static String Bairro = "NM_BAIRRO";
		public static String Cidade = "NM_CIDADE";
		public static String Estado = "NM_ESTADO";
	}
	
	private long Id =  0;
	private long Rest_id = 0;
	private String Logradouro = "";
	private long Nr = 0;
	private String Bairro = "";
	private String Cidade = "";
	private String Estado = "";
	
	public Endereco(long Id, long Restaurante_id, String Logradouro, long Nr, String Bairro, String Cidade, String Estado) {
		this.Id = Id;
		this.Rest_id = Restaurante_id;
		this.Logradouro = Logradouro;
		this.Nr = Nr;
		this.Bairro = Bairro;
		this.Cidade = Cidade;
		this.Estado = Estado;
	}
	
	public Endereco(long Restaurante_id, String Logradouro, long Nr, String Bairro, String Cidade, String Estado) {
		this.Rest_id = Restaurante_id;
		this.Logradouro = Logradouro;
		this.Nr = Nr;
		this.Bairro = Bairro;
		this.Cidade = Cidade;
		this.Estado = Estado;
	}
	
	public Endereco(String Logradouro, long Nr, String Bairro, String Cidade, String Estado) {
		this.Logradouro = Logradouro;
		this.Nr = Nr;
		this.Bairro = Bairro;
		this.Cidade = Cidade;
		this.Estado = Estado;
	}
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public long getRest_id() {
		return Rest_id;
	}

	public void setRest_id(long rest_id) {
		Rest_id = rest_id;
	}

	public String getLogradouro() {
		return Logradouro;
	}

	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}

	public long getNr() {
		return Nr;
	}

	public void setNr(long nr) {
		Nr = nr;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	@Override
	public Object getPk() {
		return getId();
	}
	
	@Override
	public String toString() {
		return getId() + ", " + getRest_id() + ", " + getLogradouro() + ", " + getNr() + ", " + getBairro() + ", " +
				getCidade() + ", " + getEstado(); 
	}
	
	@Override
	public String toJson() {
		return ("{'" + Colunas.Id + "':'" + getId() + "'," +
				"'" + Colunas.Rest_id + "':'" + getRest_id() + "'," +
				"'" + Colunas.Logradouro + "':'" + getLogradouro() + "'," +
				"'" + Colunas.Nr + "':'" + getNr() + "'," +
				"'" + Colunas.Bairro + "':'" + getBairro() + "'," +
				"'" + Colunas.Cidade + "':'" + getCidade() + "'," +
				"'" + Colunas.Estado + "':'" + getEstado() + "'}").replace("'", "\""); 
	}
	
	public static String toJson(ModelDefault[] objs) {
		return toJson((Endereco[]) objs);
	}
	
	public static String toJson(Endereco[] objs) {
		String s = "[";
		for (Endereco e : objs) {
			s += e.toJson() + ",";
		}
		s = (s + "]").replace(",]", "]").replace("'", "\"");
		return s;
	}
}
