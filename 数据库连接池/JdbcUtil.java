package sunyard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	
	private static JdbcPool pool = new JdbcPool();
	
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
	
	//πÿ±’¡¨Ω”
	public static void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			rs = null;
		}
		
		if (ps != null) {
			try {
				ps.close();
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
