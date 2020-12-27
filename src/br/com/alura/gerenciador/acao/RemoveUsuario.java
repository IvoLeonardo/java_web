package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.UsuarioDao;

public class RemoveUsuario implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		UsuarioDao dao = new UsuarioDao();
		dao.exclui(Integer.parseInt(id));

		return "redirect:entrada?acao=ListaUsuarios";
	}

}
