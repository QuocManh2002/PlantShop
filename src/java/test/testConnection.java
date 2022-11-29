/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DBAccess.AccountDao;
import DTO.Account;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class testConnection {
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        ArrayList<Account> list = AccountDao.getAccounts();
          for (Account account : list) {
              System.out.println(account.getAccID()+","+account.getEmail());
        }
    }
}
