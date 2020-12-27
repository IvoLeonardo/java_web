package br.com.alura.gerenciador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.gerenciador.jdbc.ConnectionFactory;
import br.com.alura.gerenciador.modelo.Cliente;

public class ClienteDao {

	Connection connection;

	public ClienteDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Cliente cliente) {

		String sql = "insert into clientes(nome) values (?)";

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, cliente.getNome());

			pst.execute();
			pst.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> getLista() {

		try {
			List<Cliente> clientes = new ArrayList<>();

			PreparedStatement pst = connection.prepareStatement("select * from clientes");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));

				clientes.add(cliente);
			}
			rs.close();
			pst.close();

			return clientes;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public void altera(Cliente cliente) {

		String sql = "update clientes set nome=? where id=" + cliente.getId();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, cliente.getNome());

			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void exclui(Integer id) {

		try {
			String sql = "delete from clientes where id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.execute();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Cliente buscaClientePelaId(Integer id) {

		try {
			String sql = "select * from clientes where id='" + id + "'";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));

				return cliente;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
