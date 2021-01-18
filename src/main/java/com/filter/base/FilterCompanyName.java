package com.filter.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.filter.util.testUtil;

public class FilterCompanyName {
	public static Scanner scan = new Scanner(System.in);
	public static int userChoice;
	public static int count = 0;

	public static void main(String[] args) {
		List<String> companyList = displayCompanyName(); // get company list
		for (int i = 0; i < 15; i++) { // Ask user 12 times to run all testcases 
			System.out.println("Your Available Filter Choices Are: ");

			System.out.println("1.By Company Name ");

			System.out.println("2. A -> Z ");

			System.out.println("3. Z -> A ");

			System.out.println("Please Select your Filter Choice by press 1-3 : ");

			try {
				userChoice = scan.nextInt(); // geting user input choice accept only numeric
				scan.nextLine();

				if (userChoice == 0 || userChoice > 3) {
					System.out.println(
							userChoice + " is invalid choice ,Please select in range of 1--> 3 for your choice");
				} else if (userChoice <= 3) {
					filterCompanyName(userChoice, companyList); // calling fuction which filtering data
				}

			} catch (Exception e) {
				System.out.println("Exception occures please select valid number\n Run the program again ");

				break;
			}
		}
		scan.close(); // release scanner object
	}

	/**
	 * get companies name via excel sheet and returning list of companies
	 * 
	 * @return
	 */
	public static List<String> displayCompanyName() {
		Object companyData[][] = testUtil.getTestData("CompanyNames");
		List<String> companyList = new ArrayList<String>();
		String s;
		System.out.println("Available Company Names :");
		for (int rowNum = 0; rowNum < companyData.length; rowNum++) {
			for (int colNum = 0; colNum < companyData[0].length; colNum++) {
				s = (String) companyData[rowNum][colNum];
				if (!s.trim().isEmpty()) {
					companyList.add(s.trim());
					System.out.println(rowNum + 1 + ". " + s.trim()); // printing available companies
																		// name for choice of selection
				}
			}

		}
		return companyList;
	}

	/**
	 * 
	 * @param userChoice
	 * @param companyList as per user choice and available company list fuction
	 *                    filter data
	 */
	public static void filterCompanyName(int userChoice, List<String> companyList) {

		switch (userChoice) {
		case 1:
			System.out.println("Please Enter What Company You want to search for display: ");
			String val = scan.nextLine();
			boolean flag = false;
			val = val.toLowerCase().replace(" ", "");
			// System.out.println("val is " + val.replace(" ", ""));
			for (int i = 0; i < companyList.size(); i++) {
				if (companyList.get(i).toLowerCase().replace("\\s+", "").contains(val)) {
					flag = true;
					count = count + 1;
					System.out.println(count + ". " + companyList.get(i));
				}
			}
			count = 0;
			if (flag != true) {
				System.out.println("No Company Available As Per Your Input Selections as :" + val);
			}

			break;
		case 2:
			Collections.sort(companyList, new Comparator<String>() {// sorting string alphabetically
				@Override
				public int compare(String firstValue, String secondValue) {
					return firstValue.compareToIgnoreCase(secondValue);
				}
			});
			for (int i = 0; i < companyList.size(); i++) {
				System.out.println(i + 1 + ". " + companyList.get(i));
			}
			break;
		case 3:
			Collections.sort(companyList, new Comparator<String>() { // sorting string reverse aplhabet
				@Override
				public int compare(String firstValue, String secondValue) {
					return -firstValue.compareToIgnoreCase(secondValue);
				}
			});
			for (int i = 0; i < companyList.size(); i++) {
				System.out.println(i + 1 + ". " + companyList.get(i));
			}
			break;

		}
	}

}
