package misServlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Productos
 * 
 */
@WebServlet(urlPatterns = { "/Productos" },
		initParams = { 	@WebInitParam(name="golo1", value="chupetin,15"),@WebInitParam(name="golo2", value="alfajor,120"),@WebInitParam(name="golo3", value="chocolate,90")	} 
		)
public class Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Golosina [] g;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Productos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext contexto = this.getServletContext();
		ArrayList<Golosina> lista = (ArrayList<Golosina>) contexto.getAttribute("Lista");//lo tomo del contexto
		//g = new Golosina[lista.size()];//reservo espacio para los elementos de la lista
		
		HttpSession sesion = request.getSession(false);// recuperamos la sesion
		if (sesion == null) {  // si no esta logueado
			response.sendRedirect("/compras/login.html");
		} else{ 
			//ACA SE INICIO SESION DE USUARIO
				Usuario user = (Usuario) sesion.getAttribute("usuario");
							
				for(int indice=0; indice< lista.size();indice++) {					
						if(indice==0) {
							if (sesion.getAttribute("cant"+indice) == null) {
								//NO EXISTE ATRIBUTO CON ESE NOMBRE ENTONCES LO CREO
									sesion.setAttribute("cant"+indice, "0");	
									lista.get(indice).setCantidad(Integer.parseInt((String)sesion.getAttribute("cant"+indice)));
							}
						}
						if (sesion.getAttribute("cant"+indice) == null) {
							//NO EXISTE ATRIBUTO CON ESE NOMBRE ENTONCES LO CREO
								sesion.setAttribute("cant"+indice, "0");	
								lista.get(indice).setCantidad(Integer.parseInt((String)sesion.getAttribute("cant"+indice)));
							}
						else{
							//EXITE EL ATRIBUTO LO TOMO Y LO CARGO EN EL OBJETO
								int numero = Integer.parseInt((String)sesion.getAttribute("cant"+indice));
								lista.get(indice).setCantidad(numero);
							}
				}
				
				//imprime en pantalla
				/*INTERFAZ PARA EL USUARIO*/

				
				out.print("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"    <meta charset=\"UTF-8\">\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
						"    <!-- TAG DE SOPORTE PARA EDGE -->\r\n" + 
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
						"    <title>Golosinas</title>\r\n" + 
						"	<!-- ENLACES A HOJAS DE ESTILO LOCALES Y A FUENTE DE GOOGLE -->\r\n" + 
						"    \r\n" + 
						"    <link href=\"https://fonts.googleapis.com/css?family=Lato:300,400,700,900\" rel=\"stylesheet\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"normalize.css\">\r\n" + 
						"    <link rel=\"stylesheet\" href=\"styles.css\">\r\n" + 
						"    \r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"\r\n" + 
						"	<!-- ENCABEZADO -->\r\n" + 
						"    <header class=\"site-header\"></header>\r\n" + 
						"    <h1></h1>\r\n" + 
						"<center><div class=\"alert alert-primary\" role=\"alert\"> Hola "+user.getNombre()+" 0.0/ </div></center>"+
						"    <div class=\"caramelo\">\r\n" + 
						"    	<img alt=\"una imagen\" src=\"caramelo.jpg\">\r\n" + 
						"    </div>\r\n" + 
						"    \r\n" + 
						"    \r\n" + 
						"    <!-- CUERPO PRINCIPAL -->\r\n" + 
						"    <main class=\"contenedor seccion contenido-centrado\">\r\n" + 
						"        <h2 class=\"fw-900 centrar-texto\">ELIGE TUS GOLOSINAS \\ O.O /</h2>\r\n"); 

						
						
						//ACA SE REDIRECCIONA (EJ: Productoprueba)(Facturar)
						out.print(
						"		<!-- ESTRUCTURA DEL FORMULARIO -->\r\n" + 
						"        <form class=\"contacto\" action=\"Productoprueba\" method=\"get\">\r\n" + 
						"            <fieldset>\r\n" + 
						"                <legend> </legend>\r\n" + 
						"\r\n" + 
						"<table class=\"table table-dark\" >\r\n" + 
						"\r\n" + 
						"  <tr  >\r\n" + 
						"\r\n" + 
						"    <th scope=\"col\">Golosinas</th>\r\n" + 
						"\r\n" + 
						"    <th scope=\"col\">precio</th>\r\n" + 
						"\r\n" + 
						"    <th scope=\"col\">Cantidad</th>\r\n" + 
						"\r\n");
					for(int indice=0; indice< lista.size();indice++) {						
									out.print(	
											
											"  </tr>\r\n" + 
											"  <tr>\r\n" + 
											"	  <td>\r\n" + 
											"	  	"+lista.get(indice).getNombre()+"	\r\n" + 
											"	  </td>\r\n" + 
											"	  <td>\r\n" + 
											"	  	"+ String.valueOf(lista.get(indice).getPrecio())+"	\r\n" + 
											"	  </td>\r\n" + 
											"	  <td>\r\n" + 
											"	  	  <input type=\"text\" name=\"cantidad"+indice+"\""+ "id=\"Codigocantidad\" placeholder=\"Escribe cantidad\" value=\""+ lista.get(indice).getCantidad()+"\" pattern=\"[0-9]*\" required>\r\n" + 
											"	  </td>\r\n" + 
											"	  \r\n" + 
											"  </tr>\r\n" 
											
									);			
					}						
					
					out.print(	"  \r\n" + 
						" \r\n" + 
						"\r\n" + 
						"</table>\r\n" + 
						"             \r\n" + 
						"            </fieldset>\r\n" + 
						"\r\n" + 
						"            <input type=\"submit\" value=\"Facturar\" class=\"boton boton-verde\">\r\n" + 
						              
						"        </form>\r\n" + 
						"<form class=\"contacto\" action=\"TerminarSesion\" method=\"post\" > \r\n" +         
									"<input type=\"submit\" value=\"Salir\" class=\"boton boton-rojo\">\r\n" + 
						"</form>\r\n"
						+ "<!--FIN DE LA ESTRUCTURA DEL FORMULARIO -->        \r\n" + 
						"    </main>\r\n" + 
						"    \r\n" + 
						"	<!-- FOOTER -->\r\n" + 
						"    <footer class=\"site-footer seccion\">\r\n" + 
						"        <div class=\"contenedor contenedor-footer\">\r\n" + 
						"            <nav class=\"navegacion\"></nav>\r\n" + 
						"            <p class=\"copyright\">Todos los Derechos Reservados 2020 &copy; </p>\r\n" + 
						"        </div>\r\n" + 
						"    </footer>\r\n" + 
						"</body>\r\n" + 
						"</html>");
					
				
				//ULTIMA LINEA ANTES DE TERMINAL LA EJECUCION
					
				for(int indice=0;indice<lista.size();indice++) {
					//GUARDAR LOS VALORES LAS CANTIDADES QUE INTRODUJO EL USUARIO EN ATRIBUTOS
					sesion.setAttribute("cant"+indice, request.getParameter("cantidad"+indice));	
					
					//ACTUALIZAR LOS VALORES DE LAS CANTIDADES DE GOLOSINA PARA FACTURAR
					lista.get(indice).setCantidad(Integer.parseInt((String)sesion.getAttribute("cant"+indice)));

				}
				
				
				//GUARDAR EL ESTADO DE LAS GOLOSINAS PARA FACTURAR
				contexto.setAttribute("Lista", lista);
				response.sendRedirect("Facturar");	
			  }
		//FIN DEL doGet
		}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
