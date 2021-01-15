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

	public static void main(String[] args) {
		Object companyData[][] = testUtil.getTestData("CompanyNames"); // get companies name via excel sheet 
		List<String> companyList = new ArrayList<String>();
		System.out.println("Available Company Names :");
		for (int rowNum = 0; rowNum < companyData.length; rowNum++) {
			for (int colNum = 0; colNum < companyData[0].length; colNum++) {

				companyList.add((String) companyData[rowNum][colNum]);
				System.out.println(rowNum + 1 + ". " + companyData[rowNum][colNum]); // printing available companies name for choice of selection
			}
		}
		for (int i = 0; i < 3; i++) {
			System.out.println("Your Available Filter Choices Are: ");

			System.out.println("1.By Company Name ");

			System.out.println("2. A -> Z ");

			System.out.println("3. Z -> A ");

			System.out.println("Please Select your Filter Choice by press 1-3 : ");

			try {
				userChoice = scan.nextInt(); // geting user input choice except only numeric
				filterCompanyName(userChoice, companyList); // calling fuction which filtering data
			} catch (Exception e) {
				System.out.println("Exception occures please select valid number\n" + e);

				break;
			}
		}

	}
/**
 * 
 * @param userChoice
 * @param companyList
 * as per user choice and available company list fuction filter data 
 */
	public  static void filterCompanyName(int userChoice, List<String> companyList) {
		switch (userChoice) {
		case 1:
			System.out.println("Please Enter What Company You want to search for display: ");
			String val = scan.next();
			boolean flag = false;
			System.out.println("val is " + val);
			for (int i = 0; i < companyList.size(); i++) {
				if (companyList.get(i).toLowerCase().replace(" ", "").contains(val.toLowerCase().replace(" ", ""))) {
					flag = true;
					System.out.println(companyList.get(i));
				}
			}
			if (flag != true) {
				System.out.println("No Company Available As Per Your Input Selections as :" + val);
			}
			break;
		case 2:
			Collections.sort(companyList, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s1.compareToIgnoreCase(s2);
				}
			});
			for (int i = 0; i < companyList.size(); i++) {
				System.out.println(i + 1 + ". " + companyList.get(i));
			}
			break;
		case 3:
			Collections.sort(companyList, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return -s1.compareToIgnoreCase(s2);
				}
			});
			for (int i = 0; i < companyList.size(); i++) {
				System.out.println(i + 1 + ". " + companyList.get(i));
			}
			break;

		}
	}

}
