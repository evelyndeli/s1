/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author evely
 */
@WebServlet(urlPatterns = {"/Juego"})
public class Juego extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Juego</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Juego at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String CONTENT_TYPE = "text/html";
        String Encabezado = "";
        int numero, i;
        String nombre, num = "Numero";
        response.setContentType(CONTENT_TYPE);
        ServletOutputStream out = response.getOutputStream();
        Integer n = new Integer(0);
        Integer intentos = new Integer(1);
        nombre = request.getParameter("nombre");
        //n = Integer.valueOf(request.getParameter("tunumero"));
        if (nombre == null) {
        nombre = ""; }
        
        // recuperar la sesión
        javax.servlet.http.HttpSession sesion = request.getSession(true);
        if (sesion.isNew()) { // la sesión es nueva

            Encabezado = "Bienvenido"; 
            numero = (int) (Math.random() * 100) + 1;
            sesion.setAttribute("numeroaleat", numero);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Numero Aleatorio");
            out.println("</title>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">");
            out.println("<H1 ALIGN=\"CENTER\">" + Encabezado + "</H1>");
            out.println("<H2 ALIGN=\"CENTER\">Instrucciones del juego:</H2>");
            
            out.println("<TABLE BORDER=1 ALIGN=CENTER>");
            out.println("<IN BGCOLOR=\"#E6E6FA\">");
            out.println("<TH>El adversario escogerá un número entre 1 y 100 y el usuario debe"
                    + " intentar adivinarlo introduciendo un número entre 1 y 100 en la casilla. El adversario"
                    + " le informará de si ha acertado, y si no le dará una pista." + numero);
            out.println("</TABLE>");
            
            out.println("<form id='form1' name='form1' method='post' action='Juego'>");
            out.println("<div>INTRODUZCA UN NÚMERO: </div>");
            out.println("<input type='hidden' name= 'intentos' value='intentos'/>");
            out.println("<input type='hidden' name= 'nombre' value='nombre'/>");
            out.println("<input type='hidden' name= 'numeroaleat' value='numero'/>");
            /*String opcion = "";
            String option = request.getParameter("tunumero");
            for (i = 1; i <= 100; i++)
            {
                opcion =  opcion + "<option value=\""+ i +"\">"+ i +"</option>\n";
            }
            
            out.println("<div ALIGN=\"CENTER\"> <select name=\"tunumero\"> \n"
                    + opcion 
                    + "</select></div>");
            out.println("<br>");
            out.println("<div ALIGN=\"CENTER\"><p>" + option + "</p></div>");*/
            out.println("<input type='number' size='15' maxlength='30' name='tunumero' value= 'n'/>");
            out.println("<input type='submit' name= 'Submit' value='Jugar'/>");
            out.println("</form>");
            out.println("</BODY></HTML>");
            
            sesion.setAttribute("tunumero", n);
            sesion.setAttribute("nombre", nombre);
            sesion.setAttribute("intentos", intentos);
            
            out.close();
        }
        else {
            
            if(Objects.equals((Integer)sesion.getAttribute("tunumero"), (Integer)sesion.getAttribute("numeroaleat"))){
                Encabezado = "¡Felicidades "  + sesion.getAttribute("nombre") + "!"; 
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Numero Correcto");
                out.println("</title>");
                out.println("<H2 ALIGN=\"CENTER\">Intentos = " + (Integer)sesion.getAttribute("intentos") + "</H2>");
                out.println("<BODY BGCOLOR=\"#FDF5E6\">");
                out.println("<H1 ALIGN=\"CENTER\">" + Encabezado + "</H1>");
                out.println("<a href = 'inicio.html'> Ir al enlace </a>");
                out.println("</BODY></HTML>");
            }
            else{
                
                Encabezado = "No has acertado! " + sesion.getAttribute("nombre"); 
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Numero Aleatorio");
                out.println("</title>");
                out.println("<BODY BGCOLOR=\"#FDF5E6\">");
                out.println("<H1 ALIGN=\"CENTER\">" + Encabezado + "</H1>");
                out.println("<H2 ALIGN=\"CENTER\">Pista:</H2>");

                out.println("<TABLE BORDER=1 ALIGN=CENTER>");
                out.println("<IN BGCOLOR=\"#FFAD00\">");
                
                if((Integer)sesion.getAttribute("tunumero") > (Integer)sesion.getAttribute("numeroaleat"))
                    out.println("<TH>El numero escondido es mas pequeño que " + (Integer)sesion.getAttribute("tunumero"));
                else
                    out.println("<TH>El numero escondido es mayor que " + (Integer)sesion.getAttribute("tunumero") + (Integer)sesion.getAttribute("numeroaleat"));
                    
                out.println("</TABLE>");

                out.println("<form id='form1' name='form1' method='post' action='Juego'>");
                out.println("<div>INTRODUZCA UN NÚMERO: </div>");
                out.println("<input type='hidden' name= 'nombre' value='nombre'/>");
                out.println("<input type='number' size='15' maxlength='30' value='tunumero' name= 'tunumero'>");
                out.println("<input type='submit' name= 'Submit' value='Jugar'/>");
                out.println("</form>");
                out.println("</BODY></HTML>");
                
                intentos++;
                
                sesion.setAttribute("intentos", intentos);
                sesion.setAttribute("tunumero", n);
            }
            
        }
             
    }       

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
