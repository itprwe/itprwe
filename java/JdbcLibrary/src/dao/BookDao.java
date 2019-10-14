package dao;

import entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends BaseDao{

    //增
    public int insert(Book b){
        String sql = "insert into book (categoryId,title,summary,uploaduser,createdate) values (?,?,?,?,?)";
        return executeUpdate(sql ,new Object[]{b.getCategoryId(),b.getTitle(),b.getSummary(),b.getUploaduser(),b.getCreatedate()});
    }

    //删
    public int delete(Book b){
        String sql= "delete from book where id=?";
        return  executeUpdate(sql,new Object[]{b.getId()});

    }

    //改
    public int update(Book b){
        String sql = "UPDATE `book` SET `categoryId`=?,`title`=?,`summary`=?,`uploaduser`=?,`createdate`=? WHERE id=?";
        return executeUpdate(sql,new Object[]{b.getCategoryId(),b.getTitle(),b.getSummary(),b.getUploaduser(),b.getCreatedate(),b.getId()});
    }

    //查
    public List<Book> findAll(){
        String sql = "select * from book";
        return search(sql);

    }
    //根据条件查
    public List<Book> search(String sql,Object[] ...param){
        ArrayList<Book> list = new ArrayList<>();
        Connection conn = this.getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = this.prepareStatement(conn, sql, param);
            rs = pst.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setCategoryId(rs.getInt(2));
                book.setTitle(rs.getString(3));
                book.setSummary(rs.getString(4));
                book.setUploaduser(rs.getString(5));
                book.setCreatedate(rs.getString(6));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,pst,rs);
        }
        return list;
    }

}
