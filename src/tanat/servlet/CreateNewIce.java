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
 
import tanat.beans.IceFloe;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;
 
@WebServlet(urlPatterns = { "/newice" })
public class CreateNewIce extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateNewIce() {
        super();
    }
 
    // Отобразить страницу создания продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/iceFloeListView.jsp");
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
		IceFloe iceFloe = null;
		String errorString = null;
		int numberOfFishermen = 0;
        String name = (String) request.getParameter("name");
        int size = Integer.parseInt((request.getParameter("size")));
        numberOfFishermen = Integer.parseInt((request.getParameter("numberOfFishermen")));
	
        if(numberOfFishermen <= 0) {
        	errorString = "Не нужно добавлять в базу льдины,\n на которых нет рыбаков";
        	request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
            
        } else {  
            iceFloe = new IceFloe(name, size, numberOfFishermen);      	
            
            try {
                DBUtils.insertIceFloe(conn, iceFloe);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            
            // Сохранить информацию в request attribute перед тем как forward к views.
            request.setAttribute("errorString", errorString);
            request.setAttribute("iceFloe", iceFloe);
     
            // Если имеется ошибка forward (перенаправления) к странице 'edit'.
            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/views/iceFloeListView.jsp");
                dispatcher.forward(request, response);
            }
            // Если все хорошо.
            // Redirect (перенаправить) к странице со списком льдин.
            else {
                
                response.sendRedirect(request.getContextPath() + "/icefloelist");
            }
        }
     }
 }