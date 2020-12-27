package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.EmpresaDao;

public class RemoveEmpresa implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Removendo empresa.");

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		System.out.println("Empresa com id: " + id + " excluída.");

		EmpresaDao dao = new EmpresaDao();
		dao.exclui(id);

		return "redirect:entrada?acao=ListaEmpresas";
	}
}
