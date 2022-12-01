package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Factura;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.FacturaRepository;


/**
 * Servlet implementation class FacturasServlet
 */
@WebServlet({"/FacturasServlet", "/facturasservlet"})
public class FacturasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacturasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);

		if (request.getParameter("id") != null) {
			Factura factura = facturaRepository.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("factura", factura);
		}
	
		List<Factura> facturasList = facturaRepository.findAll();
		request.setAttribute("facturasList", facturasList);
		request.getRequestDispatcher("factura.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);
		
		List<Factura> facturasList = null;
		Factura factura = null;
		
		switch(request.getParameter("btnFactura")) {
			case "Crear":
				factura = crearFactura(request);
				facturasList = Arrays.asList(factura);
				request.setAttribute("facturasList", facturasList);
				request.getRequestDispatcher("factura.jsp").forward(request, response);
				break;
			case "Editar":
				editarFactura(request);
				facturasList = facturaRepository.findAll();
				request.setAttribute("facturasList", facturasList);
				request.getRequestDispatcher("factura.jsp").forward(request, response);
				break;
			case "Borrar":
				eliminarFactura(request);
				facturasList = facturaRepository.findAll();
				request.setAttribute("facturasList", facturasList);
				request.getRequestDispatcher("factura.jsp").forward(request, response);
				break;		
			case "Mostrar": 
				factura = mostrarFactura(request);
				if(factura != null) {
					facturasList = Arrays.asList(factura);
					request.setAttribute("facturasList", facturasList);
				}
				request.getRequestDispatcher("factura.jsp").forward(request, response);
				break;
			case "Mostrar todas": 
				facturasList = facturaRepository.findAll();
				request.setAttribute("facturasList", facturasList);
				request.getRequestDispatcher("factura.jsp").forward(request, response);
				break;
		}
	}
	
	private Factura crearFactura(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);
		
		try {
			Factura factura = new Factura();
			
			String strFecha = request.getParameter("fecha");
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
			factura.setFecha(new Timestamp(fecha.getTime()));
			factura.setCoste(Double.parseDouble(request.getParameter("coste")));
			factura.setDetalles(request.getParameter("detalles"));
			return facturaRepository.save(factura);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return null;
	}
	
	private boolean editarFactura(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);
		
		try {
			Factura factura = new Factura();
			
			String strFecha = request.getParameter("fecha");
			Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
			factura.setId(Integer.parseInt(request.getParameter("id")));
			factura.setFecha(new Timestamp(fecha.getTime()));
			factura.setCoste(Double.parseDouble(request.getParameter("coste")));
			factura.setDetalles(request.getParameter("detalles"));
			return facturaRepository.update(factura);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return false;
	}
	
	private boolean eliminarFactura(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);
		
		String id = request.getParameter("id");

		return facturaRepository.delete(Integer.parseInt(id));
	}

	private Factura mostrarFactura(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);

		String id = request.getParameter("id");

		return facturaRepository.findById(Integer.parseInt(id));
	}

}
