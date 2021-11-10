package com.RocketTeam;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.RocketTeam.Infos.RestauranteInfos;
import com.RocketTeam.Models.ModelDefault;

public class Program extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Program() {
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("Funcao") != null) {
				switch (request.getParameter("Funcao").toString().toLowerCase()) {
					case "get":
					case "getall":
						doPost(request, response);
						return;
				}
			}
			CriarHeaderStatus(response, HttpServletResponse.SC_BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONObject body;
			if (request.getParameterNames().hasMoreElements()) {
				body = new JSONObject();
				body.put("Funcao", request.getParameter("Funcao"));
				body.put("Tabela", request.getParameter("Tabela"));
				if (request.getParameter("Pk") != null)
					body.put("Pk", request.getParameter("Pk"));
				if (request.getParameter("Fk") != null)
					body.put("Fk", request.getParameter("Fk"));
			}else
				body = (JSONObject)new JSONParser().parse(request.getReader());
			
			PrintWriter writer = response.getWriter();
			
			String Tabela = (String) body.get("Tabela");

			long Pk = -1;
			long Fk = -1;

			if (body.containsKey("Pk") || body.containsKey("Fk")) {
				try {
					Pk = Long.parseLong((String) body.get("Pk"));
				} catch (NumberFormatException e1) {
					try {
						Fk = Long.parseLong((String) body.get("Fk"));
					} catch (NumberFormatException e2) {
						CriarHeaderStatus(response, HttpServletResponse.SC_BAD_REQUEST);
						return;
					}
				}
			}
			
			switch (body.get("Funcao").toString().toLowerCase()) {
				case "get":{
					if (Tabela.toLowerCase().equals("restaurante*")) {
						CriarHeaderJson(response);
						writer.append(ApiFunctions.GetInfos(Tabela, Pk).toJson());
						return;
					} else {
						if (Pk > -1) {
							ModelDefault ret = ApiFunctions.Get(Tabela, Pk);
							if (ret != null) {
								CriarHeaderJson(response);
								writer.append(ret.toJson());
								return;
							} else
								CriarHeaderStatus(response, HttpServletResponse.SC_NOT_ACCEPTABLE);
							return;
						} else if (Fk > -1){
							ModelDefault[] ret = ApiFunctions.GetArray(Tabela, Fk);
							if (ret != null) {
//								System.out.println(ret.length);
								if (ret.length > 0) {
									CriarHeaderJson(response);
									writer.append(ModelsToJson(ret));
									return;
								}
								CriarHeaderStatus(response, HttpServletResponse.SC_NOT_ACCEPTABLE);
								return;
							}
						}
					}
					break;
				}
				case "getall":{
					if (Tabela.toLowerCase().equals("restaurante*")) { 
						CriarHeaderJson(response);
						writer.append(RestauranteToJson(ApiFunctions.GetAllInfos(Tabela)));
					} else {
						ModelDefault[] ret = ApiFunctions.GetAll(Tabela);
						CriarHeaderJson(response);
						writer.append(ModelsToJson(ret));
					}
					return;
				}
				case "delete": {
					if (ApiFunctions.Delete(Tabela, Pk, Fk))
						CriarHeaderStatus(response, HttpServletResponse.SC_OK);
					else 
						CriarHeaderStatus(response, HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				case "add": {
					JSONObject formDados = (JSONObject) body.get("Dados");
					if (Tabela.toLowerCase().equals("restaurante*")) {
						if (ApiFunctions.AddInfos(Tabela, formDados))
							CriarHeaderStatus(response, HttpServletResponse.SC_OK);
						else 
							CriarHeaderStatus(response, HttpServletResponse.SC_NOT_FOUND);
					} else {
						if (ApiFunctions.Add(Tabela, formDados))
							CriarHeaderStatus(response, HttpServletResponse.SC_OK);
						else 
							CriarHeaderStatus(response, HttpServletResponse.SC_NOT_FOUND);
					}
					return;
				}
				case "update": {
					JSONObject formDados = (JSONObject) body.get("Dados");
					if (Tabela.toLowerCase().equals("restaurante*")) {
						if (ApiFunctions.UpdateInfos(Tabela, formDados, Pk))
							CriarHeaderStatus(response, HttpServletResponse.SC_OK);
						else 
							CriarHeaderStatus(response, HttpServletResponse.SC_NOT_FOUND);
					} else {
						if (ApiFunctions.Update(Tabela, formDados))
							CriarHeaderStatus(response, HttpServletResponse.SC_OK);
						else 
							CriarHeaderStatus(response, HttpServletResponse.SC_NOT_FOUND);
					}
					return;
				}
			}
		} catch (Exception ex) {
			CriarHeaderStatus(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			ex.printStackTrace();
			return;
		}
		CriarHeaderStatus(response, HttpServletResponse.SC_BAD_REQUEST);
	}
	
	private String ModelsToJson(ModelDefault[] objs) {
		String s = "[";
		for (ModelDefault r : objs) {
			s += r.toJson() + ",";
		}
		s = (s + "]").replace(",]", "]").replace("'", "\"");
		return s;
	}
	
	private String RestauranteToJson(RestauranteInfos[] objs) {
		String s = "[";
		for (RestauranteInfos r : objs) {
			s += r.toJson() + ",";
		}
		s = (s + "]").replace(",]", "]").replace("'", "\"");
		return s;
	}
    
	private void CriarHeaderJson(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
	}
    
	private void CriarHeaderStatus(HttpServletResponse response, int StatusCode) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.sendError(StatusCode);
	}

}
