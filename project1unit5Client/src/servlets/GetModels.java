/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.SelectCarOption;

/**
 * Servlet implementation class GetModels
 */
@WebServlet("/GetModels")
public class GetModels extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String target = "AvailableCarChoice.jsp";
	private Set<String> set;
	
	public GetModels() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag = 0;
		
		// get available models
		SelectCarOption selectCarOption = new SelectCarOption();
		set = selectCarOption.getSet();
		
		// set attribute for target JSP
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			flag++;
			request.setAttribute(Integer.toString(flag), iterator.next());
		}
		
		// set request dispatcher
		RequestDispatcher requestDispacher = request.getRequestDispatcher(target);
		requestDispacher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
