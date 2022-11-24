package es.iespuertodelacruz.cnrg.veterinariajavaweb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.iespuertodelacruz.cnrg.veterinariajavaweb.entities.Cliente;
import es.iespuertodelacruz.cnrg.veterinariajavaweb.repositories.ClienteRepository;


/**
 * Servlet implementation class ClientesServlet
 */
@WebServlet({ "/ClientesServlet", "/clientesservlet" })
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);
				
		if(request.getParameter("id") != null) {
			Cliente cliente = clienteRepository.findById(request.getParameter("id"));
			request.setAttribute("cliente", cliente);
			List<Cliente> clientesList = clienteRepository.findAll();
			request.setAttribute("clientesList", clientesList);
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}
		
		List<Cliente> clientesList = clienteRepository.findAll();
		request.setAttribute("clientesList", clientesList);
		request.getRequestDispatcher("clientes.jsp").forward(request, response);
	}
	
	private Cliente agregarCliente(HttpServletRequest request) {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);
		
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String direccion = request.getParameter("direccion");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		
		
		Cliente cliente = new Cliente();
		cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);

		return clienteRepository.save(cliente);
	}
	
	private boolean borrarCliente(HttpServletRequest request) {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);
		
		
		String dni = request.getParameter("dni");
		
		
		return clienteRepository.delete(dni);
	}
	
	private boolean editarCliente(HttpServletRequest request) {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);
		
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String direccion = request.getParameter("direccion");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		
		
		Cliente cliente = new Cliente();
		cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
		
	
		return clienteRepository.update(cliente);
	}
	
	private Cliente mostrarCliente(HttpServletRequest request) {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);
		
		String dni = request.getParameter("dni");
		
		return clienteRepository.findById(dni);
	}
	
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory entityManagerFactory = 
				(EntityManagerFactory)request.getServletContext().getAttribute("entityManagerFactory");
		ClienteRepository clienteRepository = new ClienteRepository(entityManagerFactory);
				
		
		if(request.getParameter("agregar") != null) {
			Cliente cliente = agregarCliente(request);	
			
			List<Cliente> clientesList = Arrays.asList(cliente);
			request.setAttribute("clientesList", clientesList);
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}

		if(request.getParameter("borrar") != null) {
			
			borrarCliente(request);
			List<Cliente> clientesList = clienteRepository.findAll();
			request.setAttribute("clientesList", clientesList);
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}
		
		if(request.getParameter("editar") != null) {
			editarCliente(request);
			List<Cliente> clientesList = clienteRepository.findAll();
			request.setAttribute("clientesList", clientesList);
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}
		
		
		if(request.getParameter("mostrar") != null) {
			Cliente cliente = mostrarCliente(request);	
			
			List<Cliente> clientesList = Arrays.asList(cliente);
			request.setAttribute("clientesList", clientesList);
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}
		
		if(request.getParameter("mostrartodos") != null) {
			List<Cliente> clientesList = clienteRepository.findAll();
			
			request.setAttribute("clientesList", clientesList);
			request.getRequestDispatcher("clientes.jsp").forward(request, response);
		}
		

		List<Cliente> clientesList = clienteRepository.findAll();
	}

}
