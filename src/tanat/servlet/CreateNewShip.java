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
 
import tanat.beans.Ship;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;
 
@WebServlet(urlPatterns = { "/newship" })
public class CreateNewShip extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateNewShip() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/ship.jsp");
        dispatcher.forward(request, response);
    }
 
    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
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
        
		int id = Integer.parseInt((request.getParameter("id")));
        String name = (String) request.getParameter("name");
        int numPas = Integer.parseInt((request.getParameter("numPas")));
        boolean location = false;
	
        Ship ship = new Ship(id, name, numPas, location);
 
        String errorString = null;
 
       try {
		DBUtils.insertShip(conn, ship);
	} catch (SQLException e) {
		// TODO Автоматически созданный блок catch
		e.printStackTrace();
		errorString = e.getMessage();
	}

        request.setAttribute("errorString", errorString);
        request.setAttribute("ship", ship);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/ship.jsp");
            dispatcher.forward(request, response);
        }

        else {
            response.sendRedirect(request.getContextPath() + "/shiplist");
        }
    }
 
}