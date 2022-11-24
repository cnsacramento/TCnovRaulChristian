//package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.EntityManagerFactory;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Reserva;
//import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Reserva;
//import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.ReservaRepository;
//import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.ReservaRepository;
//
///**
// * Servlet implementation class ReservasServlet
// */
//@WebServlet({ "/ReservasServlet", "/reservasservlet" })
//public class ReservasServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ReservasServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
//				.getAttribute("entityManagerFactory");
//		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);
//		
//		List<Reserva> reservasList = null;
//		Reserva reserva = null;
//		
//		switch(request.getParameter("btnFactura")) {
//			case "Crear":
//				reserva = crearReserva(request);
//				reservasList = Arrays.asList(reserva);
//				request.setAttribute("reservasList", reservasList);
//				request.getRequestDispatcher("reserva.jsp").forward(request, response);
//				break;
//			case "Editar":
//				editarFactura(request);
//				reservasList = reservaRepository.findAll();
//				request.setAttribute("reservasList", reservasList);
//				request.getRequestDispatcher("reserva.jsp").forward(request, response);
//				break;
//			case "Borrar":
//				eliminarFactura(request);
//				reservasList = reservaRepository.findAll();
//				request.setAttribute("reservasList", reservasList);
//				request.getRequestDispatcher("reserva.jsp").forward(request, response);
//				break;		
//			case "Mostrar": 
//				reserva = mostrarFactura(request);
//				if(reserva != null) {
//					reservasList = Arrays.asList(reserva);
//					request.setAttribute("reservasList", reservasList);
//				}
//				request.getRequestDispatcher("reserva.jsp").forward(request, response);
//				break;
//			case "Mostrar todas": 
//				reservasList = reservaRepository.findAll();
//				request.setAttribute("facturasList", reservasList);
//				request.getRequestDispatcher("reserva.jsp").forward(request, response);
//				break;
//		}
//	}
//	
//	
//	private Reserva crearReserva(HttpServletRequest request) {
//
//		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
//				.getAttribute("entityManagerFactory");
//		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);
//		
//		try {
//			Reserva reserva = new Reserva();
//			
//			String strFechaInicio  = request.getParameter("fechaInicio");
//			Date fechaInicio = new SimpleDateFormat("yyyy-mm-dd").parse(strFechaInicio);
//			String strFechaFin  = request.getParameter("fechaFin");
//			Date fechaFin = new SimpleDateFormat("yyyy-mm-dd").parse(strFechaFin);
//			
//			reserva.setFechaInicio(new Timestamp(fechaInicio.getTime()));
//			reserva.setFechaFin(new Timestamp(fechaFin.getTime()));
//			
//			
//			/reserva.setIntervencion(Double.parseDouble(request.getParameter("coste")));
//			reserva.setTipoRestriccionDia(request.getParameter("detalles"));
//			return reservaRepository.save(reserva);
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		
//		
//		return null;
//	}
//	
//	private boolean editarFactura(HttpServletRequest request) {
//
//		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
//				.getAttribute("entityManagerFactory");
//		ReservaRepository facturaRepository = new ReservaRepository(entityManagerFactory);
//		
//		try {
//			Reserva factura = new Reserva();
//			
//			String strFecha = request.getParameter("fecha");
//			Date fecha = new SimpleDateFormat("yyyy-mm-dd").parse(strFecha);
//			factura.setId(Integer.parseInt(request.getParameter("id")));
//			factura.setFechaInicio(new Timestamp(fecha.getTime()));
//			factura.setIntervencion(Double.parseDouble(request.getParameter("coste")));
//			factura.setTipoRestriccionDia(request.getParameter("detalles"));
//			return facturaRepository.update(factura);
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		
//		
//		return false;
//	}
//	
//	private boolean eliminarFactura(HttpServletRequest request) {
//
//		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
//				.getAttribute("entityManagerFactory");
//		ReservaRepository facturaRepository = new ReservaRepository(entityManagerFactory);
//		
//		String id = request.getParameter("id");
//
//		return facturaRepository.delete(Integer.parseInt(id));
//	}
//
//	private Reserva mostrarFactura(HttpServletRequest request) {
//
//		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
//				.getAttribute("entityManagerFactory");
//		ReservaRepository facturaRepository = new ReservaRepository(entityManagerFactory);
//
//		String id = request.getParameter("id");
//
//		return facturaRepository.findById(Integer.parseInt(id));
//	}
//
//}
