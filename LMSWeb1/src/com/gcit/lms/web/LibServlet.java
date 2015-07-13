package com.gcit.lms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.*;
import com.gcit.lms.service.AdministrativeService;
import com.gcit.lms.service.LibrarianService;

@WebServlet({ "/editLibBranch", "/libAddCopies"})
public class LibServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LibServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		
		case "/editLibBranch":
			editLib(request, response);
			break;
		case "/libAddCopies":
			libAdd(request, response);
			break;
		
		}
}
	private void libAdd(HttpServletRequest request, HttpServletResponse response) {
		
		
	}
	private void editLib(HttpServletRequest request,
			HttpServletResponse response) {
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		Branch br = new Branch();
		br.setBranchName(branchName);
		br.setBranchId(branchId);
		LibrarianService libService = new LibrarianService();
		try {
			libService.upLibBranch(br);
			request.setAttribute("result", "Branch updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Branch update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/librarian.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
