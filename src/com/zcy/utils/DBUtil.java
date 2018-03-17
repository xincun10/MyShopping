package com.zcy.utils; 

import java.io.InputStream;
import java.util.Properties;
import java.sql.*;
  
public class DBUtil {  
      
    //定义链接所需要的变量  
    private static Connection con = null;  
    //private static PreparedStatement ps = null;  
    //private static ResultSet rs = null;  
      
    //定义链接数据库所需要的参数  
    private static String url = "";  
    private static String username = "";  
    private static String driver="";  
    private static String password="";  
      
    //定义读取配置文件所需要的变量  
    private static Properties pp = null;  
    private static InputStream fis = null;  
      
    /** 
     * 加载驱动 
     */  
    static {  
        try {  
            //从dbinfo.properties配置文件中读取配置信息  
            pp = new Properties();  
            fis = DBUtil.class.getClassLoader().getResourceAsStream("com/zcy/utils/dbinfo.properties");  
              
            pp.load(fis);  
            url = pp.getProperty("url");  
            username = pp.getProperty("username");  
            driver=pp.getProperty("driver");  
            password=pp.getProperty("password");  
            System.out.println(url);
            //加载驱动  
            Class.forName(driver);  
              
        } catch (Exception e) {  
            System.out.println("驱动加载失败！");  
            e.printStackTrace();  
        } finally {  
            try {  
                fis.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
              
            fis = null; //垃圾回收自动处理  
        }  
          
    }  
      
    /** 
     * 得到Connection链接 
     * @return Connection 
     */  
    public static Connection getConnection() {  
          
        try {  
            //建立连接  
            con = DriverManager.getConnection(url, username, password);  
              
        } catch (Exception e) {  
            System.out.println("数据库链接失败！");  
            e.printStackTrace();  
        }  
          
        return con;  
    }  
      
    /** 
     * 统一的资源关闭函数 
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