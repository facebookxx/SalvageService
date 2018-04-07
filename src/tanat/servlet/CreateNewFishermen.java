package tanat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tanat.beans.Fishermen;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;
 
@WebServlet(urlPatterns = { "/newfishermen" })
public class CreateNewFishermen extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateNewFishermen() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/fishermenlist.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        
		try {
			conn = MySQLConnUtils.getMySQLConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}
        
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
	
        Fishermen fishermen = new Fishermen(name, surname, 1, false);
 
        String errorString = null;
 
            try {
                DBUtils.insertFishermen(conn, fishermen);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }

        request.setAttribute("errorString", errorString);
        request.setAttribute("fishermen", fishermen);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/fishermenlist.jsp");
            dispatcher.forward(request, response);
        }

        else {
            response.sendRedirect(request.getContextPath() + "/fishermenlist");
        }
    }
 
}