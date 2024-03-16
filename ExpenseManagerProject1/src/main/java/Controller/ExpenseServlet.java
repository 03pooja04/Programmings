package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ExpenseDAO;
import Model.ExpenseDto;
import Model.ServiceClass;
@WebServlet (urlPatterns={"/addIncome","/addExpense"})
public class ExpenseServlet extends HttpServlet{
	private ExpenseDAO dao=new ExpenseDAO();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		
		switch(path) {
		case"/addIncome":
			addIncome(req,resp);
			displayData(req,resp);
			break;
		case"/addExpense":
			addExpense(req,resp);
			displayData(req,resp);
			break;
		case"/index":
			displayData(req,resp);
			break;
		}
		
	}

	private void displayData(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		ServiceClass s1=new ServiceClass();
		String startPoint=s1.startPoint();
		String endPoint=s1.endPoint();
		
		String temp1=req.getParameter("startDate");
		String temp2=req.getParameter("endDate");
		if(temp1!=null&& temp2!=null) {
			startPoint=temp1;
			endPoint=temp2;
		}
		
		ExpenseDto dto=new ExpenseDto();
		dto.setStartPoint(startPoint);
		dto.setEndPoint(endPoint);
		
		ExpenseDto data=dao.displayData(dto);
		req.setAttribute("DATA", data);
		
		try {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addExpense(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		double expenseAmount=Double.parseDouble(req.getParameter("expenseAmount"));
		String category=req.getParameter("expenseCategory");
		String expenseType=req.getParameter("expenseType");
		String notes=req.getParameter("expenseNote");
		String expenseDate=req.getParameter("expenseDate");
		String expenseTime=req.getParameter("expenseTime");
		
		ExpenseDto dto = new ExpenseDto();
		dto.setExpense(expenseAmount);
		dto.setCategory(category);
		dto.setPaymentMethod(expenseType);
		dto.setNote(notes);
		dto.setPaymentDate(expenseDate);
		dto.setPaymentTime(expenseTime);
		
		dao.addExpense(dto);
		
	}

	private void addIncome(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String amount=req.getParameter("incomeAmount");
		String incomeCategory=req.getParameter("incomeCategory");
		String incomeType=req.getParameter("incomeType");
		String incomeNote=req.getParameter("incomeNote");
		String incomeDate=req.getParameter("incomeDate");
		String incomeTime=req.getParameter("incomeTime");
		
		double incomeAmount=Double.parseDouble(amount);
		
		ExpenseDto dto=new ExpenseDto();
		dto.setIncome(incomeAmount);
		dto.setCategory(incomeCategory);
		dto.setPaymentMethod(incomeTime);
		dto.setNote(incomeNote);
		dto.setPaymentDate(incomeTime);
		dto.setPaymentTime(incomeTime);
	}

}
