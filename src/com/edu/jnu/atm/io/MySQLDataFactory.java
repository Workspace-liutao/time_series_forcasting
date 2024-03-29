package com.edu.jnu.atm.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.mysql.jdbc.Statement;

public class MySQLDataFactory implements DataFactory
{
	@Override
	public double getSourceData (String DEV_CODE, Calendar TRANS_DATE) 
	{
		/**
		 * DEV_CODE,TRANS_DATE as input, value of the source data as output
		 */
		double SqlResult = 0;
		
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");			
		String TRNS_DATE = dateformat.format(TRANS_DATE.getTime());
		
		Connection con = null;
		java.sql.Statement pre = null;
		ResultSet result = null;
		String sql = "SELECT WITHDRAW FROM guangfa WHERE TRNS_DATE = " + TRNS_DATE;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ATM?autoReconnect=true&useSSL=false";
			String user = "root";
			String password = "administrator";
			con = DriverManager.getConnection(url,user,password);
			pre = con.createStatement();
			result = pre.executeQuery(sql);
			while (result.next()) { 
				SqlResult = result.getDouble(1);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while interact with Database!");
		}
		finally 
		{
			try 
			{
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}		
		return SqlResult;
	}
}
