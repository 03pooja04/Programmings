package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseDAO {
	private static Connection connection=CreateConnection.createConnection();
	
	private static String addIncome="insert into expensemanager values(?,?,?,?,?,?,?,?,?,?,?)";
	private static String totalIncome="SELECT total_income,balance,total_expense FROM expensemanager ORDER BY id DESC LIMIT 1;";
	private static String display ="SELECT total_income,total_expense,balance FROM expensemanager WHERE payment_date BETWEEN ? AND ? ORDER BY id DESC LIMIT 1;";
	private static double income=0,balance=0,expense;
	private void fetchTotalIncomeAndBalance() {
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(totalIncome);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				income=rs.getDouble(1);
				balance=rs.getDouble(2);
				expense=rs.getDouble(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	public void addIncome(ExpenseDto dto) {
		fetchTotalIncomeAndBalance();
		try {
			PreparedStatement pstmt = connection.prepareStatement(addIncome);
			pstmt.setInt(1,dto.getId());
			pstmt.setDouble(2, dto.getIncome());
			pstmt.setDouble(3, dto.getExpense());
			pstmt.setString(4, dto.getCategory());
			pstmt.setString(5, dto.getPaymentMethod());
			pstmt.setString(6, dto.getNote());
			pstmt.setString(7,dto.getPaymentDate() );
			pstmt.setString(8, dto.getPaymentTime());
			pstmt.setDouble(9, dto.getIncome()+income);
			pstmt.setDouble(10, dto.getExpense()+expense);
			pstmt.setDouble(11, dto.getIncome()+balance);
			
			
	        int count=pstmt.executeUpdate();
	        System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void addExpense(ExpenseDto dto) {
		fetchTotalIncomeAndBalance();
			try {
				PreparedStatement pstmt = connection.prepareStatement(addIncome);
				pstmt.setInt(1,dto.getId());
				pstmt.setDouble(2, dto.getIncome());
				pstmt.setDouble(3, dto.getExpense());
				pstmt.setString(4, dto.getCategory());
				pstmt.setString(5, dto.getPaymentMethod());
				pstmt.setString(6, dto.getNote());
				pstmt.setString(7,dto.getPaymentDate() );
				pstmt.setString(8, dto.getPaymentTime());
				pstmt.setDouble(9, dto.getIncome()+income);
				pstmt.setDouble(10, dto.getExpense()+expense);
				pstmt.setDouble(11, income-(expense+dto.getExpense()));
				
		        int count=pstmt.executeUpdate();
		        System.out.println(count);
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		}
	public ExpenseDto displayData(ExpenseDto dto) {
		ExpenseDto temp=null;
		try {
			PreparedStatement pstmt = connection.prepareStatement(display);
			pstmt.setString(1, dto.getStartPoint());
			pstmt.setString(2, dto.getEndPoint());
			
			ResultSet rs=pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	temp=new ExpenseDto();
	        	temp.setTotalIncome(income);
	        	temp.setTotalExpense(expense);
	        	temp.setBalance(balance);
	        }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return temp;
	}
	}
