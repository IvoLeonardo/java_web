package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ProdutoDao;
import br.com.alura.gerenciador.modelo.Produto;

public class NovoProduto implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");

		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);

		ProdutoDao dao = new ProdutoDao();
		dao.adiciona(produto);

		return "redirect:entrada?acao=ListaProdutos";
	}

}
