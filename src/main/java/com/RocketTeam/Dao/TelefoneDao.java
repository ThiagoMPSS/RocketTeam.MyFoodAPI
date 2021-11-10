package com.RocketTeam.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RocketTeam.Configs.ConnManager;
import com.RocketTeam.Models.ModelDefault;
import com.RocketTeam.Models.Telefone;

public class TelefoneDao implements IDao {
	ConnManager conInst = null;
	
	public TelefoneDao() {
		conInst = ConnManager.getInstance();
	}
	
	@Override
	public ModelDefault get(long id) throws Exception {
		Connection conn = conInst.getConnection();
		 
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from telefone where cd_telefone = ?");
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return new Telefone(id, res.getLong("restaurante_id"),  res.getInt("nr_ddd"), res.getInt("nr_telefone"));
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
		List<Telefone> telefones = new ArrayList<Telefone>();
		Connection conn = conInst.getConnection();
		 
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from telefone where restaurante_id = ?");
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                telefones.add(new Telefone(res.getLong("cd_telefone"), id,  res.getInt("nr_ddd"), res.getInt("nr_telefone")));
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
		
		Telefone[] telefoneArray = new Telefone[telefones.size()];
		telefones.toArray(telefoneArray);
		return telefoneArray;
	}

	@Override
	public ModelDefault[] getAll() throws Exception {
		Connection conn = conInst.getConnection();
		List<Telefone> telefones = new ArrayList<Telefone>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from telefone");
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				telefones.add(new Telefone(res.getLong("cd_telefone"), res.getLong("restaurante_id"),  res.getInt("nr_ddd"), res.getInt("nr_telefone")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
            throw ex;
		}
		
		Telefone[] telefoneArray = new Telefone[telefones.size()];
		telefones.toArray(telefoneArray);
		return telefoneArray;
	}

	public ModelDefault add(Telefone telefone) throws Exception {
        return add((ModelDefault) telefone);
    }

	public ModelDefault add(Telefone telefone, Connection conn) throws Exception {
//		System.out.println("Teste: " + telefone.getRest_id());
        return add((ModelDefault) telefone, conn);
    }
	
	@Override
	public ModelDefault add(ModelDefault obj) throws Exception {
		return add(obj, conInst.getConnection());
	}
	
	@Override
	public ModelDefault add(ModelDefault obj, Connection conn) throws Exception {
		Telefone telefone = (Telefone) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into telefone (cd_telefone, restaurante_id, nr_ddd, nr_telefone)"
                    + " values(seq_telefone.nextval, ?, ?, ?)");
            pstmt.setLong(1, telefone.getRest_id());
            pstmt.setInt(2, telefone.getDDD());
            pstmt.setInt(3, telefone.getNumero());

            pstmt.executeUpdate();

            if (conn.getAutoCommit())
            	telefone.setId(getLastIndex());
            else
            	telefone.setId(getLastIndex(conn));
            return telefone;
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
                    "delete from telefone where cd_telefone = ?");
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
	}@Override
	public Boolean deleteByFk(long id) throws Exception {
		return deleteByFk(id, conInst.getConnection());
	}

	@Override
	public Boolean deleteByFk(long id, Connection conn) throws Exception {
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "delete from telefone where restaurante_id = ?");
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
	public Boolean delete() throws Exception {
		return delete(conInst.getConnection());
	}

	@Override
	public Boolean delete(Connection conn) throws Exception {
        try {
            PreparedStatement pstmt = conn.prepareStatement("delete from telefone");

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
	
	public Boolean update(Telefone obj, Connection conn) throws Exception {
		return update((ModelDefault) obj, conn);
	}
	
	public Boolean update(Telefone obj) throws Exception {
		return update((ModelDefault) obj);
	}

	@Override
	public Boolean update(ModelDefault obj) throws Exception {
		return update(obj, conInst.getConnection());
	}

	@Override
	public Boolean update(ModelDefault obj, Connection conn) throws Exception {
		Telefone telefone = (Telefone) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement("update telefone set restaurante_id = ?,"
            		+ "nr_ddd = ?, nr_telefone = ? where cd_telefone = ?");
            pstmt.setLong(1, telefone.getRest_id());
            pstmt.setInt(2, telefone.getDDD());
            pstmt.setInt(3, telefone.getNumero());
            pstmt.setLong(4, telefone.getId());
            
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
	
	public Boolean updateByFk(Telefone obj, Connection conn) throws Exception {
		return updateByFk((ModelDefault) obj, conn);
	}
	
	public Boolean updateByFk(Telefone obj) throws Exception {
		return updateByFk((ModelDefault) obj);
	}

	@Override
	public Boolean updateByFk(ModelDefault obj) throws Exception {
		return updateByFk(obj, conInst.getConnection());
	}

	@Override
	public Boolean updateByFk(ModelDefault obj, Connection conn) throws Exception {
		Telefone telefone = (Telefone) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement("update telefone set"
            		+ " nr_ddd = ?, nr_telefone = ? where restaurante_id = ?");
            pstmt.setInt(1, telefone.getDDD());
            pstmt.setInt(2, telefone.getNumero());
            pstmt.setLong(3, telefone.getRest_id());
            
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
	public int getLastIndex() throws Exception {
		return getLastIndex(conInst.getConnection());
	}

	@Override
	public int getLastIndex(Connection conn) throws Exception {
		int Index = 0;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "select cd_telefone from telefone order by cd_telefone desc fetch first 1 row only");
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
