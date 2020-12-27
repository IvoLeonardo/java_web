package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.UsuarioDao;
import br.com.alura.gerenciador.modelo.Usuario;

public class MostraUsuario implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		UsuarioDao dao = new UsuarioDao();
		Usuario usuario = null;

		try {
			usuario = dao.buscaUsuarioPelaId(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		request.setAttribute("usuario", usuario);
		return "forward:formAlteraUsuario.jsp";
	}

}
