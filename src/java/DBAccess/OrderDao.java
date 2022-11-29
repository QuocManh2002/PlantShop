/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import DTO.Order;
import DTO.OrderDetail;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class OrderDao {

    public static ArrayList<Order> getOrders(String email) throws ClassNotFoundException, SQLException {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        Order order = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && email != null) {

                String sql = "select [OrderID],[OrdDate],[shipdate],[dbo].[Orders].[status],[dbo].[Orders].[AccID]\n"
                        + "from [dbo].[Orders] join [dbo].[Accounts] \n"
                        + "on [dbo].[Orders].[AccID] = [dbo].[Accounts].[accID]\n"
                        + "where [dbo].[Accounts].email like ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        order = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(order);
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

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws ClassNotFoundException, SQLException {
        Connection cn = null;
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql1 = "select [DetailId],[OrderID],[PID],[PName],[price],[imgPath],[quantity]\n"
                        + "from [dbo].[OrderDetails],[dbo].[Plants]\n"
                        + "where [OrderID]= ? and OrderDetails.FID=Plants.PID";

                PreparedStatement pst = cn.prepareStatement(sql1);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailID");
                        int PlantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderDetail = new OrderDetail(detailID, orderID, PlantID, PlantName, price, imgPath, quantity);
                        list.add(orderDetail);
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

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                //get acc by email
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                //insert a new order
                Date date = new Date(System.currentTimeMillis());
                sql = "insert Orders(OrdDate, status, AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, date);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                //get the newest added order = biggest orderid
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values (?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<Order> getOrders() {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        Order ord = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from Orders";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        String orderDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        ord = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(ord);
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

    public static ArrayList<Order> getOrders(String from, String to) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [OrderID],[OrdDate],[shipdate],[status],AccID\n"
                        + "from Orders o "
                        + "where o.OrdDate >=? and o.OrdDate <=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, from);
                pst.setString(2, to);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order od = new Order(accID, ordDate, shipDate, status, accID);
                        list.add(od);

                    }
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
        return list;
    }

    public static void updateOrderStatus(int id, int status) {

        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update [dbo].[Orders]\n"
                        + "set [status] = ?\n"
                        + "where [OrderID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, id);
                pst.executeUpdate();
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

    }

    public static Order getOrder(int id) {

        Connection cn = null;
        Order order = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select [OrderID]\n"
                        + "      ,[OrdDate]\n"
                        + "      ,[shipdate]\n"
                        + "      ,[status]\n"
                        + "      ,[AccID]\n"
                        + "	  from [dbo].[Orders]\n"
                        + "	  where [OrderID] = ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {

                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    order = new Order(id, orderDate, shipDate, status, accID);

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
        return order;
    }

    public static ArrayList<Order> getOrders(int accID) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [OrderID]\n"
                        + "      ,[OrdDate]\n"
                        + "      ,[shipdate]\n"
                        + "      ,[status]\n"
                        + "      ,[AccID]\n"
                        + "	  from [dbo].[Orders]\n"
                        + "	  where [AccID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accID);
                ResultSet rs = pst.executeQuery();
                if (rs != null  ) {
                    while(rs.next()){
                        int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    
                    order = new Order(orderID, orderDate, shipDate, status, accID);
                    list.add(order);
                            }    }
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
    public static boolean changeOrderShipdate(String orderID, String currentDate) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String dt = currentDate;  // Start date
            String sql = "update Orders\n"
                        + "set shipdate =? \n"
                        + "where OrderID =? ";
            if (currentDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(dt));
                c.add(Calendar.DATE, 7);  // number of days to add
                dt = sdf.format(c.getTime());  // dt is now 6the new date
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dt);
            pst.setString(2, orderID);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
    public static ArrayList<Order> getOrdersUser(int accid, String from, String to) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select [OrderID],[OrdDate],[shipdate],[status],AccID\n"
                        + "from Orders o "
                        + "where o.OrdDate >=? and o.OrdDate <=? and AccID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, from);
                pst.setString(2, to);
                pst.setInt(3, accid);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("OrderID");
                        String ordDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order od = new Order(id, ordDate, shipDate, status, accID);
                        list.add(od);

                    }
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
        return list;
    }
}
