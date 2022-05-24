package com.aulas.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aulas.mvc.entidades.Contato;
import com.aulas.mvc.interfaces.IDAO;
import com.aulas.mvc.utilidades.Conexao;

public class DaoContato implements IDAO<Contato>{

	@Override
	public boolean salvar(Contato obj) {
		String sql = "insert into contatos(nome, email)values(?,?)";
		Connection con = Conexao.conectar();
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, obj.getNome());
			stm.setString(2, obj.getEmail());
			stm.execute();   
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		finally {
			Conexao.closeConexao();
		}
		return true;
	}

	@Override
	public Contato getOne(int id) {
		Contato ct = null;
		
		String sql = "select * from contatos where id = ?";
		Connection con = Conexao.conectar();
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id); 
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				ct = new Contato();
				ct.setId(rs.getInt("id"));
				ct.setNome(rs.getString("nome"));
				ct.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
		   e.printStackTrace();
		}	
		finally {
			Conexao.closeConexao();
		}
		return ct;
	}

	@Override
	public List<Contato> getTodos() {
		List<Contato> lista = new  ArrayList<Contato>();
		Connection con = Conexao.conectar();
		try {
			PreparedStatement stm = con.prepareStatement("select * from contatos");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Contato ct = new Contato();
				ct.setId(rs.getInt("id"));
				ct.setNome(rs.getString("nome"));
				ct.setEmail(rs.getString("email"));
				lista.add(ct);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexao.closeConexao();
		}
		return lista;
	}

	@Override
	public boolean alterar(Contato obj) {
		String sql = "update contatos set nome = ?, email = ? where id = ?";
		Connection con = Conexao.conectar();
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setString(1, obj.getNome());
			stm.setString(2, obj.getEmail());
			stm.setInt(3, obj.getId());
			
			stm.execute();    
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		finally {
			Conexao.closeConexao();
		}
		return true;
	}

	@Override
	public boolean deletar(int id) {
		Connection con = Conexao.conectar();
		try {
			PreparedStatement stm = con.prepareStatement("delete from contatos where id = ?");
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			Conexao.closeConexao();
		}
		return true;
	}
	
}