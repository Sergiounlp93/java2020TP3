package misServlets;
/*son las clases que importamos para trabajar en el proyecto*/
import java.io.IOException;

import java.util.Enumeration;//clase para recibir los nombres de los parametros iniciales
import java.util.Hashtable;//clase para poder guardar los usuarios de prueba <clave,valor> 


/*vienen con la creacion de la clase servlet*/
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginUsr
 */
@WebServlet(description = "servlet para realizar validacion del login", 
		urlPatterns = { "/loginUsr" }, 
		initParams = { 	@WebInitParam(name="sergio", value="123"),@WebInitParam(name="oriana", value="456"),@WebInitParam(name="jeff", value="789"),@WebInitParam(name="jose", value="147") 	} 
)

public class LoginUsr extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	private String datos;// datos que tomo cuando me logueo
	private String[] palabras;//arreglo de string

	/*tabla hash que pide el enunciado*/
	private Hashtable<String, String> logins;
	private Usuario usr;
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	/* ANTES DE TOCAR ESTE MODULO LEER ESTE LOG
	 * hay que ver como sacar el null
	 * NOTA: EL PRIMERO INDICE SIEMPRE ES NULL
	 * por lo tanto para poder usar los nombres de usuario se necesita hacer palabras[indice] --> este sera el campo clave de la tabla hash
	 * luego para acceder al valor usaremos config.getInitParameter(name) donde name es --> palabras[indice] como resultado retornará la contraseña para el campo valor de la tabla hash 
	 * */
	public void init(ServletConfig config) throws ServletException {
		logins=new Hashtable<String, String>();
		/*este for es medio raro el primer parametro es como el de un for each el segundo parametro es una condicion de corte, este for sirve para este tipo de interfaces */
		for (Enumeration<String> e = config.getInitParameterNames(); e.hasMoreElements();)
	    datos+=" "+e.nextElement();//se carga datos con una cadena de string donde los nombres estan separados por " "
		palabras = datos.split(" ");//resultado del split ["null","sergio","oriana"]
		for (int i = 1; i < palabras.length; i++) {
			logins.put(   palabras[i] ,  config.getInitParameter(palabras[i])  );//parametros(clave , valor)
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		Enumeration<String> llaves = logins.keys(); //ACA ESTAN LAS CLAVES {sergio,oriana.jose}

		String url = "login.html";
		while (llaves.hasMoreElements()) {
			String aux = llaves.nextElement().toString();// de vuelve una clave como string ej: "sergio"
		    if(  aux.equals(request.getParameter("userName")) == true  ) {
		    	if( logins.get(aux).toString().equals(request.getParameter("Pass")) == true ) {
		    	/*USUARIO VALIDO*/
		    		/*RECUPERAMOS LOS DATOS DE USUARIO DE LA PETICION Y LAS GUARDAMOS EN VARIABLES TEMPORALES*/
		    		String nomU=request.getParameter("userName");
		    		String con=request.getParameter("Pass");
		    		String nom=request.getParameter("firtsName");
		    		String ape=request.getParameter("lastName");
		    		String pos=request.getParameter("postal");
		    		
		    		/*ESTE PASO ES IMPORTANTE PARA PODER COMUNICAR LOS DATOS CON OTRO SERVLET*/
		    		HttpSession sesion = request.getSession(true);	// RECUPERAMOS LA SESSION
		    		
		    		if (sesion.getAttribute("usuario") == null) {
		    			/*LA SESION ES NUEVA ENTONCES SE CREA UNA INSTANCIA DE USUARIO CON LOS DATOS OBTENIDOS*/
		    			usr = new Usuario(nomU, con, nom, ape, pos);
		    		} else usr = (Usuario) sesion.getAttribute("usuario");//SI EXISTE LA SESION ENTONCES SE RECUPERA EL OBJETO USUARIO DE LA SESION
		    		
		    		sesion.setAttribute("usuario", usr);
		    		url = "Productos";
			    }
		    }
		}
		response.sendRedirect(url);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
