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

import org.mindrot.jbcrypt.BCrypt;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.CuentaVeterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecialidadVeterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Veterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.CuentaVeterinarioRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.EspecialidadVeterinarioRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.VeterinarioRepository;

/**
 * Servlet implementation class VeterinarioServlet
 */
@WebServlet({"/VeterinarioServlet","/veterinarioServlet"})
public class VeterinarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VeterinarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext().getAttribute("entityManagerFactory");
		VeterinarioRepository veterinarioRepository = new VeterinarioRepository(entityManagerFactory);
		EspecialidadVeterinarioRepository especialidadRepository = new EspecialidadVeterinarioRepository(entityManagerFactory);
		CuentaVeterinarioRepository cuentaRepository = new CuentaVeterinarioRepository(entityManagerFactory);
		
		List<Veterinario> veterinarios = new ArrayList<>();
		List<EspecialidadVeterinario> especialidades = new ArrayList<>();
		
		String metodo = request.getParameter("metodo");
		
		switch(metodo) {			
			case "editVeterinario": 
				Veterinario veterinario = veterinarioRepository.findById(request.getParameter("veterinarioId"));
				veterinario.setDni(veterinario.getDni());
				veterinario.setNombre(veterinario.getNombre());
				veterinario.setApellidos(veterinario.getApellidos());
				veterinario.setEspecialidadVeterinario(veterinario.getEspecialidadVeterinario());
				veterinario.setTelefono(veterinario.getTelefono());
				
				request.setAttribute("veterinario", veterinario);
//				if(!veterinarioRepository.update(veterinario)) {
//					request.setAttribute("mensaje", "No se ha podido modificar el veterinario");
//				}
				break;
				
			case "deleteVeterinario": 
				try {
					if(!veterinarioRepository.delete(request.getParameter("veterinarioId"))){
						request.setAttribute("mensaje", "No ha sido posible");
					}else {
						request.setAttribute("mensaje", "Se ha borrado correctamente");
					}
				}catch(Exception ex) {}
				break;
				
			case "editEspecialidad": 
				try {
					EspecialidadVeterinario especialidad = especialidadRepository.findById(Integer.parseInt(request.getParameter("especialidad")));
					request.setAttribute("especialidad", especialidad);
				}catch(Exception ex) {}
				break;
				
			case "deleteEspecialidad": 
				if(!especialidadRepository.delete(Integer.parseInt(request.getParameter("especialidad")))) {
					request.setAttribute("mensaje", "No ha sido posible borrar la especialidad");
				}
				break;
			case "asignarEspecialidad":
				request.setAttribute("especialidadAsignada", request.getParameter("especialidad"));
				break;
		}
		
		especialidades = especialidadRepository.findAll();
		veterinarios = veterinarioRepository.findAll();
		request.setAttribute("especialidades", especialidades);
		request.setAttribute("veterinarios", veterinarios);
		request.getRequestDispatcher("veterinario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext().getAttribute("entityManagerFactory");
		VeterinarioRepository veterinarioRepository = new VeterinarioRepository(entityManagerFactory);
		EspecialidadVeterinarioRepository especialidadRepository = new EspecialidadVeterinarioRepository(entityManagerFactory);
		CuentaVeterinarioRepository cuentaRepository = new CuentaVeterinarioRepository(entityManagerFactory);

		List<Veterinario> veterinarios = new ArrayList<>();
		List<EspecialidadVeterinario> especialidades = new ArrayList<>();
		
		String metodo = request.getParameter("boton");
		
		switch(metodo) {
		
			case "Crear Veterinario": 
				try {
				CuentaVeterinario cuenta = new CuentaVeterinario();
				cuenta.setCorreo(request.getParameter("correo"));
				String hashpw = BCrypt.hashpw(request.getParameter("contrasenia"),  BCrypt.gensalt());
				cuenta.setContrasenia(hashpw);
				
				if(cuentaRepository.save(cuenta) != null){
					Veterinario veterinario = new Veterinario();
					veterinario.setDni(request.getParameter("dni"));
					veterinario.setNombre(request.getParameter("nombre"));
					veterinario.setApellidos(request.getParameter("apellidos"));
					veterinario.setEspecialidadVeterinario(especialidadRepository.findById(Integer.parseInt(request.getParameter("especialidad"))));
					veterinario.setTelefono(request.getParameter("telefono"));
					veterinario.setCuentaVeterinario(cuenta);
					veterinarioRepository.save(veterinario);
				}else {
					request.setAttribute("mensaje", "No se ha podido crear la cuenta del veterinario");
				}

				}catch(Exception ex) {
					ex.printStackTrace();
				}
				break;
				
			case "Actualizar Veterinario":
				
				Veterinario original = veterinarioRepository.findById(request.getParameter("dni"));
				
				Veterinario veterinario = new Veterinario();
				veterinario.setDni(request.getParameter("dni"));
				veterinario.setNombre(request.getParameter("nombre"));
				veterinario.setApellidos(request.getParameter("apellidos"));
				veterinario.setEspecialidadVeterinario(especialidadRepository.findById(Integer.parseInt(request.getParameter("especialidad"))));
				veterinario.setTelefono(request.getParameter("telefono"));
				veterinario.setCuentaVeterinario(original.getCuentaVeterinario());
				if(!veterinarioRepository.update(veterinario)){
					request.setAttribute("mensaje", "No se ha podido actualizar el veterinario");
				}
				break;
				
			case "Editar Especialidad": 
				try {
					EspecialidadVeterinario especialidad = new EspecialidadVeterinario();
					especialidad.setId(Integer.parseInt(request.getParameter("id")));
					especialidad.setNombre(request.getParameter("nombre"));
					especialidadRepository.update(especialidad);
				}catch(Exception ex) {}
				break;
				
			case "Crear Especialidad": 
				EspecialidadVeterinario especialidad = new EspecialidadVeterinario();
				especialidad.setNombre(request.getParameter("nombre"));
				especialidadRepository.save(especialidad);
				break;
		}
		
		if(metodo.equals("Encontrar")){
			veterinarios = veterinarioRepository.findByName(request.getParameter("nombreVeterinario"));
		}else {
			veterinarios = veterinarioRepository.findAll();
		}
		
		especialidades = especialidadRepository.findAll();
		request.setAttribute("especialidades", especialidades);
		request.setAttribute("veterinarios", veterinarios);
		request.getRequestDispatcher("veterinario.jsp").forward(request, response);
		
		
	}

}
