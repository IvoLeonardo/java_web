package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.EmpresaDao;
import br.com.alura.gerenciador.modelo.Empresa;

public class MostraEmpresa implements Acao {

	// pg. 46 c�digo da p�gina alterar_livro.jsp do projeto integrador

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Mostrando informa��es da empresa.");

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		EmpresaDao dao = new EmpresaDao();

		Empresa empresa = null;

		try {
			empresa = dao.buscaEmpresaPelaId(id);

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

		request.setAttribute("empresa", empresa);

		return "forward:formAlteraEmpresa.jsp";
	}
}
