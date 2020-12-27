package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ClienteDao;
import br.com.alura.gerenciador.modelo.Cliente;

public class AlteraCliente implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		String nome = request.getParameter("nome");

		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setNome(nome);

		ClienteDao dao = new ClienteDao();
		dao.altera(cliente);

		return "redirect:entrada?acao=ListaClientes";
	}

}
