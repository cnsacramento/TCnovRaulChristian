package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Intervencion;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoIntervencion;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.IntervencionRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.TipoIntervencionRepository;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);

		if (request.getParameter("id") != null) {
			Intervencion intervencion = intervencionRepository.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("intervencion", intervencion);
		}
		
		
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
		
		if (request.getParameter("idtipo") != null) {
			TipoIntervencion tipoIntervencion = tipoIntervencionRepository.findById(Integer.parseInt(request.getParameter("idtipo")));
			request.setAttribute("tipoIntervencion", tipoIntervencion);
		}
		
		List<TipoIntervencion>tipointervencionList = tipoIntervencionRepository.findAll();
		cargarTipoIntervencion(request);
		request.setAttribute("tipointervencionList", tipointervencionList);
		cargarTipoIntervencion(request);
		List<Intervencion> intervencionesList = intervencionRepository.findAll();
		request.setAttribute("intervencionesList", intervencionesList);
		request.getRequestDispatcher("intervencion.jsp").forward(request, response);
	}
	
	private void cargarTipoIntervencion(HttpServletRequest request) {
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
		List<TipoIntervencion> tipoIntervencionList = tipoIntervencionRepository.findAll();
		request.setAttribute("tipoIntervencionList", tipoIntervencionList);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);


		if (request.getParameter("borrar") != null) {

			borrarIntervencion(request);
			List<Intervencion> intervencionesList = intervencionRepository.findAll();
			request.setAttribute("intervencionesList", intervencionesList);
			cargarTipoIntervencion(request);
			request.getRequestDispatcher("intervencion.jsp").forward(request, response);
		}

		if (request.getParameter("editar") != null) {
			editarIntervencion(request);
			List<Intervencion> intervencionesList = intervencionRepository.findAll();
			request.setAttribute("intervencionesList", intervencionesList);
			cargarTipoIntervencion(request);
			request.getRequestDispatcher("intervencion.jsp").forward(request, response);
		}

		if (request.getParameter("mostrar") != null) {
			
			Intervencion intervencion = mostrarIntervencion(request);
			if(intervencion != null) {
				List<Intervencion> intervencionesList = Arrays.asList(intervencion);
				request.setAttribute("intervencionesList", intervencionesList);
			}
			
			cargarTipoIntervencion(request);
			request.getRequestDispatcher("intervencion.jsp").forward(request, response);
		}

		if (request.getParameter("mostrartodas") != null) {
			List<Intervencion> intervencionesList = intervencionRepository.findAll();
			cargarTipoIntervencion(request);
			request.setAttribute("intervencionesList", intervencionesList);
			request.getRequestDispatcher("intervencion.jsp").forward(request, response);
		}
		
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
		List<TipoIntervencion> tipointervencionList = null;
		TipoIntervencion tipoIntervencion = null;
		
		switch(request.getParameter("btntipo")) {
			case "Crear":
				tipoIntervencion = crearTipoIntervencion(request);
				tipointervencionList = Arrays.asList(tipoIntervencion);
				request.setAttribute("tipointervencionList", tipointervencionList);
				cargarTipoIntervencion(request);
				request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				break;
			case "Editar":
				editarTipoIntervencion(request);
				tipointervencionList = tipoIntervencionRepository.findAll();
				request.setAttribute("tipointervencionList", tipointervencionList);
				cargarTipoIntervencion(request);
				request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				break;
			case "Eliminar":
				borrarTipoIntervencion(request);
				tipointervencionList = tipoIntervencionRepository.findAll();
				request.setAttribute("tipointervencionList", tipointervencionList);
				cargarTipoIntervencion(request);
				request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				break;		
			case "Mostrar": 
				tipoIntervencion = mostrarTipoIntervencion(request);
				if(tipoIntervencion != null) {
					tipointervencionList = Arrays.asList(tipoIntervencion);
					request.setAttribute("tipointervencionList", tipointervencionList);
				}
				cargarTipoIntervencion(request);
				request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				break;
			case "Mostrar todos": 
				tipointervencionList = tipoIntervencionRepository.findAll();
				cargarTipoIntervencion(request);
				request.setAttribute("tipointervencionList", tipointervencionList);
				request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				break;
		}
		
	}

	private Intervencion agregarIntervencion(HttpServletRequest request) { // TODO redirigir para los horarios

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);
		cargarTipoIntervencion(request);
		
		return null;
		
	}

	private boolean borrarIntervencion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);

		String id = request.getParameter("id");
		cargarTipoIntervencion(request);

		return intervencionRepository.delete(Integer.parseInt(id));
	}

	private boolean editarIntervencion(HttpServletRequest request) { // TODO redirigir para los horarios

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);
		cargarTipoIntervencion(request);

		return intervencionRepository.update(null);
	}
	
	private Intervencion mostrarIntervencion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);

		String id = request.getParameter("id");
		cargarTipoIntervencion(request);

		return intervencionRepository.findById(Integer.parseInt(id));
	}
	
	private TipoIntervencion crearTipoIntervencion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
		
		TipoIntervencion tipoIntervencion = new TipoIntervencion();
		tipoIntervencion.setTipo(request.getParameter("tipo"));

		return tipoIntervencionRepository.save(tipoIntervencion);
	}
	
	private boolean editarTipoIntervencion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
		
		TipoIntervencion tipoIntervencion = new TipoIntervencion();
		tipoIntervencion.setId(Integer.parseInt(request.getParameter("id")));
		tipoIntervencion.setTipo(request.getParameter("tipo"));

		return tipoIntervencionRepository.update(tipoIntervencion);
	}
	
	private boolean borrarTipoIntervencion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);
		
		String id = request.getParameter("id");

		return tipoIntervencionRepository.delete(Integer.parseInt(id));
	}

	private TipoIntervencion mostrarTipoIntervencion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(entityManagerFactory);

		String id = request.getParameter("id");
		cargarTipoIntervencion(request);

		return tipoIntervencionRepository.findById(Integer.parseInt(id));
	}

}
