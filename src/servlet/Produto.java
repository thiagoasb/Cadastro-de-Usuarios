package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProdutoBean;
import dao.DaoProduto;

@WebServlet("/produtoServlet")
public class Produto extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DaoProduto daoProduto = new DaoProduto();
	
	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String prod = request.getParameter("prod");
		
		try {
			
			if(acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(prod);
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				ProdutoBean produtoBean = daoProduto.consultar(prod);
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		if(acao != null && acao.equalsIgnoreCase("reset")) {
			try{
			RequestDispatcher view = request
					.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			String id = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("qtde");
			String valor = request.getParameter("valor");
			
			ProdutoBean produtoBean = new ProdutoBean();
			produtoBean.setId(!id.isEmpty()? Long.parseLong(id): null);
			produtoBean.setNome(nome);
			produtoBean.setQuantidade(Long.parseLong(quantidade));
			produtoBean.setValor(Double.parseDouble(valor));
			
			daoProduto.salvar(produtoBean);
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			try {
				request.setAttribute("produtos", daoProduto.listar());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.forward(request, response);
		}

	}

}
