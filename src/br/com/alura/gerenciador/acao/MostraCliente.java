package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.ClienteDao;
import br.com.alura.gerenciador.modelo.Cliente;

public class MostraCliente implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		ClienteDao dao = new ClienteDao();
		Cliente cliente = null;

		try {

			cliente = dao.buscaClientePelaId(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		request.setAttribute("cliente", cliente);

		return "forward:formAlteraCliente.jsp";
	}

}
