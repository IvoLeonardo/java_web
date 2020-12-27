package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ProdutoDao;
import br.com.alura.gerenciador.modelo.Produto;

public class ListaProdutos implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProdutos = dao.geLista();

		request.setAttribute("produtos", listaProdutos);

		return "forward:listaProdutos.jsp";
	}

}
