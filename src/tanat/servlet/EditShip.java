package tanat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tanat.beans.IceFloe;
import tanat.beans.Ship;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;
 
@WebServlet(urlPatterns = { "/editship" })
public class EditShip extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public int numPas = 0;
    public int id = 0;
 
    public EditShip() {
        super();
    }
 
    // Отобразить страницу редактирования продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

 
		id = Integer.parseInt(request.getParameter("id"));
		String location = request.getParameter("location");
 
		List<IceFloe> listfloe = null;
        Ship ship = null;
        String errorString = null;
 
	    if (location.equals("На суше")) {  
        
        try {
            ship = DBUtils.findShip(conn, id);
            listfloe = DBUtils.findIceFloe(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        // Ошибки не имеются.
        // Продукт не существует для редактирования (edit).
        if (errorString != null && ship == null) {
            response.sendRedirect(request.getServletPath() + "/shiplist");
            return;
        }
        
        numPas = ship.getNumPas();
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("ship", ship);
        request.setAttribute("listfloe", listfloe);
//        request.setAttribute("iceFloe", iceFloe);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/editship.jsp");
        dispatcher.forward(request, response);
        
	    } else {
        	errorString = "Нельзя отправить корабль который уже в море";
        	request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
	    }
 
    }
 
    // После того, как пользователь отредактировал информацию продукта и нажал на Submit.
    // Данный метод будет выполнен.
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
		String button = request.getParameter("button");
		Ship ship = new Ship();
		
		String nameIce = null;
		
		//усли нажали кнопку "Вернулся"
		if (button.equals("shipcencel")) {
			id = Integer.parseInt(request.getParameter("id"));
			ship.setId(id);
			ship.setLocation(false);
			try {
				DBUtils.updateShip(conn, ship);
			} catch (SQLException e) {
				// TODO Автоматически созданный блок catch
				e.printStackTrace();
			}
			
			System.out.println(ship.getId() + ship.getLocation());
			
			//если нажали кнопку "Списать"
        } else if (button.equals("killtask")) {
            try {
            	id = Integer.parseInt(request.getParameter("id"));
				DBUtils.deleteShip(conn, id);
			} catch (SQLException e) {
				// TODO Автоматически созданный блок catch
				e.printStackTrace();
			}
           
            //если нажали кнопку "Отправить"
        }else if (button.equals("togo")) {
        	ship.setId(id);
			ship.setLocation(true);
			//присваиваем кораблю значение "В море"
			try {
				DBUtils.updateShip(conn, ship);
			} catch (SQLException e) {
				// TODO Автоматически созданный блок catch
				e.printStackTrace();
			}
			//проверяем заберет ли корабль всех рыбаков, и если да льдину удаляем из БД
        	nameIce = (String) request.getParameter("nameIce");
        	//вызываем метод, который посчитает сколько рыбаков заберет корабль
        	try {
				DeleteIceFloe.deleteIceFloe(nameIce, numPas);
			} catch (SQLException e) {
				// TODO Автоматически созданный блок catch
				e.printStackTrace();
			}
        	
        }
		
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
 
        // Если имеется ошибка, forward к странице edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/error.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/shiplist");
        }
    }
 
}