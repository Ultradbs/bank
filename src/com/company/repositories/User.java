package com.company.repositories;

import com.company.database.IDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements IUser {
    private final IDB db;

    public User(IDB db) {
        this.db = db;
    }

    @Override
    public boolean checkPhone(String phone) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id FROM accounts WHERE phone = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,phone);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String login) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id FROM accounts WHERE login = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,login);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }



    @Override
    public boolean checkPassword(String password) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id FROM accounts WHERE password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int getUserId(String login, String password) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id FROM accounts WHERE login = ? AND password = ? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,login);
            st.setString(2,password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id =  rs.getInt("id");

                return id;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Boolean newUser(String name, String surname, String phone, String login, String password) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "insert into accounts (name, surname, phone, bank, login, password) values (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,name);
            st.setString(2,surname);
            st.setString(3,phone);
            st.setInt(4,200);
            st.setString(5,login);
            st.setString(6,password);

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String getName(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT name, surname FROM accounts WHERE id = ? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                return surname + " "+ name;
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "Error";

        }

    @Override
    public int getCash(int user_id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT bank FROM accounts WHERE id = ? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, user_id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int cash = rs.getInt("bank");
                return cash;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return -1;

    }

    @Override
    public int findIdByPhone(String phone) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id FROM accounts WHERE phone= ? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,phone);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id =  rs.getInt("id");

                return id;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public boolean makeTransaction(int user_id, int receiver_id, int sum) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "insert into transactions (sender_id, receiver_id, sum) values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,user_id);
            st.setInt(2,receiver_id);
            st.setInt(3,sum);


            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void changeBank1(int user_id, int sum) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "update accounts set bank = ? where id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,sum);
            st.setInt(2,user_id);
            st.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public void changeBank2(int receiver_id, int sum) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "update accounts set bank = ? where id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,sum);
            st.setInt(2,receiver_id);
            st.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public String listIncomes(int user_id) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id, sender_id, sum FROM transactions WHERE receiver_id=? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,user_id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                System.out.println(rs.getInt("id")+"] "+ getName(rs.getInt("sender_id"))
                        +" transferred you " + rs.getInt("sum") +" ₸");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "";
    }

    public String listCosts(int user_id) {
        Connection con = null;
        try {

            con = db.getConnection();
            String sql = "SELECT id, receiver_id, sum FROM transactions WHERE sender_id=? ";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,user_id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                System.out.println(rs.getInt("id")+"] You transferred "+ rs.getInt("sum") +"₸ "
                      +  "to " + getName(rs.getInt("receiver_id")));


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "";
    }
}
