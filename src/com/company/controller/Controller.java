package com.company.controller;

import com.company.repositories.User;

public class Controller {
    private User repo;

    public Controller(User repo) {
        this.repo = repo;
    }

    public boolean checkPhone(String phone) { return  repo.checkPhone(phone); }

    public boolean checkLogin(String login){
        return  repo.checkLogin(login);
    }
    public boolean checkPassword(String password) {
        return repo.checkPassword(password);
    }

    public int getUserId(String login, String password) {
        Integer id = repo.getUserId(login, password);
        if(id >0){return id;} else return 0;
    }

    public void newUser(String name,String surname, String phone, String login, String password){
        boolean response = repo.newUser(name,surname, phone, login, password);
        if(response == true){
            System.out.println("Account was created");
        } else System.out.println("There something wrong");

    }

    public String getName(int id) {
        return repo.getName(id);
    }

    public int getCash(int user_id) {
        return repo.getCash(user_id);
    }

    public int findIdByPhone(String phone) {
        return repo.findIdByPhone(phone);
    }

    public void makeTransaction(int user_id, int receiver_id, int sum) {
        boolean response = repo.makeTransaction(user_id, receiver_id,sum);
            if (response==true){
                System.out.println("Money transfer was successful");
            } else System.out.println("Failed");
    }

    public void changeBank1(int user_id, int sum) {
        repo.changeBank1(user_id, sum);
    }

    public void changeBank2(int receiver_id, int sum) {
        repo.changeBank2(receiver_id, sum);
    }


    public String listIncomes(int user_id) {
        return repo.listIncomes(user_id);

    }

    public String listCosts(int user_id) {
        return repo.listCosts(user_id);
    }
}
