package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.UsuarioDao;
import br.com.alura.gerenciador.modelo.Usuario;

public class AlteraUsuario implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		String nome = request.getParameter("nome");
		String login = request.getParameter("login");

		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(id));
		usuario.setNome(nome);
		usuario.setLogin(login);

		UsuarioDao dao = new UsuarioDao();
		dao.altera(usuario);
		return "redirect:entrada?acao=ListaUsuarios";
	}

}
