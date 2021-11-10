package com.RocketTeam.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RocketTeam.Configs.ConnManager;
import com.RocketTeam.Models.Restaurante;
import com.RocketTeam.Models.ModelDefault;

public class RestauranteDao implements IDao {
	ConnManager conInst = null;	
	
	public RestauranteDao() {
		conInst = ConnManager.getInstance();
	}
	
	@Override
	public ModelDefault get(long id) throws Exception {
		Connection conn = conInst.getConnection();
		 
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from restaurante where id = ?");
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return new Restaurante(id, res.getString("cnpj"), res.getString("email"), res.getString("nm_fantasia"), 
                		res.getString("ds_especialidade"), res.getTime("hr_abertura"), res.getTime("hr_fechamento"),
                		res.getInt("nr_funcionarios"), res.getBoolean("retirada_presencial"), res.getBoolean("entrega_propria"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return null;
	}

	@Override
	public ModelDefault[] getByFk(long id) throws Exception {
		return null;
	}

	@Override
	public ModelDefault[] getAll() throws Exception {
		Connection conn = conInst.getConnection();
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from restaurante");
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				restaurantes.add(new Restaurante(res.getLong("id"), res.getString("cnpj"), res.getString("email"), res.getString("nm_fantasia"), 
                		res.getString("ds_especialidade"), res.getTime("hr_abertura"), res.getTime("hr_fechamento"),
                		res.getInt("nr_funcionarios"), res.getBoolean("retirada_presencial"), res.getBoolean("entrega_propria")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
            throw ex;
		}
		
		Restaurante[] restaurantesArray = new Restaurante[restaurantes.size()];
		restaurantes.toArray(restaurantesArray);
		return restaurantesArray;
	}

	public ModelDefault add(Restaurante restaurante) throws Exception {
        return add((ModelDefault) restaurante);
    }

	public ModelDefault add(Restaurante restaurante, Connection conn) throws Exception {
        return add((ModelDefault) restaurante, conn);
    }
	
	@Override
	public ModelDefault add(ModelDefault obj) throws Exception {
		return add(obj, conInst.getConnection());
	}
	
	@Override
	public ModelDefault add(ModelDefault obj, Connection conn) throws Exception {
		Restaurante restaurante = (Restaurante) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into restaurante (id, cnpj, email, nm_fantasia, ds_especialidade, hr_abertura, hr_fechamento, "
                    + "nr_funcionarios, retirada_presencial, entrega_propria)"
                    + " values(seq_restaurante.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, restaurante.getCnpj());
            pstmt.setString(2, restaurante.getEmail());
            pstmt.setString(3, restaurante.getNm_fantasia());
            pstmt.setString(4, restaurante.getDs_especialidade());
            pstmt.setTime(5, restaurante.getHr_abertura());
            pstmt.setTime(6, restaurante.getHr_fechamento());
            pstmt.setInt(7, restaurante.getNr_funcionarios());
            pstmt.setBoolean(8, restaurante.isRetirada_presencial());
            pstmt.setBoolean(9, restaurante.isEntrega_propria());

            pstmt.executeUpdate();

            if (conn.getAutoCommit())
            	restaurante.setId(getLastIndex());
            else
            	restaurante.setId(getLastIndex(conn));
            return restaurante;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if (conn.getAutoCommit())
            		conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
	}

	@Override
	public Boolean delete(long id) throws Exception {
		return delete(id, conInst.getConnection());
	}

	@Override
	public Boolean delete(long id, Connection conn) throws Exception {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "delete from restaurante where id = ?");
            pstmt.setLong(1, id);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if (conn.getAutoCommit())
            		conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
	}

	@Override
	public Boolean deleteByFk(long id) throws Exception {
		return deleteByFk(id, conInst.getConnection());
	}

	@Override
	public Boolean deleteByFk(long id, Connection conn) throws Exception {
        return false;
	}
	
	@Override
	public Boolean delete() throws Exception {
		return delete(conInst.getConnection());
	}

	@Override
	public Boolean delete(Connection conn) throws Exception {
        try {
            PreparedStatement pstmt = conn.prepareStatement("delete from restaurante");

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if (conn.getAutoCommit())
            		conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
	}
	
	public Boolean update(Restaurante obj) throws Exception {
		return update((ModelDefault) obj);
	}

	public Boolean update(Restaurante obj, Connection conn) throws Exception {
		return update((ModelDefault) obj, conn);
	}
	
	@Override
	public Boolean update(ModelDefault obj) throws Exception {
		return update(obj, conInst.getConnection());
	}
	
	@Override
	public Boolean update(ModelDefault obj, Connection conn) throws Exception {
		Restaurante restaurante = (Restaurante) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement("update restaurante set cnpj = ?, email = ?,"
            		+ "nm_fantasia = ?, ds_especialidade = ?, hr_abertura = ?, hr_fechamento = ?,"
            		+ "nr_funcionarios = ?, retirada_presencial = ?, entrega_propria = ? where id = ?");
            pstmt.setString(1, restaurante.getCnpj());
            pstmt.setString(2, restaurante.getEmail());
            pstmt.setString(3, restaurante.getNm_fantasia());
            pstmt.setString(4, restaurante.getDs_especialidade());
            pstmt.setTime(5, restaurante.getHr_abertura());
            pstmt.setTime(6, restaurante.getHr_fechamento());
            pstmt.setInt(7, restaurante.getNr_funcionarios());
            pstmt.setBoolean(8, restaurante.isRetirada_presencial());
            pstmt.setBoolean(9, restaurante.isEntrega_propria());
            pstmt.setLong(10, restaurante.getId());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if (conn.getAutoCommit())
            		conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
	}
	
	public Boolean updateByFk(Restaurante obj) throws Exception {
		return updateByFk((ModelDefault) obj);
	}

	public Boolean updateByFk(Restaurante obj, Connection conn) throws Exception {
		return updateByFk((ModelDefault) obj, conn);
	}
	
	@Override
	public Boolean updateByFk(ModelDefault obj) throws Exception {
		return updateByFk(obj, conInst.getConnection());
	}
	
	@Override
	public Boolean updateByFk(ModelDefault obj, Connection conn) throws Exception {
		return false;
	}

	@Override
	public int getLastIndex() throws Exception {
		return getLastIndex(conInst.getConnection());
	}
	
	@Override
	public int getLastIndex(Connection conn) throws Exception {
		int Index = 0;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "select id from restaurante order by id desc fetch first 1 row only");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
                Index = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
            	if (conn.getAutoCommit())
            		conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }

        return Index;
	}
}
