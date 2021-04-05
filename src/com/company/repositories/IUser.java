package com.company.repositories;

public interface IUser {
    boolean checkPhone(String phone);

    boolean checkLogin(String login);

    boolean checkPassword(String password);
    int getUserId(String login, String password);

    Boolean newUser(String name, String surname, String phone, String login, String password);

    String getName(int id);

    int getCash(int user_id);

    int findIdByPhone(String phone);

    boolean makeTransaction(int user_id, int receiver_id, int sum);

    void changeBank1(int user_id, int sum);

    void changeBank2(int receiver_id, int sum);
}
