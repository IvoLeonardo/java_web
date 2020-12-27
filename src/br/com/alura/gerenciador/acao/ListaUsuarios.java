package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.UsuarioDao;
import br.com.alura.gerenciador.modelo.Usuario;

public class ListaUsuarios implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UsuarioDao dao = new UsuarioDao();
		List<Usuario> lista = dao.getLista();

		request.setAttribute("usuarios", lista);

		return "forward:listaUsuarios.jsp";
	}

}
