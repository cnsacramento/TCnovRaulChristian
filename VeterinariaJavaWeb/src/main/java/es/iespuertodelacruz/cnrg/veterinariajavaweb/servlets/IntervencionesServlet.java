package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Factura;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Intervencion;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Mascota;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Reserva;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoIntervencion;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Veterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.FacturaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.IntervencionRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.MascotaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.ReservaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.TipoIntervencionRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.VeterinarioRepository;

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
			TipoIntervencion tipoIntervencion = tipoIntervencionRepository
					.findById(Integer.parseInt(request.getParameter("idtipo")));
			request.setAttribute("tipoIntervencion", tipoIntervencion);
		}

		List<TipoIntervencion> tipointervencionList = tipoIntervencionRepository.findAll();
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

		if (request.getParameter("fechaIntervencion") != null) {
			switch (request.getParameter("fechaIntervencion")) {
			case "Crear":
				request.setAttribute("bloqueoFecha", false);
				request.setAttribute("bloqueoHora", true);
				request.setAttribute("bloqueoDatos", true);
				request.setAttribute("bloqueoFactura", true);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;
			case "Escoger fecha":
				try {
					String fechaSeleccionada = request.getParameter("fecha");
					request.getSession().setAttribute("fecha", fechaSeleccionada);
					request.setAttribute("bloqueoFecha", true);
					request.setAttribute("bloqueoHora", false);
					request.setAttribute("bloqueoDatos", true);
					request.setAttribute("bloqueoFactura", true);
					ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

					String strFecha = request.getParameter("fecha");
					Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
					Timestamp fechaApertura = new Timestamp(fecha.getTime() + (8 * 60 * 60 * 1000));
					request.setAttribute("fechaApertura", fechaApertura);
					Timestamp fechaCierre = new Timestamp(fecha.getTime() + (16 * 60 * 60 * 1000));

					List<Reserva> reservas = reservaRepository.encontrarCitasDeUnDia(fechaApertura, fechaCierre);

					TreeMap<Integer, Boolean> sesiones = new TreeMap<>();

					sesiones.put(8, false);
					sesiones.put(9, false);
					sesiones.put(10, false);
					sesiones.put(11, false);
					sesiones.put(12, false);
					sesiones.put(13, false);
					sesiones.put(14, false);
					sesiones.put(15, false);
					sesiones.put(16, false);

					Calendar calendarInicio = new GregorianCalendar();
					Calendar calendarFin = new GregorianCalendar();
					for (Reserva reserva : reservas) {
						calendarInicio.setTime(reserva.getFechaInicio());
						calendarFin.setTime(reserva.getFechaFin());
						int cantidadSesiones = calendarFin.get(Calendar.HOUR_OF_DAY)
								- calendarInicio.get(Calendar.HOUR_OF_DAY);

						int hora = calendarInicio.get(Calendar.HOUR_OF_DAY);

						for (int i = 0; i < cantidadSesiones; i++) {
							sesiones.put(hora, true);
							hora++;
						}
					}

					request.getSession().setAttribute("sesiones", sesiones);
					List<Integer> horasLibres = new ArrayList<>();

					for (Map.Entry<Integer, Boolean> pair : sesiones.entrySet()) {
						if (!pair.getValue())
							horasLibres.add(pair.getKey());
					}

					request.setAttribute("horasLibres", horasLibres);

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;

			case "Escoger hora":
				String hora = request.getParameter("hora");
				cargarTipoIntervencion(request);
				request.getSession().setAttribute("hora", hora);
				request.setAttribute("bloqueoFecha", true);
				request.setAttribute("bloqueoHora", true);
				request.setAttribute("bloqueoDatos", false);
				request.setAttribute("bloqueoFactura", true);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;

			case "Escoger sesiones":

				String horaSesion = (String)request.getSession().getAttribute("hora");
				
				
				break;
				
			case "Continuar":

				String asunto = request.getParameter("asunto");
				String descripcion = request.getParameter("descripcion");
				String tipoIntervencion = request.getParameter("tipointervencion");
				String idMascota = request.getParameter("idmascota");
				String equipo = request.getParameter("equipo");

				request.getSession().setAttribute("asunto", asunto);
				request.getSession().setAttribute("descripcion", descripcion);
				request.getSession().setAttribute("tipointervencion", tipoIntervencion);
				request.getSession().setAttribute("idmascota", idMascota);
				request.getSession().setAttribute("equipo", equipo);

				request.setAttribute("bloqueoFecha", true);
				request.setAttribute("bloqueoHora", true);
				request.setAttribute("bloqueoDatos", true);
				request.setAttribute("bloqueoFactura", false);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;

			case "Crear factura":

				try {
					Intervencion intervencion = new Intervencion();

					String asuntoFinal = (String) request.getSession().getAttribute("asunto");
					request.getSession().removeAttribute("asunto");
					String descripcionFinal = (String) request.getSession().getAttribute("descripcion");
					request.getSession().removeAttribute("descripcion");

					intervencion.setAsunto(asuntoFinal);
					intervencion.setDescripcion(descripcionFinal);

					TipoIntervencionRepository tipoIntervencionRepository = new TipoIntervencionRepository(
							entityManagerFactory);

					int idTipoIntervencion = Integer
							.parseInt((String) request.getSession().getAttribute("tipointervencion"));
					request.getSession().removeAttribute("tipointervencion");

					TipoIntervencion tipoIntervencionFinal = tipoIntervencionRepository.findById(idTipoIntervencion);
					intervencion.setTipoIntervencion(tipoIntervencionFinal);

					String idMascotaFinal = (String) request.getSession().getAttribute("idmascota");
					request.getSession().removeAttribute("idmascota");

					MascotaRepository mascotaRepository = new MascotaRepository(entityManagerFactory);
					Mascota mascota = mascotaRepository.findById(Integer.parseInt(idMascotaFinal));

					intervencion.setMascota(mascota);

					String equipoFinal = (String) request.getSession().getAttribute("equipo");
					request.getSession().removeAttribute("equipo");

					String[] equipoSplit = equipoFinal.split(",");

					VeterinarioRepository veterinarioRepository = new VeterinarioRepository(entityManagerFactory);
					Veterinario veterinario;
					List<Veterinario> veterinarios = new ArrayList<>();
					for (String strVeterinario : equipoSplit) {
						veterinario = veterinarioRepository.findById(strVeterinario);
						veterinarios.add(veterinario);
					}

					intervencion.setVeterinarios(veterinarios);

					FacturaRepository facturaRepository = new FacturaRepository(entityManagerFactory);
					Factura factura = new Factura();

					String strFechaFactura = request.getParameter("fechafactura");
					Date fechaFactura = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaFactura);
					Double coste = Double.parseDouble((String) request.getParameter("coste"));
					String detallesFinal = (String) request.getParameter("detalles");

					factura.setFecha(new Timestamp(fechaFactura.getTime()));
					factura.setCoste(coste);
					factura.setDetalles(detallesFinal);

					Factura facturaGuardada = facturaRepository.save(factura);

					intervencion.setFactura(facturaGuardada);

					Intervencion intervencionGuardada = intervencionRepository.save(intervencion);

					ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);
					Reserva reserva = new Reserva();
					// TODO Terminar con reserva

					cargarTipoIntervencion(request);
					request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				break;
			default:
				break;
			}

		}

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
			if (intervencion != null) {
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

		if (request.getParameter("btnTipo") != null) {
			switch (request.getParameter("btntipo")) {
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
				if (tipoIntervencion != null) {
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
