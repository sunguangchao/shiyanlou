import java.sql.*;

/**
 * Created by 11981 on 2016/12/4.
 */
public class Test_Pre {

    // JDBC ���������� �����ݿ��ַ
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/shiyanloudb?" +
            "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    //����һ�п�������һ��warning
    //warning:����ssl���ӣ����Ƿ�����û�������֤�����ַ�ʽ���Ƽ�ʹ��

    //  ���ݿ��û�������
    static final String USER = "root";

    static final String PASSWORD = "root";

    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement stat = null;
        try {
            //ע��JDBC ��������
            Class.forName("com.mysql.jdbc.Driver");

            //������
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);

            //ִ�в�ѯ
            System.out.println("Creating statement...");
            String sql;
            sql = "UPDATE Students set age=? WHERE id=?";
            stat = conn.prepareStatement(sql);

            stat.setInt(1, 23);
            stat.setInt(2, 2);
            int rows = stat.executeUpdate();
            System.out.println("��Ӱ������� �� " + rows);

            sql = "SELECT id, name, age FROM Students";
            ResultSet rs = stat.executeQuery(sql);

            //�õ��ʹ�������
            while (rs.next()){
                //����
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");

                //��ʾ
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Name: " + name);
                System.out.println();
            }

            //������
            rs.close();
            stat.close();
            conn.close();
        }catch (SQLException se){
            // JDBC ��������
            se.printStackTrace();
            // Class.forName ����
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //����һ�������ر���Դ��
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
