import java.sql.*;

/**
 * Created by 11981 on 2016/12/4.
 */
public class Test_Batch {
    // JDBC 驱动器名称 和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/shiyanloudb?" +
            "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //加这一行可以消除一个warning
    //warning:建立ssl连接，但是服务器没有身份认证，这种方式不推荐使用

    //  数据库用户和密码
    static final String USER = "root";

    static final String PASSWORD = "root";

    public static void main(String[] args){
        Connection conn = null;
        Statement stat = null;
        try {
            //注册JDBC 驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            //打开连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            conn.setAutoCommit(false); //启动一个事务，事先关闭auto-commit模式

            //执行查询
            System.out.println("Creating statement...");
            stat = conn.createStatement();
            String sql = "INSERT INTO Students VALUES(6, 21, 'Mike')";
            stat.addBatch(sql);

            System.out.print("1");
            String sql2 = "INSERT INTO　Students(id, name, age) VALUES(7, 'Angle', 23)";
            stat.addBatch(sql2);

            int[] count = stat.executeBatch();
            conn.commit();//提交事务
            String sql3 = "SELECT id, name, age FROM Students";
            ResultSet rs = stat.executeQuery(sql3);

            //得到和处理结果集
            while (rs.next()){
                //检索
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");

                //显示
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Name: " + name);
                System.out.println();
            }

            //清理环境
            rs.close();
            stat.close();
            conn.close();
        }catch (SQLException se){
            // JDBC 操作错误
            se.printStackTrace();
            // Class.forName 错误
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //这里一般用来关闭资源的
            try {
                if(stat != null)
                    stat.close();
            }catch (SQLException se2){
            }
            try {
                if(conn != null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbey!");
    }
}
