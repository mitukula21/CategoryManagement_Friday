package com.capg.categorymanagement.dao;

import java.util.List;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.exception.CategoryManagementException;

public interface DaoInterface {

	public List<Bean> takeDetails() throws CategoryManagementException;

	boolean createCategory(String cat) throws CategoryManagementException;

	void editCategory(Bean bean) throws CategoryManagementException;

	public boolean admin_email(Bean bean) throws CategoryManagementException;

	public boolean admin_password(Bean bean) throws CategoryManagementException;

}
