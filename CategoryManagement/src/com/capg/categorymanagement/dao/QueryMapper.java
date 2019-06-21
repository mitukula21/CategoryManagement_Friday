package com.capg.categorymanagement.dao;

public interface QueryMapper  
{
	public static final String LISTING_DETAILS_QUERY="select * from categorymanagement";
	public static final String CREATE_CATEGORY_QUERY="Insert into categorymanagement values(category_id_seq.nextval,?)";
	public static final String CHECKING_THE_CATEGORY_QUERY="select category_name from categorymanagement where category_name=?";
	public static final String DISPLAYING_CATEGORY_ID_QUERY="select category_id_seq.currval from dual";
	public static final String EDITING_CATEGORY_QUERY="update categorymanagement set category_name=? where category_name=?";
	public static final String DISPLAYING_ID="select category_id from categorymanagement where category_name=?";
	public static final String DELETING_CATEGORY_QUERY="delete  from categorymanagement where category_name=?";
	public static final String CHECKING_ADMIN_EMAIL_QUERY="select admin_email from admin_details where admin_email=?";
	public static final String CHECKING_ADMIN_PASSWORD_QUERY="select admin_password from admin_details where admin_email=?";
}
