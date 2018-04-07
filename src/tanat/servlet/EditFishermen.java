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
 
@WebServlet(urlPatterns = { "/editfishermen" })
public class EditFishermen extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditFishermen() {
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
        
		String errorString = null;
		String button = (String) request.getParameter("button");
		String name = (String) request.getParameter("name");
		int numSave = Integer.parseInt(request.getParameter("numSave"));
		boolean location = false;
		System.out.println(button);
        
		if (button.equals("insee")) {
			location = true;
        } else if (button.equals("save")) {
        	numSave++;
        } else if (button.equals("")) {
        	try {
                DBUtils.deleteFishermen(conn, name);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        System.out.println("Имя рыбака которого спасли уже " + numSave + " раз: " + name);
	
        Fishermen fishermen = new Fishermen(name, numSave);        
 
            try {
                DBUtils.updateFishermen(conn, fishermen, location);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }

        request.setAttribute("errorString", errorString);
        request.setAttribute("fishermen", fishermen);

        if (errorString != null) {
        	System.out.println(errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/fishermenlist.jsp");
            dispatcher.forward(request, response);
        }

        else {
            response.sendRedirect(request.getContextPath() + "/fishermenlist");
        }
    }
 
}