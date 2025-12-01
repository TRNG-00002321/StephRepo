package com.revature.expense;

import com.revature.expense.utils.*;
import com.revature.expense.dao.*;
import com.revature.expense.dao.impl.*;
import com.revature.expense.service.*;
import com.revature.expense.service.impl.*;
import com.revature.expense.model.User;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            DBInitializer.ensureSchema();

            UserDAO userDao = new UserDAOImpl();
            ExpenseDAO expenseDao = new ExpenseDAOImpl();
            ApprovalDAO approvalDao = new ApprovalDAOImpl();

            UserService userService = new DefUserService(userDao);
            ExpenseService expenseService = new DefExpenseService(expenseDao);
            ApprovalService approvalService = new DefApprovalService(approvalDao);

            // bootstrap admin if missing
            if (userDao.findByUsername("admin") == null) {
                int id = userService.register("admin", "admin", "Manager");
                System.out.println("Created bootstrap admin admin/admin id=" + id);
            }

            System.out.println();

            Scanner sc = new Scanner(System.in);
            System.out.print("Username: ");
            String uname = sc.nextLine().trim();
            System.out.print("Password: ");
            String pwd = sc.nextLine().trim();

            User logged = userService.authenticate(uname, pwd);
            if (logged == null) {
                System.out.println("Login failed.");
                ConnectionManager.closeConnection();
                return;
            }
            if (!"Manager".equalsIgnoreCase(logged.getRole())) {
                System.out.println("Must be a Manager to use this app.");
                ConnectionManager.closeConnection();
                return;
            }

            System.out.println("Welcome " + logged.getUsername());

            // Minimal menu loop (list pending + approve/deny + quit)
            loop:
            while (true) {
                System.out.println("\n1) List pending  2) Approve  3) Deny  4) Quit");
                String c = sc.nextLine().trim();
                switch (c) {
                    case "1":
                        List<com.revature.expense.model.Expense> pending = expenseService.listPending();
                        if (pending.isEmpty()) System.out.println("No pending.");
                        else pending.forEach(System.out::println);
                        break;
                    case "2":
                    case "3":
                        boolean approve = c.equals("2");
                        System.out.print("Expense id: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Comment: ");
                        String comment = sc.nextLine().trim();
                        boolean ok = (approve)
                                ? approvalService.approve(id, logged.getId(), comment)
                                : approvalService.deny(id, logged.getId(), comment);
                        System.out.println(ok ? "Updated." : "Failed.");
                        break;
                    case "4":
                        break loop;
                    default:
                        System.out.println("Bad choice.");
                }
            }

            ConnectionManager.closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
            ConnectionManager.closeConnection();
        }
    }
}
