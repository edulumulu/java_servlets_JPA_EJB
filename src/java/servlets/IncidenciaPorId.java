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
@WebServlet(name = "IncidenciaPorId", urlPatterns = {"/IncidenciaPorId"})
public class IncidenciaPorId extends HttpServlet {

    @EJB
    IncidenciasEJB iEJB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Detalle de Incidencia</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/detalleIncidenciaCSS.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='contenedor'>");

            String id_string = request.getParameter("id_incidencia");

            try {
                int id_int = Integer.parseInt(id_string);

                if (iEJB == null) {
                    throw new ServletException("EJB no encontrado");
                }

                for (int i = 0; i < lista_incidencias.size(); i++) {

                    String estado_string = "";
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Incidencias inc = lista_incidencias.get(i);

                    if (inc.getEstado() == ('U')) {
                        estado_string = "Urgente";
                    } else if (inc.getEstado() == ('N')) {
                        estado_string = "Normal";
                    }

                    if (inc != null) {
                        out.println("<h1>Detalle de la Incidencia</h1>");
                        out.println("<table>");
                        out.println("<tr><th>Campo</th><th>Valor</th></tr>");
                        out.println("<tr><td>ID</td><td>" + inc.getIdIncidencia() + "</td></tr>");
                        out.println("<tr><td>Origen</td><td>" + inc.getIdEmpleadoOrigen().getNombreUsuario() + "</td></tr>");
                        out.println("<tr><td>Destino</td><td>" + inc.getIdEmpleadoDestino().getNombreUsuario() + "</td></tr>");
                        out.println("<tr><td>Descripción</td><td>" + inc.getDescripcion() + "</td></tr>");
                        out.println("<tr><td>Fecha</td><td>" + formato.format(inc.getFecha()) + "</td></tr>");
                        out.println("<tr><td>Urgencia</td><td>" + estado_string + "</td></tr>");
                        out.println("</table>");
                    } else {
                        out.println("<p>No existe una incidencia con ese ID.</p>");
                    }

                }catch (NumberFormatException e) {
                out.println("<p class='error'>El campo ID debe rellenarse con un número entero.</p>");
            }

                out.println("<form action=\"IncidenciaPorId.html\" method=\"POST\">");
                out.println("<input type=\"submit\" name=\"volver\" value=\"Volver\" class=\"boton\"/>");
                out.println("</form>");

                out.println("</div>");
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
