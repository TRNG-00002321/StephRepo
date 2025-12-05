package com.revature.expense;

import com.revature.expense.model.Expense;
import com.revature.expense.utils.*;
import com.revature.expense.dao.*;
import com.revature.expense.dao.impl.*;
import com.revature.expense.service.*;
import com.revature.expense.service.impl.*;
import com.revature.expense.model.User;

import java.util.List;
import java.util.Scanner;

import static com.revature.expense.utils.Session.*;

public class App {
    public static void main(String[] args)
    {
        try {
            DBInitializer.ensureSchema();

            UserService userService = new DefUserService();
            ExpenseService expenseService = new DefExpenseService();
            ApprovalService approvalService = new DefApprovalService();

            Scanner sc = new Scanner(System.in);
            boolean running = true;

            try
            {
                while(running)
                {
                    if(!isLoggedIn())
                    {
                        System.out.println("""
                                ---Manager Portal---
                                1) Register
                                2) Login
                                3) Quit""");
                        String c = sc.nextLine().trim();

                        switch(c)
                        {
                            case "1" ->{
                                System.out.print("Username: ");
                                String uname = sc.nextLine().trim();
                                System.out.print("Password: ");
                                String pwd = sc.nextLine().trim();
                                try {
                                    int id = userService.register(uname, pwd, "Manager");
                                    if (id > 0) System.out.println("Manager created with id=" + id);
                                    else System.out.println("Failed to create manager (maybe username taken).");
                                } catch (Exception e) {
                                    System.out.println("Error creating manager: " + e.getMessage());
                                }
                            }
                            case "2" ->{
                                System.out.print("Username: ");
                                String uname = sc.nextLine().trim();
                                System.out.print("Password: ");
                                String pwd = sc.nextLine().trim();
                                try {
                                    User logged = userService.authenticate(uname, pwd);
                                    if (logged == null) {
                                        System.out.println("Login failed (bad credentials).");
                                    } else if (!"Manager".equalsIgnoreCase(logged.getRole())) {
                                        System.out.println("Only users with role 'Manager' may log in here.");
                                    } else {
                                        Session.setCurrentUser(logged);
                                        System.out.println("Welcome " + logged.getUsername());
                                    }
                                } catch (Exception e) {
                                    System.out.println("Login error: " + e.getMessage());
                                }
                            }
                            case "3"->{
                                System.out.println("Quitting.");
                                running = false;

                            }
                            default -> System.out.println("Invalid input: Choose number 1, 2, or 3.");

                        }
                    }
                    else  //they are logged in
                    {
                        User mng = Session.getCurrentUser();

                        System.out.println("\nLogged in as " + mng.getUsername());
                        System.out.print("""
                                
                                --- Manager Menu ---
                                1) List pending expenses
                                2) Approve an expense
                                3) Deny an expense
                                4) Get a Report
                                5) Logout
                                6) Quit
                                >\s""");
                        String c = sc.nextLine().trim();

                        switch(c)
                        {
                            case "1"->{
                                List<com.revature.expense.model.Expense> pending = expenseService.listPending();
                                if (pending.isEmpty()) System.out.println("No pending expenses.");
                                else {
                                    for(Expense p : pending){
                                        System.out.println(p.toString());
                                    }
                                }
                            }
                            case "2","3"->{
                                boolean approve = c.equals("2");
                                try
                                {
                                    System.out.print("Expense id: ");
                                    int id = Integer.parseInt(sc.nextLine().trim());
                                    System.out.print("Comment (optional): ");
                                    String comment = sc.nextLine().trim();
                                    boolean updated = approve
                                            ? approvalService.approve(id, mng.getId(), comment)
                                            : approvalService.deny(id, mng.getId(), comment);

                                    System.out.println(updated ? "Updated." : "Failed to update.");
                                }
                                catch (NumberFormatException nfe)
                                {
                                    System.out.println("Invalid id; must be a number.");
                                }
                                catch (Exception e)
                                {
                                    System.out.println("Error updating approval: " + e.getMessage());
                                }
                            }
                            case "4"->{
                                System.out.println("Reports: 1) By employee  2) By category  ");
                                String r = sc.nextLine().trim();
                                switch (r) {
                                    case "1" -> expenseService.reportByEmployee().forEach(System.out::println);
                                    case "2" -> expenseService.reportByCategory().forEach(System.out::println);

                                }

                            }
                            case "5"->{
                                Session.logout(); // logout
                                System.out.println("Logged out.");

                            }
                            case "6" -> {
                                System.out.println("Exiting App. Goodbye");
                                running = false;
                            }
                            default -> System.out.println("Invalid input.");

                        }

                    }

                }
            }
            finally
            {
                ConnectionManager.closeConnection();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ConnectionManager.closeConnection();
        }

    }
}
