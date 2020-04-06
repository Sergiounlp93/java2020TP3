package misServlets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InicializaStock
 *
 */
@WebListener
public class InicializaStock implements ServletContextListener {

    /**
     * Default constructor. 
     */

    public InicializaStock() {
        // Inicializando stock!
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // ACA HAY QUE HACER ALGO
    	ServletContext contexto=sce.getServletContext();
       	//CONSTRUYO UNA TABLA HASH
        ArrayList<Golosina> listaGolosina = new ArrayList<Golosina>();	
    	
    	BufferedReader cat=null;
    	Hashtable<String, String> catalogo=new Hashtable<String, String>();
    	try {
    		InputStream archCatalogo= contexto.getResourceAsStream("/WEB-INF/recursos/stock.txt"); 
	    	cat=new BufferedReader (new InputStreamReader(archCatalogo));
	    	int posComa=0;
	    	String linea = cat.readLine();
	    	while (linea != null) {
		    	posComa = linea.indexOf(",");//me quedo con el dato
		    	String nom = linea.substring(0, posComa);//antes de la coma
		    	String precio = linea.substring(posComa + 1);//despues de la coma
		    	//Agrego golosina a la tabla
		    	catalogo.put(nom, precio);
		    	//catalogo.forEach(  (k, v) -> System.out.println("Key : " + k + ", Value : " + v)      ); 
		        
		    	linea = cat.readLine();//leo la siguiente linea
	    	}
	    	// Parsea el archivo y construye una lista de productos en el objeto catalogo
	    	/*
	    	contexto.setAttribute("stock",catalogo);
	    	contexto.log("lista de Productos creada ");
	    	*/
			catalogo.forEach(  (k, v) ->  {  listaGolosina.add(new Golosina(k, Float.parseFloat(v) ));  });
			
			contexto.setAttribute("Lista", listaGolosina);
	    	
	    	}catch(Exception e) {contexto.log("Ocurrio una excepcion mientras se abria el archivo",e);
	    	}finally{
		    	if (cat!=null) {
		    	try{cat.close();} catch(Exception e) {}
	    	}
    	}
    }
	
}
