package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ClienteDao;
import br.com.alura.gerenciador.modelo.Cliente;

public class ListaClientes implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClienteDao dao = new ClienteDao();
		List<Cliente> listaClientes = dao.getLista();

		request.setAttribute("clientes", listaClientes);

		return "forward:listaClientes.jsp";
	}

}
