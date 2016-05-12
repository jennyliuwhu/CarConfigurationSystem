/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Automobile;
import client.SelectCarOption;

/**
 * Servlet implementation class GetChoices
 */
@WebServlet("/GetChoices")
public class GetChoices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String target = "BasicCarChoice.jsp";
	private int optionSetAmount;
	public GetChoices() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get the selected car name from the AvailableCarChoice form
		String car = request.getParameter("selectCar");

		// get the Automobile from server
		SelectCarOption selectCarOption = new SelectCarOption();
		Automobile auto = selectCarOption.getAuto(car);
		optionSetAmount = auto.getOptionSetAmount();
		// set attributes for target JSP
		request.setAttribute("Make/Model", auto.getMake() + "/" + auto.getModel());
		request.setAttribute("car", car);
		request.setAttribute("optionSetAmount", optionSetAmount);
		for (int i = 0; i < optionSetAmount; i++) {
			String optionSetName = auto.getOptionSetName(i);
			request.setAttribute(Integer.toString(-i-1), optionSetName);
			for (int j = 0; j < auto.getOptionAmount(i); j++) {
				String optionName = auto.getOptionName(i, j);
				request.setAttribute(Integer.toString(i*100+j), optionName);
			}
		}

		// set request dispatcher
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}
	protected int getOptionSetAmount() {
		return optionSetAmount;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
