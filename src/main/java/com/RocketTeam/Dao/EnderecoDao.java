package com.RocketTeam.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RocketTeam.Configs.ConnManager;
import com.RocketTeam.Models.Endereco;
import com.RocketTeam.Models.ModelDefault;

public class EnderecoDao implements IDao {
	ConnManager conInst = null;
	
	public EnderecoDao() {
		conInst = ConnManager.getInstance();
	}
	
	@Override
	public ModelDefault get(long id) throws Exception {
		Connection conn = conInst.getConnection();
		 
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from endereco where cd_endereco = ?");
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return new Endereco(id, res.getLong("restaurante_id"), res.getString("ds_logradouro"),
                		res.getInt("nr_endereco"), res.getString("nm_bairro"), res.getString("nm_cidade"),
                		res.getString("nm_estado"));
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
		List<Endereco> enderecos = new ArrayList<Endereco>();
		Connection conn = conInst.getConnection();
		 
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from endereco where restaurante_id = ?");
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                enderecos.add(new Endereco(res.getLong("cd_endereco"), id, res.getString("ds_logradouro"),
                		res.getInt("nr_endereco"), res.getString("nm_bairro"), res.getString("nm_cidade"),
                		res.getString("nm_estado")));
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
		
		Endereco[] enderecosArray = new Endereco[enderecos.size()];
		enderecos.toArray(enderecosArray);
		return enderecosArray;
	}

	@Override
	public ModelDefault[] getAll() throws Exception {
		Connection conn = conInst.getConnection();
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from endereco");
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
				enderecos.add(new Endereco(res.getLong("cd_endereco"), res.getLong("restaurante_id"), res.getString("ds_logradouro"),
                		res.getInt("nr_endereco"), res.getString("nm_bairro"), res.getString("nm_cidade"),
                		res.getString("nm_estado")));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
            throw ex;
		}
		
		Endereco[] enderecosArray = new Endereco[enderecos.size()];
		enderecos.toArray(enderecosArray);
		return enderecosArray;
	}

	public ModelDefault add(Endereco endereco) throws Exception {
        return add((ModelDefault) endereco, conInst.getConnection());
    }

	public ModelDefault add(Endereco endereco, Connection conn) throws Exception {
        return add((ModelDefault) endereco, conn);
    }
	
	@Override
	public ModelDefault add(ModelDefault obj) throws Exception {
		return add(obj, conInst.getConnection());
	}
	
	@Override
	public ModelDefault add(ModelDefault obj, Connection conn) throws Exception {
		Endereco endereco = (Endereco) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into endereco (cd_endereco, restaurante_id, ds_logradouro, nr_endereco, nm_bairro, nm_cidade, nm_estado)"
                    + " values(seq_endereco.nextval, ?, ?, ?, ?, ?, ?)");
            pstmt.setLong(1, endereco.getRest_id());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setLong(3, endereco.getNr());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());

            pstmt.executeUpdate();

            if (conn.getAutoCommit())
            	endereco.setId(getLastIndex());
            else
            	endereco.setId(getLastIndex(conn));
            return endereco;
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
                    "delete from endereco where cd_endereco = ?");
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
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "delete from endereco where restaurante_id = ?");
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
            PreparedStatement pstmt = conn.prepareStatement("delete from endereco");

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

	public Boolean update(Endereco obj) throws Exception {
		return update((ModelDefault) obj, conInst.getConnection());
	}

	public Boolean update(Endereco obj, Connection conn) throws Exception {
		return update((ModelDefault) obj, conn);
	}

	@Override
	public Boolean update(ModelDefault obj) throws Exception {
		return update(obj, conInst.getConnection());
	}
	
	@Override
	public Boolean update(ModelDefault obj, Connection conn) throws Exception {
		Endereco endereco = (Endereco) obj;

        try {
            PreparedStatement pstmt = conn.prepareStatement("update endereco set  restaurante_id = ?,"
            		+ " ds_logradouro = ?,  nr_endereco = ?,  nm_bairro = ?,  nm_cidade = ?,  nm_estado = ? where cd_endereco = ?");
            pstmt.setLong(1, endereco.getRest_id());
            pstmt.setString(2, endereco.getLogradouro());
            pstmt.setLong(3, endereco.getNr());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());
            pstmt.setLong(7, endereco.getId());
            
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
                    "select cd_endereco from endereco order by cd_endereco desc fetch first 1 row only");
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
