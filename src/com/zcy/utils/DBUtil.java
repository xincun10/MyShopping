package com.zcy.utils; 

import java.io.InputStream;
import java.util.Properties;
import java.sql.*;
  
public class DBUtil {  
      
    //������������Ҫ�ı���  
    private static Connection con = null;  
    //private static PreparedStatement ps = null;  
    //private static ResultSet rs = null;  
      
    //�����������ݿ�����Ҫ�Ĳ���  
    private static String url = "";  
    private static String username = "";  
    private static String driver="";  
    private static String password="";  
      
    //�����ȡ�����ļ�����Ҫ�ı���  
    private static Properties pp = null;  
    private static InputStream fis = null;  
      
    /** 
     * �������� 
     */  
    static {  
        try {  
            //��dbinfo.properties�����ļ��ж�ȡ������Ϣ  
            pp = new Properties();  
            fis = DBUtil.class.getClassLoader().getResourceAsStream("com/zcy/utils/dbinfo.properties");  
              
            pp.load(fis);  
            url = pp.getProperty("url");  
            username = pp.getProperty("username");  
            driver=pp.getProperty("driver");  
            password=pp.getProperty("password");  
            System.out.println(url);
            //��������  
            Class.forName(driver);  
              
        } catch (Exception e) {  
            System.out.println("��������ʧ�ܣ�");  
            e.printStackTrace();  
        } finally {  
            try {  
                fis.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
              
            fis = null; //���������Զ�����  
        }  
          
    }  
      
    /** 
     * �õ�Connection���� 
     * @return Connection 
     */  
    public static Connection getConnection() {  
          
        try {  
            //��������  
            con = DriverManager.getConnection(url, username, password);  
              
        } catch (Exception e) {  
            System.out.println("���ݿ�����ʧ�ܣ�");  
            e.printStackTrace();  
        }  
          
        return con;  
    }  
      
    /** 
     * ͳһ����Դ�رպ��� 
     * @param rs 
     * @param ps 
     * @param ct 
     */  
    public static void close(ResultSet rs,Statement ps, Connection con){  
          
        if(rs != null) {  
            try {  
                rs.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        if(ps != null) {  
            try {  
                ps.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        if(con != null) {  
            try {  
                con.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
}