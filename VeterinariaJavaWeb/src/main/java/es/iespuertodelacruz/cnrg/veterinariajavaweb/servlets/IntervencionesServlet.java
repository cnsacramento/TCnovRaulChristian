package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Intervencion;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.IntervencionRepository;

/**
 * Servlet implementation class IntervencionesServlet
 */
@WebServlet({ "/IntervencionesServlet", "/intervencionesservlet" })
public class IntervencionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntervencionesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);
				
		if(request.getParameter("id") != null) {
			Intervencion intervencion = intervencionRepository.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("intervencion", intervencion);
			List<Intervencion> intervencionesList = intervencionRepository.findAll();
			request.setAttribute("intervencionesList", intervencionesList);
			request.getRequestDispatcher("intervencion.jsp").forward(request, response);
		}
		
		List<Intervencion> intervencionesList = intervencionRepository.findAll();
		request.setAttribute("intervencionesList", intervencionesList);
		request.getRequestDispatcher("intervencion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
