package com.RocketTeam.Models;

public class Telefone extends ModelDefault {
	public static class Colunas {
		public static String Id = "CD_TELEFONE";
		public static String Rest_Id = "RESTAURANTE_ID";
		public static String DDD = "NR_DDD";
		public static String Numero = "NR_TELEFONE";
	}
	
	private long Id = 0;
	private long Rest_id = 0;
	private int DDD = 0;
	private int Numero = 0;
	
	public Telefone(long Id, long Rest_id, int DDD, int Numero) {
		this.Id = Id;
		this.Rest_id = Rest_id;
		this.DDD = DDD;
		this.Numero = Numero;
	}
	
	public Telefone(long Rest_id, int DDD, int Numero) {
		this.Rest_id = Rest_id;
		this.DDD = DDD;
		this.Numero = Numero;
	}
	
	public Telefone(int DDD, int Numero) {
		this.DDD = DDD;
		this.Numero = Numero;
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

	public int getDDD() {
		return DDD;
	}

	public void setDDD(int DDD) {
		this.DDD = DDD;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}
	
	@Override
	public Object getPk() {
		return getId();
	}
	
	@Override
	public String toString() {
		return getId() + ", " + getRest_id() + ", " + getDDD() + ", " + getNumero();
	}

	@Override
	public String toJson() {
		return ("{'" + Colunas.Id + "':'" + getId() + "'," +
				"'" + Colunas.Rest_Id + "':'" + getRest_id() + "'," +
				"'" + Colunas.DDD + "':'" + getDDD() + "'," +
				"'" + Colunas.Numero + "':'" + getNumero() + "'}").replace("'", "\"");
	}
	
	public static String toJson(ModelDefault[] objs) {
		return toJson((Telefone[]) objs);
	}
	
	public static String toJson(Telefone[] objs) {
		String s = "[";
		for (Telefone e : objs) {
			s += e.toJson() + ",";
		}
		s = (s + "]").replace(",]", "]").replace("'", "\"");
		return s;
	}

}
