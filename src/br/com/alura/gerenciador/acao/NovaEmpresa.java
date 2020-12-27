package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.EmpresaDao;
import br.com.alura.gerenciador.modelo.Empresa;

public class NovaEmpresa implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Cadastrando nova empresa");

		String nomeEmpresa = request.getParameter("nome");
		String municipioEmpresa = request.getParameter("municipio");
//		String paramDataEmpresa = request.getParameter("data");

//		Date dataAbertura = null;
//
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			dataAbertura = sdf.parse(paramDataEmpresa);
//		} catch (ParseException e) {
//			throw new ServletException(e);
//		}

		// Banco banco = new Banco();
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpresa);
		empresa.setMunicipio(municipioEmpresa);

//		empresa.setDataAbertura(dataAbertura);

		EmpresaDao dao = new EmpresaDao();
		dao.adiciona(empresa);

		// request.setAttribute("empresa", dao.getLista());

		return "redirect:entrada?acao=ListaEmpresas";
	}
}
