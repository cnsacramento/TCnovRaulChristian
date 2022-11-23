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
		
		String metodo = request.getParameter("metodo");
		if(metodo != null) {
			switch(metodo) {
			case "edit": 
					Mascota mascota = mascotaRepository.findById(Integer.parseInt(request.getParameter("id")));
					request.setAttribute("mascota", mascota);
				break;
			case "delete": 
					
				break;
			case "intervencion": 
				break;	
			}
		}
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
		
		if (proceso.equals("Buscar") && !request.getParameter("idMascota").isEmpty() ) {
			
			try {
				int id = Integer.parseInt(request.getParameter("idMascota"));
				mascotas = new ArrayList<>();
				if (mascotaRepository.findById(id) != null) {
					mascotas.add(mascotaRepository.findById(id));
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}else if(proceso.equals("Crear")) {
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
			
		}else {
		}
		
		if(mascotas == null) {
			 mascotas = mascotaRepository.findAll();
		}
		request.setAttribute("mascotas", mascotas);
		request.getRequestDispatcher("mascotas.jsp").forward(request, response);
	}

}
