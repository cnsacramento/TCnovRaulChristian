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

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.EspecialidadVeterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Veterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.EspecialidadVeterinarioRepository;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.VeterinarioRepository;

/**
 * Servlet implementation class VeterinarioServlet
 */
@WebServlet("/VeterinarioServlet")
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
		
		List<Veterinario> veterinarios = new ArrayList<>();
		List<EspecialidadVeterinario> especialidades = new ArrayList<>();
		
		String metodo = request.getParameter("metodo");
		
		switch(metodo) {
			case "saveVeterinario":
				
				break;
			
			case "updateVeterinario": 
				break;
				
			case "deleteVeterinario": 
				break;
				
			case "editEspecialidad": 
				try {
					EspecialidadVeterinario especialidad = especialidadRepository.findById(Integer.parseInt(request.getParameter("editEspecialidad")));
					request.setAttribute("especialidad", especialidad);
				}catch(Exception ex) {}
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
		
	}

}
