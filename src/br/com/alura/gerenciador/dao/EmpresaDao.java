package br.com.alura.gerenciador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.gerenciador.jdbc.ConnectionFactory;
import br.com.alura.gerenciador.modelo.Empresa;

public class EmpresaDao {

	private Connection connection;

	public EmpresaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Empresa empresa) {

		String sql = "insert into empresas(nome,municipio) values(?, ?)";

		try {

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, empresa.getNome());
			stmt.setString(2, empresa.getMunicipio());
//			stmt.setDate(2, new Date(empresa.getDataAbertura().getTimeInMillis()));

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Empresa> getLista() {

		try {
			List<Empresa> empresas = new ArrayList<>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from empresas");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setId(rs.getInt("id"));
				empresa.setNome(rs.getString("nome"));
				empresa.setMunicipio(rs.getString("municipio"));

//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("dataAbertura"));
//				empresa.setDataAbertura(data);

				empresas.add(empresa);
			}
			rs.close();
			stmt.close();
			return empresas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// pg 29 do projeto integrador = método atualizar

	public void altera(Empresa empresa) {
		String sql = "update empresas set nome=?, municipio=? where id=" + empresa.getId();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, empresa.getNome());

			stmt.setString(2, empresa.getMunicipio());

//			stmt.setDate(2, new Date(empresa.getDataAbertura().getTimeInMillis()));

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Integer id) {

		try {
			String sql = "delete from empresas where id='" + id + "'";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Empresa buscaEmpresaPelaId(Integer id) throws SQLException {

		String sql = "select * from empresas where id='" + id + "'";

		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Empresa empresa = new Empresa();
			empresa.setId(rs.getInt("id"));
			empresa.setNome(rs.getString("nome"));
			empresa.setMunicipio(rs.getString("municipio"));

			return empresa;
		}
		return null;
	}
}
