package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.TipoRestriccionDia;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Veterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.FacturaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.IntervencionRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.MascotaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.ReservaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.TipoIntervencionRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.TipoRestriccionDiaRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.VeterinarioRepository;

/**
 * Servlet implementation class ReservasServlet
 */
@WebServlet({ "/ReservasServlet", "/reservasservlet" })
public class ReservasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservasServlet() {
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
		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

		if (request.getParameter("id") != null) {
			Reserva reserva = reservaRepository.findById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("reserva", reserva);
		}

		TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
				entityManagerFactory);

		if (request.getParameter("restriccion") != null) {

			TipoRestriccionDia tipoRestriccionDia = tipoRestriccionDiaRepository
					.findById(request.getParameter("restriccion"));
			request.setAttribute("tipoRestriccionDia", tipoRestriccionDia);
		}

		Timestamp fechaInicioInput = new Timestamp(new Date().getTime());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 15);
		calendar.add(Calendar.MINUTE, 00);
		calendar.add(Calendar.SECOND, 00);

		Timestamp fechaCierreInput = new Timestamp(calendar.getTime().getTime());

		request.setAttribute("fechaInicioInput", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fechaInicioInput));
		request.setAttribute("fechaCierreInput", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fechaCierreInput));

		List<Reserva> reservasList = reservaRepository.findAll();
		request.setAttribute("reservasList", reservasList);

		List<TipoRestriccionDia> tipoRestriccionDiaList = tipoRestriccionDiaRepository.findAll();
		request.setAttribute("tipoRestriccionDiaList", tipoRestriccionDiaList);

		request.getRequestDispatcher("reserva.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

		if (request.getParameter("btnReserva") != null) {
			List<Reserva> reservasList = null;
			Reserva reserva = null;

			switch (request.getParameter("btnReserva")) {
			case "Crear":
				reserva = crearReserva(request);
				reservasList = Arrays.asList(reserva);
				request.setAttribute("reservasList", reservasList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Editar":
				editarReserva(request);
				reservasList = reservaRepository.findAll();
				request.setAttribute("reservasList", reservasList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Borrar":
				eliminarReserva(request);
				reservasList = reservaRepository.findAll();
				request.setAttribute("reservasList", reservasList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Mostrar":
				reserva = mostrarReserva(request);
				if (reserva != null) {
					reservasList = Arrays.asList(reserva);
					request.setAttribute("reservasList", reservasList);
				}
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Mostrar todas":
				reservasList = reservaRepository.findAll();
				request.setAttribute("reservasList", reservasList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			}
		}
		
		if (request.getParameter("fechaReserva") != null) {

			switch (request.getParameter("fechaReserva")) {

			case "Crear":

				desbloquear("bloqueoFecha", request);
				request.getRequestDispatcher("fechaReserva.jsp").forward(request, response);
				break;

			case "Escoger fecha":

				try {
					String fechaSeleccionada = request.getParameter("fecha");


					request.getSession().setAttribute("fecha", fechaSeleccionada);

					desbloquear("bloqueoHora", request);

					String strFecha = request.getParameter("fecha");
					Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
					Timestamp fechaApertura = new Timestamp(fecha.getTime() + (8 * 60 * 60 * 1000));
					request.setAttribute("fechaApertura", fechaApertura);
					Timestamp fechaCierre = new Timestamp(fecha.getTime() + (15 * 60 * 60 * 1000));

					List<Reserva> reservas = reservaRepository.encontrarCitasDeUnDia(fechaApertura, fechaCierre);

					TreeMap<Integer, Boolean> sesiones = new TreeMap<>();

					sesiones.put(8, false);
					sesiones.put(9, false);
					sesiones.put(10, false);
					sesiones.put(11, false);
					sesiones.put(12, false);
					sesiones.put(13, false);
					sesiones.put(14, false);

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

				request.getRequestDispatcher("fechaReserva.jsp").forward(request, response);
				break;

			case "Escoger hora":
				String hora = request.getParameter("hora");
				int intHora = Integer.parseInt(hora);

				@SuppressWarnings("unchecked")
				TreeMap<Integer, Boolean> sesiones = (TreeMap<Integer, Boolean>) request.getSession()
						.getAttribute("sesiones");

				int cantidadSesiones = 1; // Dado que existe minimo 1 sesion
				List<Integer> sesionesDisponibles = new ArrayList<>();
				Iterator<Integer> iterator = sesiones.tailMap(intHora).keySet().iterator();
				while (iterator.hasNext()) {
					int key = iterator.next();
	
					if (sesiones.get(key))
						break;
					sesionesDisponibles.add(cantidadSesiones++);
				}

				request.setAttribute("sesionesDisponibles", sesionesDisponibles);
				request.getSession().setAttribute("hora", hora);
				desbloquear("bloqueoSesiones", request);
				request.getRequestDispatcher("fechaReserva.jsp").forward(request, response);
				break;

			case "Escoger sesiones":
				String numeroSesiones = request.getParameter("numeroSesiones");
				request.getSession().setAttribute("numeroSesiones", numeroSesiones);
				desbloquear("bloqueoDatos", request);
				
				
				
				reservaRepository.findById(null);
				
				List<Reserva> reservasList = reservaRepository.findAll();
				request.setAttribute("reservasList", reservasList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			default:
				break;
			}
		}

		if (request.getParameter("btnRestriccion") != null) {

			TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
					entityManagerFactory);
			List<TipoRestriccionDia> tipoRestriccionDiaList = null;
			TipoRestriccionDia tipoRestriccionDia = null;

			switch (request.getParameter("btnRestriccion")) {
			case "Crear":
				tipoRestriccionDia = crearTipoRestriccion(request);
				tipoRestriccionDiaList = Arrays.asList(tipoRestriccionDia);
				request.setAttribute("tipoRestriccionDiaList", tipoRestriccionDiaList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Editar":
				editarRestriccion(request);
				tipoRestriccionDiaList = tipoRestriccionDiaRepository.findAll();
				request.setAttribute("tipoRestriccionDiaList", tipoRestriccionDiaList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Borrar":
				eliminarRestriccion(request);
				tipoRestriccionDiaList = tipoRestriccionDiaRepository.findAll();
				request.setAttribute("tipoRestriccionDiaList", tipoRestriccionDiaList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Mostrar":
				tipoRestriccionDia = mostrarRestriccion(request);
				if (tipoRestriccionDia != null) {
					tipoRestriccionDiaList = Arrays.asList(tipoRestriccionDia);
					request.setAttribute("tipoRestriccionDiaList", tipoRestriccionDiaList);
				}
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			case "Mostrar todas":
				tipoRestriccionDiaList = tipoRestriccionDiaRepository.findAll();
				request.setAttribute("tipoRestriccionDiaList", tipoRestriccionDiaList);
				request.getRequestDispatcher("reserva.jsp").forward(request, response);
				break;
			}
		}

	}

	private Reserva crearReserva(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

		try {
			Reserva reserva = new Reserva();

			String strFechaInicio = request.getParameter("fechaInicio");
			Date fechaInicio = new SimpleDateFormat("yyyy-mm-dd").parse(strFechaInicio);
			String strFechaFin = request.getParameter("fechaFin");
			Date fechaFin = new SimpleDateFormat("yyyy-mm-dd").parse(strFechaFin);

			reserva.setFechaInicio(new Timestamp(fechaInicio.getTime()));
			reserva.setFechaFin(new Timestamp(fechaFin.getTime()));

			IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);
			Intervencion intervencion = intervencionRepository
					.findById(Integer.parseInt(request.getParameter("idIntervencion")));

			reserva.setIntervencion(intervencion);

			TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
					entityManagerFactory);
			TipoRestriccionDia tipoRestriccionDia = tipoRestriccionDiaRepository.findById("tipoRestriccion");
			reserva.setTipoRestriccionDia(tipoRestriccionDia);

			return reservaRepository.save(reserva);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private boolean editarReserva(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

		try {
			Reserva reserva = new Reserva();

			reserva.setId(Integer.parseInt("id"));
			String strFechaInicio = request.getParameter("fechaInicio");
			Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaInicio);
			String strFechaFin = request.getParameter("fechaFin");
			Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaFin);

			reserva.setFechaInicio(new Timestamp(fechaInicio.getTime()));
			reserva.setFechaFin(new Timestamp(fechaFin.getTime()));

			IntervencionRepository intervencionRepository = new IntervencionRepository(entityManagerFactory);
			Intervencion intervencion = intervencionRepository
					.findById(Integer.parseInt(request.getParameter("idIntervencion")));

			reserva.setIntervencion(intervencion);

			TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
					entityManagerFactory);
			TipoRestriccionDia tipoRestriccionDia = tipoRestriccionDiaRepository.findById("tipoRestriccion");
			reserva.setTipoRestriccionDia(tipoRestriccionDia);

			return reservaRepository.update(reserva);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	private boolean eliminarReserva(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

		String id = request.getParameter("id");

		return reservaRepository.delete(Integer.parseInt(id));
	}

	private Reserva mostrarReserva(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

		String id = request.getParameter("id");

		return reservaRepository.findById(Integer.parseInt(id));
	}

	private TipoRestriccionDia crearTipoRestriccion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
				entityManagerFactory);

		try {
			TipoRestriccionDia tipoRestriccionDia = new TipoRestriccionDia();

			tipoRestriccionDia.setTipo(request.getParameter("tipo"));

			String strHoraApertura = request.getParameter("horaApertura");
			Date dateHoraApertura = new SimpleDateFormat("hh:mm:ss").parse(strHoraApertura);
			Time horaApertura = new Time(dateHoraApertura.getTime());

			tipoRestriccionDia.setHoraApertura(horaApertura);

			String strHoraCierre = request.getParameter("horaCierre");
			Date dateHoraCierre = new SimpleDateFormat("hh:mm:ss").parse(strHoraCierre);
			Time horaCierre = new Time(dateHoraCierre.getTime());

			tipoRestriccionDia.setHoraCierre(horaCierre);

			tipoRestriccionDia.setIntervaloTiempo(Integer.parseInt(request.getParameter("intervalo")));
			return tipoRestriccionDiaRepository.save(tipoRestriccionDia);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private boolean editarRestriccion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
				entityManagerFactory);

		try {
			TipoRestriccionDia tipoRestriccionDia = new TipoRestriccionDia();
			tipoRestriccionDia.setTipo(request.getParameter("tipo"));

			String strHoraApertura = request.getParameter("horaApertura");

			Date dateHoraApertura = new SimpleDateFormat("hh:mm:ss").parse(strHoraApertura);
			Time horaApertura = new Time(dateHoraApertura.getTime());

			tipoRestriccionDia.setHoraApertura(horaApertura);

			String strHoraCierre = request.getParameter("horaCierre");
			Date dateHoraCierre = new SimpleDateFormat("hh:mm:ss").parse(strHoraCierre);
			Time horaCierre = new Time(dateHoraCierre.getTime());

			tipoRestriccionDia.setHoraCierre(horaCierre);

			tipoRestriccionDia.setIntervaloTiempo(Integer.parseInt(request.getParameter("intervalo")));
			return tipoRestriccionDiaRepository.update(tipoRestriccionDia);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;

	}

	private boolean eliminarRestriccion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
				entityManagerFactory);

		String tipo = request.getParameter("tipo");

		return tipoRestriccionDiaRepository.delete(tipo);
	}

	private TipoRestriccionDia mostrarRestriccion(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext()
				.getAttribute("entityManagerFactory");
		TipoRestriccionDiaRepository tipoRestriccionDiaRepository = new TipoRestriccionDiaRepository(
				entityManagerFactory);

		String tipo = request.getParameter("tipo");

		return tipoRestriccionDiaRepository.findById(tipo);
	}

	public void desbloquear(String bloqueo, HttpServletRequest request) {

		switch (bloqueo) {
		case "bloqueoFecha":
			request.setAttribute("bloqueoFecha", false);
			request.setAttribute("bloqueoHora", true);
			request.setAttribute("bloqueoSesiones", true);
			break;
		case "bloqueoHora":
			request.setAttribute("bloqueoFecha", true);
			request.setAttribute("bloqueoHora", false);
			request.setAttribute("bloqueoSesiones", true);
			break;
		case "bloqueoSesiones":
			request.setAttribute("bloqueoFecha", true);
			request.setAttribute("bloqueoHora", true);
			request.setAttribute("bloqueoSesiones", false);
			break;
		}

	}
}
