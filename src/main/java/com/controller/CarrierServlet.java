package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CarrierDAO;
import com.model.Carrier;

/**
 * Servlet implementation class CarrierServlet
 */
@WebServlet("/CarrierServlet")
public class CarrierServlet extends HttpServlet {

    private CarrierDAO carrierDAO;

    @Override
    public void init() throws ServletException {
        carrierDAO = new CarrierDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action.equals("searchCarrier")) {
            searchCarrier(request, response);
        } else if (action.equals("editCarrier")) {
            editCarrier(request, response);
        } else if (action.equals("deleteCarrier")) {
            deleteCarrier(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Fetch carrier list and send to JSP
    	List<Carrier> carrierList = carrierDAO.getAllCarriersNames();
        
        // Set the list as a request attribute to be accessible in JSP
    	request.setAttribute("carriers", carrierList);
        
        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("editCarrier.jsp");
        dispatcher.forward(request, response);
    }

    private void searchCarrier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carrierName = request.getParameter("carrierName");
        Carrier carrierDetails = carrierDAO.getCarrierByName(carrierName);

        request.setAttribute("carrierDetails", carrierDetails);
        List<Carrier> carrierList = carrierDAO.getAllCarriers();
        request.setAttribute("carrierList", carrierList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("editCarrier.jsp");
        dispatcher.forward(request, response);
//        doGet(request, response);
    }

    private void editCarrier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carrierName = request.getParameter("carrierName");

        Carrier carrier = new Carrier();
        carrier.setCarrierName(carrierName);
        carrier.set_30DaysAdvanceBooking(Integer.parseInt(request.getParameter("30DaysAdvanceBooking")));
        carrier.set_60DaysAdvanceBooking(Integer.parseInt(request.getParameter("60DaysAdvanceBooking")));
        carrier.set_90DaysAdvanceBooking(Integer.parseInt(request.getParameter("90DaysAdvanceBooking")));
        carrier.setBulkBooking(Integer.parseInt(request.getParameter("bulkBooking")));
        carrier.setSilverUser(Integer.parseInt(request.getParameter("silverUser")));
        carrier.setGoldUser(Integer.parseInt(request.getParameter("goldUser")));
        carrier.setPlatinumUser(Integer.parseInt(request.getParameter("platinumUser")));
        carrier.set_2DaysBeforeTravelDate(Integer.parseInt(request.getParameter("2DaysBeforeTravelDate")));
        carrier.set_10DaysBeforeTravelDate(Integer.parseInt(request.getParameter("10DaysBeforeTravelDate")));
        carrier.set_20DaysOrMoreBeforeTravelDate(Integer.parseInt(request.getParameter("20DaysOrMoreBeforeTravelDate")));
        carrier.setCarrierStatus(request.getParameter("status"));

        carrierDAO.updateCarrier(carrier);
        System.out.println("Edit called!");

        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCarrier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carrierName = request.getParameter("carrierName");

        carrierDAO.deleteCarrier(carrierName);

        request.setAttribute("acknowledgmentMessage", "Carrier deleted successfully!");
        request.setAttribute("carrierDetails", null);

        List<Carrier> carrierList = carrierDAO.getAllCarriers();
        request.setAttribute("carrierList", carrierList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHome.jsp");
        dispatcher.forward(request, response);
    }
    
    public List<Carrier> doGetName() throws ServletException, IOException {
    	try {
    		System.out.println("Try to connect!!");
			carrierDAO.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Carrier> carrierList = carrierDAO.getAllCarriers();
        return carrierList;
    }
}
