package sunyard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTest {

	public static void main(String[] args) {
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	conn = JdbcUtil.getConnection();
        	StringBuilder sql = new StringBuilder();
        	sql.append("select * from G_BOOKS where ");
            sql.append("BOOK_ID = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, "309");//1�����һ�������������ֵ����Ҫ�����ֵ
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("TITLE"));
            }
        }catch (Exception e) {
			// TODO: handle exception
		}finally {
            JdbcUtil.release(conn, ps, rs);
		}
        
       
	}
}
