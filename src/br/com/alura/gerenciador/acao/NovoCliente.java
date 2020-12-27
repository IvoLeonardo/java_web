package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ClienteDao;
import br.com.alura.gerenciador.modelo.Cliente;

public class NovoCliente implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");

		Cliente cliente = new Cliente();
		cliente.setNome(nome);

		ClienteDao dao = new ClienteDao();
		dao.adiciona(cliente);

		System.out.println("Cliente cadastrado.");

		return "redirect:entrada?acao=ListaClientes";
	}

}
