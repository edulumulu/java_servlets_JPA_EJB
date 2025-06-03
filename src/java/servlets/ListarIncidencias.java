/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.IncidenciasEJB;
import clases.Empleados;
import clases.Incidencias;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edulumulu
 */
@WebServlet(name = "ListarIncidencias", urlPatterns = {"/ListarIncidencias"})
public class ListarIncidencias extends HttpServlet {

    @EJB
    IncidenciasEJB iEJB;

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
            List<Incidencias> lista_incidencias = iEJB.listarIncidencias();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Incidencias</title>");

            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estiloTablasCSS.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de Incidencias</h1>");

            if (lista_incidencias.isEmpty()) {
                out.println("<p>No hay incidencias registradas.</p>");
            } else {
                out.println("<table>");
                out.println("<tr>");
                //ut.println("<th>#</th>");
                out.println("<th>ID</th>");
                out.println("<th>Origen</th>");
                out.println("<th>Destino</th>");
                out.println("<th>Descripción</th>");
                out.println("<th>Fecha</th>");
                out.println("<th>Estado</th>");
                out.println("</tr>");

                for (int i = 0; i < lista_incidencias.size(); i++) {

                    String estado_string = "";
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Incidencias inc = lista_incidencias.get(i);

                    if (inc.getEstado() == ('U')) {
                        estado_string = "Urgente";
                    } else if (inc.getEstado() == ('N')) {
                        estado_string = "Normal";
                    }

                    out.println("<tr>");
                    //out.println("<td>" + (i + 1) + "</td>");
                    out.println("<td>" + inc.getIdIncidencia() + "</td>");
                    out.println("<td>" + inc.getIdEmpleadoOrigen().getNombreUsuario() + "</td>");
                    out.println("<td>" + inc.getIdEmpleadoDestino().getNombreUsuario() + "</td>");
                    out.println("<td>" + inc.getDescripcion() + "</td>");
                    out.println("<td>" + formato.format(inc.getFecha()) + "</td>");
                    out.println("<td>" + estado_string + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }

            out.println("<form action=\"index2.html\" method=\"POST\">");
            out.println("<input type=\"submit\" name=\"volver\" value=\"Volver\" class=\"button\" />");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }
    }

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//             /* TODO output your page here. You may use following sample code. */
//            List<Incidencias> lista_incidencias = iEJB.listarIncidencias();
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PruebaServlet</title>");            
//            out.println("<link rel=\"stylesheet\" type=\"text/cs\" href=\"css/estilos.css\">");
//            out.println("</head>");
//            out.println("<body>");
//            //out.println("<h1>Servlet PruebaServlet at " + "Me huelen los pies\n" +request.getContextPath() + "</h1>");
//            for(int i=0; i<lista_incidencias.size();i++){
//                out.print("<b>"+(i+1)+" --> Id: </b>" + 
//                        lista_incidencias.get(i).getIdIncidencia() + 
//                        ", <b>Origen: </b>" + 
//                        lista_incidencias.get(i).getIdEmpleadoOrigen().getNombreUsuario() +
//                        ", <b>Destino: </b>" + 
//                        lista_incidencias.get(i).getIdEmpleadoDestino().getNombreUsuario()+
//                        ", <b>Descripcion: </b>" + 
//                        lista_incidencias.get(i).getDescripcion()+
//                         ", <b>Fecha: </b>" + 
//                        lista_incidencias.get(i).getFecha()+
//                                ", <b>Urgencia: </b>" + 
//                        lista_incidencias.get(i).getEstado() + "<br>");
//            }
//            
//            out.println("<form action=\"index2.html\" method=\"POST\">"
//                    + "Volver al menú de Incidencias"
//                    + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
//                    + "</form>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
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
        processRequest(request, response);
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
