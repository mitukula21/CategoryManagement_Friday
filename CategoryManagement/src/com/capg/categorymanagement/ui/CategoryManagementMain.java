package com.capg.categorymanagement.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.exception.CategoryManagementException;
import com.capg.categorymanagement.service.ServiceClass;

public class CategoryManagementMain {

	public static void main(String[] args) throws Exception {

		/*
		 * ServiceClass sc= new ServiceClass(); Bean bean=new Bean(); boolean
		 * emailcheckflag=false; boolean passwordcheck=false; boolean em=false;
		 */
		/*
		 * do { System.out.println("Enter the Email"); String admin_email = s.next();
		 * bean.setAdmin_email(admin_email); emailcheckflag=sc.emailpattern(bean);
		 * 
		 * em=sc.emailcheck(bean); System.out.println(em); } while(!emailcheckflag ||
		 * !em);
		 * 
		 * 
		 * boolean ps=false; do { System.out.println("Enter the password"); String
		 * admin_password=s.next(); bean.setAdmin_password(admin_password);
		 * passwordcheck=sc.passwordpattern(bean);
		 * 
		 * ps=sc.passwordcheck(bean);
		 * 
		 * } while(!passwordcheck || !ps);
		 */

		ServiceClass service = new ServiceClass();
		try {
			System.out.println("asa");

			int option = 0;
			boolean bc = true;
			boolean continueValue = false;
			String continueChoice = null;
			do {
				do {
					System.out.println("   CATEGORY MANAGEMENT ");

					Bean bean = new Bean();
					System.out.println("_______________________________\n");

					System.out.println("1.Listing Categories ");
					System.out.println("2.Creating Category");
					System.out.println("3.Editing Category");
					System.out.println("4.Deleting Category");
					System.out.println("________________________________");
					System.out.println("Select an option:");

					try {
						Scanner scanner = new Scanner(System.in);
						option = scanner.nextInt();

						switch (option) {

						case 1:
							System.out.println("Listing ");

							try {
								List<Bean> list = service.takeDetails();
							} catch (CategoryManagementException e) {
								System.err.println(e.getMessage());
							}
							continueValue = true;
							break;

						case 2:
							boolean b = false;

							do {
								try {

									System.out.println("Enter the category Name");
									String cat = scanner.next();
									// ServiceClass sc2= new ServiceClass();
									service.validateName(cat);
									b = true;
									bean.setCategory_name(cat);
									b = service.createCategory(cat);
								} catch (CategoryManagementException e) {
									System.err.println(e.getMessage());
								}
							} while (!b);
							continueValue = true;
							break;
						case 3:
							System.out.println("Editing ");

							boolean b1 = false;
							do {
								System.out.println("Enter the name of the category to Edit");
								try {

									String cat = scanner.next();
									// ServiceClass sc= new ServiceClass();
									service.validateName(cat);
									b1 = true;
									bean.setCategory_name(cat);
								} catch (CategoryManagementException e) {
									System.err.println(e.getMessage());
								}
							} while (!b1);
							// ServiceClass sc3= new ServiceClass();
							service.editCategory(bean);
							continueValue = true;
							break;

						case 4:
							System.out.println("deleting ");// ServiceClass sc4= new ServiceClass();
							service.deleteCategory();
							continueValue = true;
							break;
						case 5:

							System.exit(0);
							continueValue = true;
							break;
						default:
							System.out.println("Enter only digits");
							continueValue = true;
							break;

						}

					} catch (InputMismatchException e) {

						System.out.println("Input Mismatch ");
						bc = false;
						// throw new CategoryManagementException("Input Mismatch");

					}
				} while (!bc);

				do {
					Scanner scanner = new Scanner(System.in);

					System.out.println("do you want to continue again [yes/no]");
					continueChoice = scanner.nextLine();
					if (continueChoice.equalsIgnoreCase("yes")) {
						continueValue = true;
						break;
					} else if (continueChoice.equalsIgnoreCase("no")) {
						System.out.println("thank you");
						continueValue = false;
						break;
					} else {
						System.out.println("enter yes or no");
						continueValue = false;
						continue;
					}
				} while (!continueValue);
			} while (continueValue);
		} catch (StackOverflowError e) {
			System.out.println(e);
		}
	}
}
