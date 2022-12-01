package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
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

				desbloquear("bloqueoFecha", request);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;

			case "Escoger fecha":

				try {
					String fechaSeleccionada = request.getParameter("fecha");
					
					System.out.println("//////////////////");
					System.out.println(fechaSeleccionada);
					System.out.println("//////////////////");
					
					request.getSession().setAttribute("fecha", fechaSeleccionada);

					desbloquear("bloqueoHora", request);

					ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);

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

				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
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
					System.out.println("///////////////////////////////");
					int key = iterator.next();
					System.out.println("[" + key + "]" + "=>" + sesiones.get(key));
					System.out.println("///////////////////////////////");
					if (sesiones.get(key))
						break;
					sesionesDisponibles.add(cantidadSesiones++);
				}
				
				request.setAttribute("sesionesDisponibles", sesionesDisponibles);
				request.getSession().setAttribute("hora", hora);
				desbloquear("bloqueoSesiones", request);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;
				
			case "Escoger sesiones":
				String numeroSesiones = request.getParameter("numeroSesiones");
				request.getSession().setAttribute("numeroSesiones", numeroSesiones);
				desbloquear("bloqueoDatos", request);
				cargarTipoIntervencion(request);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
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

				desbloquear("bloqueoFactura", request);
				request.getRequestDispatcher("fechaIntervencion.jsp").forward(request, response);
				break;

			case "Crear factura":

				try {
					Intervencion intervencion = new Intervencion();

					String asuntoFinal = (String) request.getSession().getAttribute("asunto");
					String descripcionFinal = (String) request.getSession().getAttribute("descripcion");
					
					TipoIntervencionRepository tipoIntervencionRepository = 
							new TipoIntervencionRepository(entityManagerFactory);
					int idTipoIntervencion = 
							Integer.parseInt((String) request.getSession().getAttribute("tipointervencion"));
					TipoIntervencion tipoIntervencionFinal = 
							tipoIntervencionRepository.findById(idTipoIntervencion);

					String idMascotaFinal = (String) request.getSession().getAttribute("idmascota");
					MascotaRepository mascotaRepository = new MascotaRepository(entityManagerFactory);
					Mascota mascota = mascotaRepository.findById(Integer.parseInt(idMascotaFinal));
					
					String equipoFinal = (String) request.getSession().getAttribute("equipo");
					String[] equipoSplit = equipoFinal.split(",");

					VeterinarioRepository veterinarioRepository = new VeterinarioRepository(entityManagerFactory);
					Veterinario veterinario;
					List<Veterinario> veterinarios = new ArrayList<>();
					for (String strVeterinario : equipoSplit) {
						veterinario = veterinarioRepository.findById(strVeterinario);
						veterinarios.add(veterinario);
					}
					
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
					
					TipoRestriccionDiaRepository tipoReservaRepository = new TipoRestriccionDiaRepository(entityManagerFactory);
					TipoRestriccionDia tipoRestriccionDia = tipoReservaRepository.findById("laboral");
					
					ReservaRepository reservaRepository = new ReservaRepository(entityManagerFactory);
					Reserva reserva = new Reserva();		
					String fecha = (String)request.getSession().getAttribute("fecha");
					String strHora = (String)request.getSession().getAttribute("hora");
					String strFechaInicio = fecha + " " + strHora; 
					Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd HH").parse(strFechaInicio);
					String strNumeroSesiones = (String)request.getSession().getAttribute("numeroSesiones");
					int horaFin = Integer.parseInt(strHora) + Integer.parseInt(strNumeroSesiones);
					String strFechaFin = fecha + " " + horaFin;
					Date fechaFin = new SimpleDateFormat("yyyy-MM-dd HH").parse(strFechaFin);
					reserva.setFechaInicio(new Timestamp(fechaInicio.getTime()));
					reserva.setFechaFin( new Timestamp( fechaFin.getTime() ) );
					reserva.setTipoRestriccionDia(tipoRestriccionDia);
					List<Reserva> reservas = Arrays.asList(reserva);
					
					intervencion.setAsunto(asuntoFinal);
					intervencion.setDescripcion(descripcionFinal);
					intervencion.setTipoIntervencion(tipoIntervencionFinal);
					intervencion.setMascota(mascota);
					intervencion.setVeterinarios(veterinarios);
					intervencion.setFactura(facturaGuardada);
					intervencion.setReservas(reservas);
					Intervencion intervencionGuardada = intervencionRepository.save(intervencion);
					
					//reserva.setIntervencion(intervencionGuardada);
										
					eliminarDatosSesion(request);
					cargarTipoIntervencion(request);
					List<Intervencion> intervencionesList = Arrays.asList(intervencionGuardada);
					request.setAttribute("intervencionesList", intervencionesList);
					request.getRequestDispatcher("intervencion.jsp").forward(request, response);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				break;
			default:
				break;
			}

		}

		if (request.getParameter("eliminar") != null) {

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

		if (request.getParameter("btntipo") != null) {
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
		
		Intervencion intervencion = new Intervencion();
		
		intervencion.setId( Integer.parseInt(request.getParameter("id")) );
		
		intervencion.setAsunto( request.getParameter("asunto") );
		intervencion.setDescripcion( request.getParameter("descripcion") );
		
		TipoIntervencionRepository tipoIntervencionRepository = 
				new TipoIntervencionRepository(entityManagerFactory);
		
		TipoIntervencion tipoIntervencion = 
				tipoIntervencionRepository.findById( Integer.parseInt(request.getParameter("tipointervencion")) );
		
		intervencion.setTipoIntervencion( tipoIntervencion );
		
		
		MascotaRepository mascotaRepository = new MascotaRepository( entityManagerFactory );
		Mascota mascota = mascotaRepository.findById( Integer.parseInt(request.getParameter("idmascota")) );
		intervencion.setMascota( mascota );
		
		FacturaRepository facturaRepository = new FacturaRepository( entityManagerFactory );
		Factura factura = facturaRepository.findById( Integer.parseInt(request.getParameter("factura")) );
		intervencion.setFactura(factura);
		
		
		
		VeterinarioRepository veterinarioRepository = new VeterinarioRepository(entityManagerFactory);
		Veterinario veterinario;
		String equipo = request.getParameter("equipo");
		String[] equipoSplit = equipo.split(",");
		
		List<Veterinario> veterinarios = new ArrayList<>();
		for (String strVeterinario : equipoSplit) {
			veterinario = veterinarioRepository.findById(strVeterinario);
			veterinarios.add(veterinario);
		}
		intervencion.setVeterinarios(veterinarios);
		
		
		cargarTipoIntervencion(request);

		return intervencionRepository.update(intervencion);
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

	public void desbloquear(String bloqueo, HttpServletRequest request) {

		switch (bloqueo) {
		case "bloqueoFecha":
			request.setAttribute("bloqueoFecha", false);
			request.setAttribute("bloqueoHora", true);
			request.setAttribute("bloqueoSesiones", true);
			request.setAttribute("bloqueoDatos", true);
			request.setAttribute("bloqueoFactura", true);
			break;
		case "bloqueoHora":
			request.setAttribute("bloqueoFecha", true);
			request.setAttribute("bloqueoHora", false);
			request.setAttribute("bloqueoSesiones", true);
			request.setAttribute("bloqueoDatos", true);
			request.setAttribute("bloqueoFactura", true);
			break;
		case "bloqueoSesiones":
			request.setAttribute("bloqueoFecha", true);
			request.setAttribute("bloqueoHora", true);
			request.setAttribute("bloqueoSesiones", false);
			request.setAttribute("bloqueoDatos", true);
			request.setAttribute("bloqueoFactura", true);
			break;
		case "bloqueoDatos":
			request.setAttribute("bloqueoFecha", true);
			request.setAttribute("bloqueoHora", true);
			request.setAttribute("bloqueoSesiones", true);
			request.setAttribute("bloqueoDatos", false);
			request.setAttribute("bloqueoFactura", true);
			break;
		case "bloqueoFactura":
			request.setAttribute("bloqueoFecha", true);
			request.setAttribute("bloqueoHora", true);
			request.setAttribute("bloqueoSesiones", true);
			request.setAttribute("bloqueoDatos", true);
			request.setAttribute("bloqueoFactura", false);
			break;
		}

	}

	public void eliminarDatosSesion(HttpServletRequest request) {
		request.getSession().removeAttribute("asunto");
		request.getSession().removeAttribute("descripcion");
		request.getSession().removeAttribute("tipointervencion");
		request.getSession().removeAttribute("idmascota");
		request.getSession().removeAttribute("equipo");
		request.getSession().removeAttribute("sesiones");
		request.getSession().removeAttribute("numeroSesiones");
		request.getSession().removeAttribute("fecha");
		request.getSession().removeAttribute("hora");
	}
}
