package com.capg.categorymanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.connection.DbConnection;
import com.capg.categorymanagement.exception.CategoryManagementException;
import com.capg.categorymanagement.service.ServiceClass;

public class DaoClass implements DaoInterface {
	Bean bean = new Bean();
    ServiceClass service= new ServiceClass();
	Logger logger = Logger.getRootLogger();

	Connection con = null;
	PreparedStatement ps = null;

	public DaoClass() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	public List<Bean> takeDetails() throws CategoryManagementException {
		
		con = DbConnection.getConnection();
		List<Bean> list = new ArrayList<>();

		ResultSet rs = null;

		try {
			ps = con.prepareStatement(QueryMapper.LISTING_DETAILS_QUERY);
			rs = ps.executeQuery();
			while (rs.next()) {
				Bean bean = new Bean();
				bean.setCategory_id(rs.getInt(1));
				bean.setCategory_name(rs.getString(2));
				list.add(bean);
			}
		} catch (SQLException e) {
			throw new CategoryManagementException("problem occured while creating ps");
		}
		return list;
	}

	public boolean createCategory(String cat) throws CategoryManagementException {
		// TODO Auto-generated method stub
		con = DbConnection.getConnection();
		boolean b;
		Bean bean = new Bean();
		try {
			ResultSet rs = null;
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			//con = db.getConnection();

			ps = con.prepareStatement(QueryMapper.CREATE_CATEGORY_QUERY);
			ps.setString(1, cat);
			int rs1 = ps.executeUpdate();
			System.out.println(cat);

			if (rs1 == 1) {

				System.out.print("A new Category is created with category_id ");

				ps1 = con.prepareStatement(QueryMapper.DISPLAYING_CATEGORY_ID_QUERY);
				rs = ps1.executeQuery();
			}

			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}

			b = true;
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Already existing category");
			b = false;

		}
		return b;

	}

	public void editCategory(Bean bean) throws CategoryManagementException {
		con = DbConnection.getConnection();
		String a = null, b = null;
		boolean b1 = true;

		try {

			
			ResultSet rs = null;
			ResultSet rs1 = null, rs2 = null;
			PreparedStatement ps = null;
			PreparedStatement ps1 = null, ps2 = null;

			Scanner scanner = new Scanner(System.in);
			ps = con.prepareStatement(QueryMapper.CHECKING_THE_CATEGORY_QUERY);
			ps.setString(1, bean.getCategory_name());
			rs = ps.executeQuery();
			String cat = null;
			if (!rs.next()) {
				System.out.println("this category is not existing");
				b1 = false;
			}

			if (b1) {
				boolean b2 = false;
				do {
					System.out.println("Enter the name of the category to Edit");
					try {

						cat = scanner.next();
						service.validateName(cat);
						b2 = true;

					} catch (CategoryManagementException e) {
						System.err.println(e.getMessage());
					}
				} while (!b2);
				ps1 = con.prepareStatement(QueryMapper.EDITING_CATEGORY_QUERY);
				ps1.setString(1, cat);
				ps1.setString(2, bean.getCategory_name());
				rs1 = ps1.executeQuery();
				System.out.println("The Category name is successfully edited");
				ps2 = con.prepareStatement(QueryMapper.DISPLAYING_ID);
				ps2.setString(1, b);
				rs2 = ps2.executeQuery();
				while (rs2.next()) {
					System.out.printf("The category id is %d", rs2.getInt(1));
				}
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("The name of the category is already existing");

		}

	}

	public void deleteCategory() throws CategoryManagementException {
		
		con = DbConnection.getConnection();
		boolean b1 = true;
        
		try {
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter the name of the category to delete");
			String a = scanner.next();
			PreparedStatement ps = con.prepareStatement(QueryMapper.CHECKING_THE_CATEGORY_QUERY);
			ps.setString(1, a);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("this category is not existing");
				b1 = false;
			}

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			if (b1) {
				PreparedStatement ps1 = con.prepareStatement(QueryMapper.DELETING_CATEGORY_QUERY);
				ps1.setString(1, a);
				ResultSet rs1 = ps1.executeQuery();
				System.out.println("The category is deleted successfully");
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean admin_email(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		con = DbConnection.getConnection();
		boolean b1 = false;

		try {
			

			PreparedStatement ps = con.prepareStatement(QueryMapper.CHECKING_ADMIN_EMAIL_QUERY);
			ps.setString(1, bean.getAdmin_email());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b1 = true;
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b1;

	}

	public boolean admin_password(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		con = DbConnection.getConnection();
		boolean b1 = false;

		try {
			
			PreparedStatement ps = con.prepareStatement(QueryMapper.CHECKING_ADMIN_PASSWORD_QUERY);
			ps.setString(1, bean.getAdmin_email());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				b1 = true;
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b1;
	}

}
