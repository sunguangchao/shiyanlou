package dao;

import entity.Category;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11981 on 2016/9/11.
 */
public class CategoryDAO {
    public int getTotal()
    {
        int total = 0;
        try(Connection c = DBUtil.getConnection(); Statement s = c.createStatement(); ){
            String sql = "select count(*) from category";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next())
            {
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category category)
    {
        String sql = "insert into category values(null,?)";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);)
        {
            /*PreparedStatement:表示预编译的 SQL 语句的对象。
            * SQL 语句被预编译并且存储在 PreparedStatement 对象中。然后可以使用此对象高效地多次执行该语句。
            */
            ps.setString(1,category.name);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                int id = rs.getInt(1);
                category.id = id;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void update(Category category)
    {
        String sql = "update category set name = ? where id = ?";
        try(Connection c = DBUtil.getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);)
        {
            ps.setString(1,category.name);
            ps.setInt(2,category.id);
            ps.execute();


        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void delete(int id)
    {
        try(Connection c = DBUtil.getConnection();Statement s = c.createStatement();)
        {
            String sql = "delete from category where id = "+id;
            s.execute(sql);


        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Category get(int id) {
        Category category = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from category where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                category = new Category();
                String name = rs.getString(2);
                category.name = name;
                category.id = id;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    public List<Category> list(){
        return list(0,Short.MAX_VALUE);
    }

    public List<Category> list(int start,int count)
    {
        List<Category> categorys = new ArrayList<Category>();
        String sql = "select * from category order by id desc linit ?,?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);)
        {
            ps.setInt(1,start);
            ps.setInt(2,count);

            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Category category = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                category.id = id;
                category.name = name;
                categorys.add(category);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return categorys;
    }

}
