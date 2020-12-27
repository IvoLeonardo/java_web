package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ProdutoDao;
import br.com.alura.gerenciador.modelo.Produto;

public class AlteraProduto implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");

		Produto produto = new Produto();
		produto.setId(id);
		produto.setNome(nome);
		produto.setDescricao(descricao);

		ProdutoDao dao = new ProdutoDao();
		dao.altera(produto);

		return "redirect:entrada?acao=ListaProdutos";
	}

}
