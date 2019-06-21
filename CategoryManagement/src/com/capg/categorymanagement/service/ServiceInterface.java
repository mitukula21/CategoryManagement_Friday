package com.capg.categorymanagement.service;

import java.util.List;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.exception.CategoryManagementException;

public interface ServiceInterface {

	List<Bean> takeDetails() throws CategoryManagementException;

	boolean createCategory(String cat) throws CategoryManagementException;

	void editCategory(Bean bean) throws CategoryManagementException;

	void deleteCategory() throws CategoryManagementException;

	public boolean emailpattern(Bean bean)  throws CategoryManagementException;

	public boolean passwordpattern(Bean bean) throws CategoryManagementException;

	public boolean emailcheck(Bean bean) throws CategoryManagementException;

	public boolean passwordcheck(Bean bean) throws CategoryManagementException;

	public void validateName(String name) throws CategoryManagementException;
}
