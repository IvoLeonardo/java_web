package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.dao.EmpresaDao;
import br.com.alura.gerenciador.modelo.Empresa;

public class AlteraEmpresa implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nomeEmpresa = request.getParameter("nome");
//		String paramDataEmpresa = request.getParameter("data");
		String municipioEmpresa = request.getParameter("municipio");
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		System.out.println("Ação altera empresa: " + id);

//		Date dataAbertura = null;
//
//		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			dataAbertura = sdf.parse(paramDataEmpresa);
//		} catch (ParseException e) {
//			throw new ServletException(e);
//		}

		Empresa empresa = new Empresa();

		empresa.setNome(nomeEmpresa);
//		empresa.setDataAbertura(dataAbertura);
		empresa.setMunicipio(municipioEmpresa);
		empresa.setId(id);

		EmpresaDao dao = new EmpresaDao();
		dao.altera(empresa);

		return "redirect:entrada?acao=ListaEmpresas";
	}
}
