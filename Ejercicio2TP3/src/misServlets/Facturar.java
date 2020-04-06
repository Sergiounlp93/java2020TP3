package misServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Facturar
 * 
 * @WebServlet(urlPatterns = { "/Facturar" },
		initParams = { 	@WebInitParam(name="golo1", value="chupetin,15"),@WebInitParam(name="golo2", value="alfajor,120"),@WebInitParam(name="golo3", value="chocolate,90")	} 
		)
 * 
 */
@WebServlet("/Facturar")
public class Facturar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Golosina [] g = new Golosina[5];
	
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
    public Facturar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Entro en contexto
		ServletContext contexto = this.getServletContext();
		ArrayList<Golosina> lista = (ArrayList<Golosina>) contexto.getAttribute("Lista");//lo tomo del contexto
		
		/*ESTE PASO ES IMPORTANTE PARA PODER COMUNICAR LOS DATOS CON OTRO SERVLET*/
		HttpSession sesion = request.getSession(false);	// RECUPERAMOS LA SESSION
		
		if (sesion == null) {  // si no esta logueado
			response.sendRedirect("/compras/login.html");
		} else{
			//Obtengo las golosinas con la cantidad seleccionada por el usuario junto con la informacion del precio y nombre
			
			
			
			
			//	g[0]= (Golosina) sesion.getAttribute("golosina1");
				//g[1]= (Golosina) sesion.getAttribute("golosina2");
				//g[2]= (Golosina) sesion.getAttribute("golosina3");
				float total=0;
				for(int indice=0;indice<lista.size();indice++) {
					total = (  total+(lista.get(indice).getPrecio()*lista.get(indice).getCantidad())   );
				}
			//	+(g[1].getPrecio()*g[1].getCantidad())+(g[2].getPrecio()*g[2].getCantidad());
				
				//INTERFAZ PARA EL USUARIO
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
						"    <div class=\"caramelo\">\r\n" + 
						"    	<img alt=\"una imagen\" src=\"money.jpg\">\r\n" + 
						"    </div>\r\n" + 
						"    \r\n" + 
						"    \r\n" + 
						"    <!-- CUERPO PRINCIPAL -->\r\n" + 
						"    <main class=\"contenedor seccion contenido-centrado\">\r\n" + 
						"        <h2 class=\"fw-900 centrar-texto\">Productos facturados hasta ahora</h2>\r\n" + 
						"		<!-- ESTRUCTURA DEL FORMULARIO -->\r\n" + 
						"        <form class=\"contacto\" action=\"Facturar\" method=\"get\">\r\n" + 
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
						"    <th scope=\"col\">Precio Total</th>\r\n" + 
						"\r\n" + 
						"  </tr>\r\n");
				
				for(int indice=0; indice<lista.size();indice++) {
					if(lista.get(indice).getCantidad() != 0) {
						out.print("  <tr>\r\n" + 
								"	  <td>\r\n" + 
								"	  	"+lista.get(indice).getNombre()+"	\r\n" + 
								"	  </td>\r\n" + 
								"	  <td>\r\n" + 
								"	  	$"+lista.get(indice).getPrecio()+"	\r\n" + 
								"	  </td>\r\n" + 
								"	  <td>\r\n" + 
								"	  	  <input type=\"text\" name=\"cantidad"+indice+"\" id=\"Codigocantidad\" placeholder=\"Escribe cantidad\" value=\"$"+  lista.get(indice).getCantidad()*lista.get(indice).getPrecio()+"\" pattern=\"[0-9]*\" readonly=\"readonly\">\r\n" + 
								"	  </td>\r\n" + 
								"	  \r\n" + 
								"  </tr>\r\n"); 
					}
				}
				/*
				if(g[0].getCantidad() != 0) {
						out.print("  <tr>\r\n" + 
								"	  <td>\r\n" + 
								"	  	"+g[0].getNombre()+"	\r\n" + 
								"	  </td>\r\n" + 
								"	  <td>\r\n" + 
								"	  	$"+g[0].getPrecio()+"	\r\n" + 
								"	  </td>\r\n" + 
								"	  <td>\r\n" + 
								"	  	  <input type=\"text\" name=\"cantidad0\" id=\"Codigocantidad\" placeholder=\"Escribe cantidad\" value=\"$"+  g[0].getCantidad()*g[0].getPrecio()+"\" pattern=\"[0-9]*\" readonly=\"readonly\">\r\n" + 
								"	  </td>\r\n" + 
								"	  \r\n" + 
								"  </tr>\r\n"); 
				}
				if(g[1].getCantidad() != 0) {
						out.print(		
								"  <tr>\r\n" + 
								"    <td>\r\n" + 
								"  	"+g[1].getNombre()+"	\r\n" + 
								"  </td>\r\n" + 
								"   <td>\r\n" + 
								"	  	$"+g[1].getPrecio()+"	\r\n" + 
								"	  </td>\r\n" + 
								"	    <td>\r\n" + 
								"	  	  <input type=\"text\" name=\"cantidad1\" id=\"Codigocantidad\" placeholder=\"Escribe cantidad\" value=\"$"+ g[1].getCantidad()*g[1].getPrecio()+"\" pattern=\"[0-9]*\" readonly=\"readonly\">\r\n" + 
								"	  </td>\r\n" + 
								"  </tr>\r\n");
				}
				if(g[2].getCantidad() != 0) {
						out.print(
								"  <tr>\r\n" + 
								"    <td>\r\n" + 
								"  	"+g[2].getNombre()+"\r\n" + 
								"  </td>\r\n" + 
								"   <td>\r\n" + 
								"	  	$"+g[2].getPrecio()+ "\r\n" + 
								"	  </td>\r\n" + 
								"	    <td>\r\n" + 
								"	  	  <input type=\"text\" name=\"cantidad2\" id=\"Codigocantidad\" placeholder=\"Escribe cantidad\" value=\"$"+ g[2].getCantidad()*g[2].getPrecio()+"\" pattern=\"[0-9]*\" readonly=\"readonly\">\r\n" + 
								"	  </td>\r\n" + 
								"  </tr>\r\n");
				}
				
				*/
				
					out.print(
							"  <tr>\r\n" + 
							"   <td>MONTO TOTAL FACTURADO" + 
							"	  </td>\r\n" + 
							"	    <td>\r\n" + 
							"	  	$ "+total+"  \r\n" + 
							"	  </td>\r\n" + 
							"  </tr>\r\n");
				
				
				out.print(
						"  \r\n" + 
						" \r\n" + 
						"\r\n" + 
						"</table>\r\n" + 
						"             \r\n" + 
						"            </fieldset>\r\n" + 
						"\r\n" + 
						              
						"        </form>\r\n" + 
						"<form class=\"contacto\" action=\"TerminarSesion\" method=\"post\" > \r\n" +         
									"<input type=\"submit\" value=\"Salir\" class=\"boton boton-rojo\">\r\n" + 
						"</form>\r\n"
						+ "<!--FIN DE LA ESTRUCTURA DEL FORMULARIO -->        \r\n" + 
						" <a class=\"boton boton-rojo\" href=\"http://localhost:8080/compras/Productos\"> Seguir Comprando </a>"+
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
			
		}
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
