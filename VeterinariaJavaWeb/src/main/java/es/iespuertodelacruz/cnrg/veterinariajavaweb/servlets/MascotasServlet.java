package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.ClienteRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.EspecieMascotaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.MascotaRepository;

/**
 * Servlet implementation class MascotasServlet
 */
@WebServlet({ "/MascotasServlet", "/mascotasServlet" })
public class MascotasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MascotasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		MascotaRepository mascotaRepository = new MascotaRepository(entityManagerFactory);

		List<Mascota> mascotas = new ArrayList<>();

		mascotas = mascotaRepository.findAll();
		request.setAttribute("mascotas", mascotas);
		request.getRequestDispatcher("mascotas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		MascotaRepository mascotaRepository = new MascotaRepository(entityManagerFactory);
		EspecieMascotaRepository especieRepository = new EspecieMascotaRepository(entityManagerFactory);
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);

		String proceso = request.getParameter("boton");
		List<Mascota> mascotas = new ArrayList<>();

		if (proceso != null) {

		} else {
			mascotas = mascotaRepository.findAll();
			request.setAttribute("mascotas", mascotas);
			request.getRequestDispatcher("mascotas.jsp").forward(request, response);
		}
	}

}
