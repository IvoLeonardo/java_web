package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ProdutoDao;
import br.com.alura.gerenciador.modelo.Produto;

public class MostraProduto implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		ProdutoDao dao = new ProdutoDao();
		Produto produto = null;

		try {
			produto = dao.buscaProdutoPelaId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		request.setAttribute("produto", produto);
		return "forward:formAlteraProduto.jsp";
	}

}
