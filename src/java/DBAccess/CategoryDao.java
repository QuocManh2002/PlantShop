/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DTO.Category;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CategoryDao {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> list = new ArrayList<>();
        Category cate = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from Categories";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("cateID");
                        String name = rs.getString("cateName");
                        cate = new Category(id, name);
                        list.add(cate);
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

    public static void createCategory(String name) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert [dbo].[Categories] ([CateName]) values(?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
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

    public static void updateCategory(int id, String cateName) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Categories]\n"
                        + "set [CateName] = ? \n"
                        + "where [CateID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, cateName);
                pst.setInt(2, id);
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
}
