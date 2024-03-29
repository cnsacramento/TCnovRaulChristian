package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.CuentaVeterinario;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.CuentaVeterinarioRepository;

/**
 * Servlet implementation class logServlet
 */
@WebServlet("/logServlet")
public class logServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getServletContext().getAttribute("entityManagerFactory");
		CuentaVeterinarioRepository cvRepository = new CuentaVeterinarioRepository(entityManagerFactory);
		
		String proceso = request.getParameter("boton");
		
		String correo = request.getParameter("correo");
        String contra = request.getParameter("contrasenia");
		
		if(proceso.equals("login")) {
			
			if(cvRepository.findById(correo) != null) {
				CuentaVeterinario cv = cvRepository.findById(correo);
				if(BCrypt.checkpw(contra, cv.getContrasenia())) {
					 request.setAttribute("mensaje", "Se ha iniciado sesión correctamente");
					 request.getSession().setAttribute("usuario", cv);
				}else {
	                request.setAttribute("mensaje", "El correo y la contrasenia no coinciden");
				}
			}else {
                request.setAttribute("mensaje", "El correo introducido no está registrado");
			}
			
		}else if(proceso.equals("register")) {
            
            //COMPROBACION DE CORREO
            Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); 
     
            Matcher mather = pattern.matcher(correo);
     
            if (mather.find() == true) {
                if (cvRepository.findById(correo) == null) {
                	CuentaVeterinario cv = new CuentaVeterinario();
                	cv.setCorreo(correo);
                	cv.setContrasenia(contra);
                	if(cvRepository.save(cv) != null) {
                        request.setAttribute("mensaje", "Se ha registrado correctamente");
                	}else {
                        request.setAttribute("mensaje", "Se ha producido un error inexperado");
                	}
                }else {
                    request.setAttribute("mensaje", "El correo introducido ya está siendo utilizado");
                }
            } else {
                request.setAttribute("mensaje", "El correo introducido no es valido");
            }
		}
		
		if(request.getSession().getAttribute("usuario") != null){
	        request.getRequestDispatcher("panelVeterinario.jsp").forward(request, response);
		}else {
	        request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
