/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DTO.Plant;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class PlantDao {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws ClassNotFoundException, SQLException {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select [PID],[PName],[price],[imgPath],[description],[status],[dbo].[Plants].[CateID] as 'CateID', [CateName]\n"
                        + "from [dbo].[Plants] join [dbo].[Categories] on [dbo].[Plants].[CateID]=[dbo].[Categories].[CateID]";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where [dbo].[Plants].[PName] like ?";
                } else {
                    sql = sql + "where [CateName] like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("cateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public static Plant getPlant(int id) throws SQLException, Exception {
        Plant p = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'cateid',CateName\n"
                    + "from dbo.Categories,dbo.Plants\n"
                    + "where Categories.CateID=Plants.CateID and PID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {

                    String pname = table.getString("PName");
                    int price = table.getInt("price");
                    String imgpath = table.getString("imgPath");
                    String description = table.getString("description");
                    int status = table.getInt("status");
                    int cateid = table.getInt("cateID");
                    String catename = table.getString("CateName");
                    p = new Plant(id, pname, price, imgpath, description, status, cateid, catename);

                }
            }
            cn.close();
        }
        return p;
    }

    public static ArrayList<Plant> getPlants() {
        Plant p = null;
        Connection cn = null;
        ArrayList<Plant> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName from Plants join Categories on Plants.CateID = Categories.CateID ";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void createPlant(String name, int price, String imgpath, String des, int status, int cateid) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert [dbo].[Plants]([PName],[price],[imgPath],[description],[status],[CateID])\n"
                        + "values(?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, imgpath);
                pst.setString(4, des);
                pst.setInt(5, status);
                pst.setInt(6, cateid);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int updatePlant(int pid, String name, int price, String imgpath, String des, int status, int cateid) {
        Connection cn = null;
        int rs =0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Plants]\n"
                        + "set [PName] = ?,\n"
                        + "[price] = ?,\n"
                        + "[imgPath] = ?,\n"
                        + "[description] = ?,\n"
                        + "[status] = ?,\n"
                        + "[CateID] = ?\n"
                        + "where [PID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, imgpath);
                pst.setString(4, des);
                pst.setInt(5, status);
                pst.setInt(6, cateid);
                pst.setInt(7, pid);
                rs = pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
