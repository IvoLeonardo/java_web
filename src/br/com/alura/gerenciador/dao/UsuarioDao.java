package br.com.alura.gerenciador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.gerenciador.jdbc.ConnectionFactory;
import br.com.alura.gerenciador.modelo.Usuario;

public class UsuarioDao {

	Connection connection;

	public UsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Usuario usuario) {

		String sql = "insert into usuarios(nome, login, senha) values(?,?,?)";

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getLogin());
			pst.setString(3, usuario.getSenha());

			pst.execute();
			pst.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> getLista() {

		try {
			List<Usuario> usuarios = new ArrayList<>();

			PreparedStatement pst = connection.prepareStatement("select * from usuarios");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));

				usuarios.add(usuario);
			}
			rs.close();
			pst.close();
			return usuarios;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Usuario usuario) {

		String sql = "update usuarios set nome=?, login=? where id=" + usuario.getId();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getLogin());

			pst.executeUpdate();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void exclui(Integer id) {

		try {
			String sql = "delete from usuarios where id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.execute();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public Usuario buscaUsuarioPelaId(Integer id) {
		try {
			String sql = "select * from usuarios where id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				return usuario;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * public Usuario existeUsuario(String login, String senha) {
	 * 
	 * for (Usuario usuario : getLista()) { if (usuario.ehIgual(login, senha)) {
	 * return usuario; } }
	 * 
	 * return null; }
	 */

	public Usuario validarLogin(String login, String senha) throws Exception {

		Usuario usuario = null;

		String sql = "select * from usuarios where login = '" + login + "' and senha = '" + senha + "'";

		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			usuario = new Usuario();
			usuario.setNome(rs.getString("nome"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			return usuario;
		} else {
			return null;
		}
	}
}
