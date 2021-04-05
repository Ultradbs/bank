package com.company;

import com.company.controller.Controller;

import java.util.Scanner;

public class Application {
    private final Controller controller;
    private final Scanner scanner;

    public Application(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }


    public void start() {
        int user_id;
        boolean check = true;
        while (check) {
                showMenu();
                try {
                    int option = scanner.nextInt();
                    if (option == 1) {
                        user_id = getUserId();
                        System.out.println("Welcome: " + getUserName(user_id));
                        boolean operations = true;
                        while (operations){
                            showOperations();
                            int operation = scanner.nextInt();
                            if(operation==1){
                                int receiver_id=0;
                                boolean trans = true;
                                while(trans) {
                                    boolean phoneCheck = true;
                                    while (phoneCheck) {
                                        System.out.println("Enter phone: ");
                                        String phone = scanner.next();
                                        receiver_id = findIdByPhone(phone);
                                        String checkPhone = getUserName(receiver_id);
                                        if (checkPhone.equals("Error")) {
                                            System.out.println("Phone don't match");
                                        } else if (receiver_id == user_id) {
                                            System.out.println("Warn: It's your own account!");
                                        } else {
                                            phoneCheck = false;
                                        }
                                    }
                                    System.out.println("Is " + getUserName(receiver_id) + " must get?");
                                    System.out.println("Print 'yes' if it is right ");
                                    String answer = scanner.next();
                                    if (answer.equals("yes")) {
                                        trans = false;
                                    } else System.out.println("Try Again");

                                    boolean cashCheck = true;
                                    int sum=0;
                                    while (cashCheck) {
                                        System.out.println("Enter the amount of money you want to transfer: ");
                                        sum = scanner.nextInt();
                                        if(sum<=0){
                                            System.out.println("Your input is incorrect");
                                        } else if(sum>getCash(user_id)){
                                            System.out.println("You don't have enough funds ");
                                        } else {cashCheck=false;}
                                    }

                                    makeTransaction(user_id, receiver_id,sum);
                                }
                            }else if(operation==2){
                                System.out.println("List of incomes: ");
                                listIncomes(user_id);
                            }else if(operation==3){
                                System.out.println("List of costs: ");
                                listCosts(user_id);
                            }else if(operation==4){
                                System.out.println("----------------------");
                                System.out.println("Cash account: ");
                                System.out.print(getCash(user_id));
                                System.out.println(" ₸");
                                System.out.println("----------------------");
                            }else if(operation==5){
                                operations = false;
                            }

                        }
                    } else if (option == 2) {
                        System.out.println("Enter your name");
                        String name = scanner.next();
                        System.out.println("Enter your surname");
                        String surname = scanner.next();
                        boolean checkPhoneLoop = true;
                        String phone=null;
                        while(checkPhoneLoop){
                            System.out.println(("Enter your phone"));
                            phone = scanner.next();
                            if(checkPhone(phone)==false){
                                System.out.println("Your phone is unique!");
                                checkPhoneLoop = false;
                            } else{
                                System.out.println("Use another phone number");
                            }
                        }
                        boolean checkReg = true;
                        String login = null, input;
                        while(checkReg) {
                            System.out.println("Create your login");
                            input = scanner.next();
                            login= input;
                            if(checkLogin(login) == false){
                                System.out.println("Your login is unique!");
                                checkReg = false;
                            } else System.out.println("Create unique login");
                        }
                        System.out.println("Create your password");
                        String password = scanner.next();
                        newUser(name, surname, phone,login,password);
                    } else {
                        check = false;
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }



        }

    private void listCosts(int user_id) {
        System.out.println("==================================");
        System.out.println(controller.listCosts(user_id));
        System.out.println("==================================");
    }

    private void listIncomes(int user_id) {
        System.out.println("==================================");
        System.out.println(controller.listIncomes(user_id));
        System.out.println("==================================");

    }


    private void makeTransaction(int user_id, int receiver_id, int sum) {
         controller.makeTransaction(user_id, receiver_id, sum);
         int sum1 = getCash(user_id)-sum;
         controller.changeBank1(user_id, sum1);
         int sum2 = getCash(receiver_id)+sum;
         controller.changeBank2(receiver_id,sum2);
    }

    private int findIdByPhone(String phone) {
        return controller.findIdByPhone(phone);
    }

    private int getCash(int user_id) {
        return  controller.getCash(user_id);
    }

    private String getUserName(int id) {
        return controller.getName(id);
    }

    public void showMenu(){
        System.out.println("Enter option (1-3): ");
        System.out.println("1.Log in");
        System.out.println("2.Registration");
        System.out.println("3.Exit");
    }

    public void showOperations(){
        System.out.println("Enter option (1-5):");
        System.out.println("1.Send money");
        System.out.println("2.Check incomes");
        System.out.println("3.Check expensec");
        System.out.println("4.Check cash");
        System.out.println("5.Exit");
    }

    public boolean checkAccount(String login, String password){
        boolean log, pass;
        log = checkLogin(login);
        pass = checkPassword(password);
        if(log == true && pass ==true){
            return true;
        } else return false;
    }

    public int getUserId(){
        boolean check = true;
        String login = null; String password = null;
        while (check) {
            System.out.println("Enter your login");
            login = scanner.next();
            System.out.println("Enter your password");
            password = scanner.next();
            if(checkAccount(login, password)){
                check = false;
            } else System.out.println("Some mistake in your input");
        }
        int id = controller.getUserId(login, password);
        return id;
    }

    public boolean checkLogin(String login){
        if(controller.checkLogin(login) == true){
            return true;
        } else return  false;
    }

    private boolean checkPhone(String phone) {
        if(controller.checkPhone(phone) == true){
            return true;
        } else return  false;
    }


    public boolean checkPassword(String password){
        if(controller.checkPassword(password)==true){
            return true;
        }else return false;
    }

    public void newUser(String name, String surname, String phone,String login, String password){
        controller.newUser(name,surname, phone, login, password);

    }

}