package br.com.alura.gerenciador.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.gerenciador.jdbc.ConnectionFactory;
import br.com.alura.gerenciador.modelo.Produto;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Produto produto) {

		String sql = "insert into produtos (nome, descricao) values (?, ?)";

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());

			pst.execute();
			pst.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> geLista() {

		try {
			List<Produto> produtos = new ArrayList<>();

			PreparedStatement pst = this.connection.prepareStatement("select * from produtos");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));

				produtos.add(produto);
			}
			rs.close();
			pst.close();

			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Produto produto) {
		String sql = "update produtos set nome=?, descricao=? where id=" + produto.getId();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());

			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void exclui(Integer id) {

		try {
			String sql = "delete from produtos where id='" + id + "'";
			PreparedStatement pst = this.connection.prepareStatement(sql);

			pst.execute();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Produto buscaProdutoPelaId(Integer id) throws SQLException {

		String sql = "select * from produtos where id='" + id + "'";

		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Produto produto = new Produto();
			produto.setId(rs.getInt("id"));
			produto.setNome(rs.getString("nome"));
			produto.setDescricao(rs.getString("descricao"));

			return produto;
		}
		return null;
	}
}
