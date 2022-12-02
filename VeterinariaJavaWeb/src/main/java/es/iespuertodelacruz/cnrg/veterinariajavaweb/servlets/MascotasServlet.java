package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecieMascota;
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
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext().getAttribute("entityManagerFactory");
		EspecieMascotaRepository especieRepository = new EspecieMascotaRepository(entityManagerFactory);
		MascotaRepository mascotaRepository = new MascotaRepository(entityManagerFactory);
		
		List<Mascota> mascotas = new ArrayList<>();
		List<EspecieMascota> especies  = new ArrayList<>();
		
		String metodo = request.getParameter("metodo");
		if(metodo != null) {
			switch(metodo) {
			case "save":
				if(!request.getParameter("clienteDni").isEmpty()) {
					request.getServletContext().setAttribute("clienteDni", request.getParameter("clienteDni"));
				}
				
				if(!request.getParameter("especieId").isEmpty()) {
					request.setAttribute("especieId", request.getParameter("especieId"));   
				}
				especies = especieRepository.findAll();
				String dni = request.getParameter("clienteDni").toString();
				mascotas = (ArrayList)mascotaRepository.findByCliente(dni);
				request.setAttribute("mascotas", mascotas);
				request.setAttribute("especies", especies);
				request.getRequestDispatcher("crearMascota.jsp").forward(request, response);
				break;
			case "edit": 
					Mascota mascota = mascotaRepository.findById(Integer.parseInt(request.getParameter("id")));
					request.setAttribute("mascota", mascota);
					String[] split = mascota.getFechaNacimiento().toString().split(" ");
					request.setAttribute("fechaNacimiento", split[0]);
					mascotas = mascotaRepository.findAll();
					request.setAttribute("mascotas", mascotas);
				break;
			case "delete": 
				if(mascotaRepository.delete(Integer.parseInt(request.getParameter("id")))) {
					request.setAttribute("mensaje", "Se ha borrado la exitosamente");
				}else {
					request.setAttribute("mensaje", "No ha sido posible borrar la mascota");
				}
				mascotas = mascotaRepository.findAll();
				request.setAttribute("mascotas", mascotas);
				break;
			case "intervencion": 
				break;
			case "editEspecie":
				EspecieMascota especie = especieRepository.findById(Integer.parseInt(request.getParameter("especieId")));
				request.setAttribute("especie", especie);
				especies = especieRepository.findAll();
				request.setAttribute("especies", especies);
				request.getRequestDispatcher("crearMascota.jsp").forward(request, response);
				break;
				
			case "deleteEspecie":
				if(especieRepository.delete(Integer.parseInt(request.getParameter("especieId")))){
					request.setAttribute("mensaje", "Se ha borrado la exitosamente");
				}else {
					request.setAttribute("mensaje", "No ha sido posible borrar la mascota");
				}
				especies = especieRepository.findAll();
				request.setAttribute("especies", especies);
				request.getRequestDispatcher("crearMascota.jsp").forward(request, response);
				break;
			}
		}
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
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext().getAttribute("entityManagerFactory");
		MascotaRepository mascotaRepository = new MascotaRepository(entityManagerFactory);
		EspecieMascotaRepository especieRepository = new EspecieMascotaRepository(entityManagerFactory);
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);

		String proceso = request.getParameter("boton");
		List<Mascota> mascotas = null;
		List<EspecieMascota> especies = null;
		
		if (proceso.equals("Buscar") && !request.getParameter("idMascota").isEmpty()) {
			
			try {
				int id = Integer.parseInt(request.getParameter("idMascota"));
				mascotas = new ArrayList<>(); 
				if (mascotaRepository.findById(id) != null) {
					mascotas.add(mascotaRepository.findById(id));
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}else if(proceso.equals("Crear Mascota")) {
				try {
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					String[] split = request.getParameter("fechaNacimiento").split("-");
					Date fecha = formato.parse(split[2]+"/"+split[1]+"/"+split[0]);
					
					Mascota mascota = new Mascota();
					mascota.setNombre(request.getParameter("nombre"));
					mascota.setFechaNacimiento(new Timestamp(fecha.getTime()));
					mascota.setPeso(Double.parseDouble((request.getParameter("peso").toString())));
					mascota.setEspecieMascota(especieRepository.findById(Integer.parseInt(request.getParameter("especie").toString())));
					mascota.setCliente(clienteRepository.findById(request.getParameter("cliente").toString()));
					mascotaRepository.save(mascota);
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}else if(proceso.equals("Editar")) {
			
			try {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String[] split = request.getParameter("fechaNacimiento").split("-");
				Date fecha = formato.parse(split[2]+"/"+split[1]+"/"+split[0]);
				
				Mascota mascota = new Mascota();
				mascota.setId(Integer.parseInt(request.getParameter("id")));
				mascota.setNombre(request.getParameter("nombre"));
				mascota.setFechaNacimiento(new Timestamp(fecha.getTime()));
				mascota.setPeso(Double.parseDouble((request.getParameter("peso").toString())));
				mascota.setEspecieMascota(especieRepository.findById(Integer.parseInt(request.getParameter("especie").toString())));
				mascota.setCliente(clienteRepository.findById(request.getParameter("cliente").toString()));
				mascotaRepository.update(mascota);

			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else if(proceso.equals("Crear Especie")) {
			EspecieMascota especie = new EspecieMascota();
			especie.setNombre(request.getParameter("nombre"));
			if(request.getParameter("peligrosa").equals("Si")) {
				especie.setPeligrosa((byte)1);
			}else {
				especie.setPeligrosa((byte)0);
			}
			especieRepository.save(especie);
			especies = especieRepository.findAll();
		}else if(proceso.equals("Editar Especie")) {
			try {
				EspecieMascota especie = new EspecieMascota();
				especie.setId(Integer.parseInt(request.getParameter("id")));
				especie.setNombre(request.getParameter("nombre"));
				if(request.getParameter("peligrosa").equals("Si")) {
					especie.setPeligrosa((byte)1);
				}else {
					especie.setPeligrosa((byte)0);
				}
				especieRepository.update(especie);
				especies = especieRepository.findAll();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}else if(proceso.equals("Encontrar")) {
			especies = especieRepository.findByName(request.getParameter("nombreEspecie"));
		}
		
		if(especies != null) {
			request.setAttribute("especies", especies);
			request.getRequestDispatcher("crearMascota.jsp").forward(request, response);
		}else {
			if(mascotas == null) {
				 mascotas = mascotaRepository.findAll();
			}
			request.setAttribute("mascotas", mascotas);
			request.getRequestDispatcher("mascotas.jsp").forward(request, response);
		}
	}

}
