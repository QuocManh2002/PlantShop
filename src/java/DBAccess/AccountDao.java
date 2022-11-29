/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DTO.Account;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class AccountDao {

    public static Account getAccount(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [accID],[email],[password],[fullname],[phone],[status],[role]\n"
                        + "from [dbo].[Accounts]\n"
                        + "where status = 1 and email =? and password = ?    COLLATE Latin1_General_CS_AS ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int accID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(accID, Email, Password, Fullname, Status, Phone, Role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static ArrayList<Account> getAccounts() throws ClassNotFoundException, SQLException  {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        try {
            if (cn != null) {
                String sql = "select [accID],[email],[password],[fullname],[phone],[status],[role]\n"
                        + "from [dbo].[Accounts]";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int accID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        Account acc = new Account(accID, Email, Password, Fullname, Status, Phone, Role);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static void updateAccountStatus(String email, int status) {
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Accounts]\n"
                        + "set [status] = ?\n"
                        + "where [email] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        boolean result = false;
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Accounts]\n"
                        + "set [password] = '',\n"
                        + "[fullname] = '',\n"
                        + "[phone] = ''\n"
                        + "where [email] = ''";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, email);
                ResultSet rs = pst.executeQuery(sql);
                if (rs != null) {
                    result = true;
                } else {
                    result = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static int insertAccount(String email, String password, String fullname, String phone, int status, int role) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert [dbo].[Accounts]([email],[password],[fullname],[phone],[status],[role])\n"
                        + "values (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                pst.setString(3, fullname);
                pst.setString(4, phone);
                pst.setInt(5, status);
                pst.setInt(6, role);
                result = pst.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Account getAccount(String token) {
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [accID],[email],[password],[fullname],[phone],[status],[role],[token]\n"
                        + "from [dbo].[Accounts]\n"
                        + "where token like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int accID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = token;
                    acc = new Account(accID, Email, Password, Fullname, Status, Phone, Role, Token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static boolean updateToken(String token, String email) {
        Account acc = null;
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Accounts]\n"
                        + "set [token] = ?\n"
                        + "where [email] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                int rs = pst.executeUpdate();
                if (rs != 0) {
                    result = true;
                } else {
                    result = false;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {

                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static int updateAccount(int id, String newname, String newphone) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Accounts]\n"
                        + "set [fullname] = ?,\n"
                        + "[phone] = ?\n"
                        + "where [accID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newname);
                pst.setString(2, newphone);
                pst.setInt(3, id);
                rs = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {

                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return rs;
    }

    public static ArrayList<Account> getAccounts(String name) {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [accID],[email],[password],[fullname],[phone],[status],[role],[token]\n"
                        + "from [dbo].[Accounts]\n"
                        + "where [fullname] like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+name+"%");
                ResultSet rs = pst.executeQuery();
                if(rs!=null){
                    while(rs.next()){
                        int accID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String token = rs.getString("token");
                    acc = new Account(accID, Email, Password, Fullname, Status, Phone, Role, token);
                    list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {

                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public static Account getAccountByEmail(String email) {
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts\n"
                        + "where email =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    int accid = table.getInt("accID");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    acc = new Account(accid, email, password, fullname, status, phone, role);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
}
