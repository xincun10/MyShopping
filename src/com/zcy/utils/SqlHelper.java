package com.zcy.utils;  
  
import java.sql.*;
import java.util.ArrayList;  
  
public class SqlHelper {  
      
    private static Connection con = null;  
      
    private static PreparedStatement ps = null;  
      
    private static ResultSet rs = null;  
      
    private static CallableStatement cs = null;  
      
      
    /** 
     * 提供查询方法 
     * @param sql sql语句 
     * @param parameters 给问号赋值的参数组 
     * @return {@link ArrayList} 
     */  
    public ArrayList executeQuery(String sql, String[] parameters) {  
          
        ArrayList al = new ArrayList();  
          
        try {  
            con = DBUtil.getConnection();  
            ps = con.prepareStatement(sql);  
              
            //给sql语句中的问号赋值  
            if (parameters != null) {  
                for (int i = 0; i < parameters.length; i++) {  
                    ps.setObject(i+1, parameters[i]);  
                }  
            }  
              
            rs = ps.executeQuery();  
              
            //得到结果集(rs)的结构  
            ResultSetMetaData rsmd = rs.getMetaData();  
              
            //通过rsmd可以得到该结果集有多少列  
            int columnNum = rsmd.getColumnCount();  
  
            //从rs中取出数据，并且封装到ArrayList中  
            while (rs.next()) {  
                  
                Object []objects = new Object[columnNum];  
                for(int i = 0; i < objects.length; i++) {  
                    objects[i] = rs.getObject(i + 1);  
                }  
                  
                al.add(objects);  
            }  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            DBUtil.close(rs, ps, con);  
        }  
          
        return al;  
    }  
      
    /** 
     * 提供统一的插入/删除/更新方法 
     * @param sql sql语句 
     * @param parameteres 给问号赋值的参数组 
     * @return 
     */  
    public static boolean executeUpdate(String sql,String[] parameteres) {  
          
        boolean success = false;  
          
        try {  
              
            con = DBUtil.getConnection();  
            ps = con.prepareStatement(sql);  
              
            //给问号赋值  
            if (parameteres != null) {  
                for (int i = 0; i < parameteres.length; i++) {  
                    ps.setString(i + 1, parameteres[i]);  
                }  
            }  
              
            //执行动作，如果返回“1” 则为操作成功  
            if (ps.executeUpdate() == 1) {  
                success = true;  
            }  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            DBUtil.close(rs, ps, con);  
        }  
          
        return success;  
    }  
      
    /** 
     * 提供统一的插入/删除/更新方法[需要考虑事物] 
     * @param sql 
     * @param parameters 
     */  
    public static void executeUpdate(String sql[], String[][] parameters){  
          
        try {  
            con = DBUtil.getConnection();  
              
            //sql命令的提交由应用程序负责，程序必须调用commit或者rollback方法  
            con.setAutoCommit(false);  
              
            for (int i = 0; i < sql.length; i++) {  
                if (parameters[i] != null) {  
                    ps = con.prepareStatement(sql[i]);  
                    for (int j = 0; j < parameters[i].length; i++){  
                        ps.setString(j + 1, parameters[i][j]);  
                    }  
                    ps.executeUpdate();  
                }  
            }  
            con.commit();  
              
        } catch (Exception e) {  
            e.printStackTrace();  
              
            //回滚操作  
            try {  
                con.rollback();  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
            throw new RuntimeException(e.getMessage());  
              
        } finally {  
            DBUtil.close(rs, ps, con);  
        }  
    }  
      
}