package com.zcy.utils;  
  
import java.sql.*;
import java.util.ArrayList;  
  
public class SqlHelper {  
      
    private static Connection con = null;  
      
    private static PreparedStatement ps = null;  
      
    private static ResultSet rs = null;  
      
    private static CallableStatement cs = null;  
      
      
    /** 
     * �ṩ��ѯ���� 
     * @param sql sql��� 
     * @param parameters ���ʺŸ�ֵ�Ĳ����� 
     * @return {@link ArrayList} 
     */  
    public ArrayList executeQuery(String sql, String[] parameters) {  
          
        ArrayList al = new ArrayList();  
          
        try {  
            con = DBUtil.getConnection();  
            ps = con.prepareStatement(sql);  
              
            //��sql����е��ʺŸ�ֵ  
            if (parameters != null) {  
                for (int i = 0; i < parameters.length; i++) {  
                    ps.setObject(i+1, parameters[i]);  
                }  
            }  
              
            rs = ps.executeQuery();  
              
            //�õ������(rs)�Ľṹ  
            ResultSetMetaData rsmd = rs.getMetaData();  
              
            //ͨ��rsmd���Եõ��ý�����ж�����  
            int columnNum = rsmd.getColumnCount();  
  
            //��rs��ȡ�����ݣ����ҷ�װ��ArrayList��  
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
     * �ṩͳһ�Ĳ���/ɾ��/���·��� 
     * @param sql sql��� 
     * @param parameteres ���ʺŸ�ֵ�Ĳ����� 
     * @return 
     */  
    public static boolean executeUpdate(String sql,String[] parameteres) {  
          
        boolean success = false;  
          
        try {  
              
            con = DBUtil.getConnection();  
            ps = con.prepareStatement(sql);  
              
            //���ʺŸ�ֵ  
            if (parameteres != null) {  
                for (int i = 0; i < parameteres.length; i++) {  
                    ps.setString(i + 1, parameteres[i]);  
                }  
            }  
              
            //ִ�ж�����������ء�1�� ��Ϊ�����ɹ�  
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
     * �ṩͳһ�Ĳ���/ɾ��/���·���[��Ҫ��������] 
     * @param sql 
     * @param parameters 
     */  
    public static void executeUpdate(String sql[], String[][] parameters){  
          
        try {  
            con = DBUtil.getConnection();  
              
            //sql������ύ��Ӧ�ó����𣬳���������commit����rollback����  
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
              
            //�ع�����  
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