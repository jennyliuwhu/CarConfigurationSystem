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

import client.SelectCarOption;
import model.Automobile;

/**
 * Servlet implementation class GetPrices
 */
@WebServlet("/GetPrices")
public class GetPrices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String target = "CarConfigProof.jsp";

	public GetPrices() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String car = request.getParameter("car");

		// get the Automobile from server
		SelectCarOption selectCarOption = new SelectCarOption();
		Automobile auto = selectCarOption.getAuto(car);
		int optionSetAmount = auto.getOptionSetAmount();
		request.setAttribute("optionSetAmount", optionSetAmount);
		float totalPrice = auto.getBasePrice();
		request.setAttribute("basePrice", totalPrice);
		// set attributes for target JSP
		request.setAttribute("Make/Model", auto.getMake() + "/" + auto.getModel());
		for (int i = 0; i < optionSetAmount; i++) {
			String optionSetName = auto.getOptionSetName(i);
			request.setAttribute(Integer.toString(-i-1), optionSetName);

			// get each option's parameter
			int option = Integer.parseInt(request.getParameter(Integer.toString(-i-1)));

			// get name and set attribute
			String name = auto.getOptionName(i, option-i*100);
			request.setAttribute(Integer.toString(2*i), name);

			// get price and set attribute
			float price = auto.getOptionPrice(i, option-i*100);
			request.setAttribute(Integer.toString(2*i+1), price);

			totalPrice += price;
		}
		
		// set attribute for total price
		request.setAttribute("totalPrice", totalPrice);
		// set request dispatcher
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
