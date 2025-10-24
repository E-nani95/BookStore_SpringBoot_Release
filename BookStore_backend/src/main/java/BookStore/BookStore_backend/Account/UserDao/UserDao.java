package BookStore.BookStore_backend.Account.UserDao;

import BookStore.BookStore_backend.Account.UserDto.BuyorderDto;
import BookStore.BookStore_backend.Account.UserDto.OrderListDto;
import BookStore.BookStore_backend.Account.UserDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class UserDao {
    private final DataSource dataSource;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Transactional
    public boolean DeleteList(OrderListDto orderListDto){
        Connection conn;
        PreparedStatement pstmt, pstmt_select;
        ResultSet rs;
        String sql_select_bno="Select b_no from book where b_name = ?";
        String sql = "Delete from buyorder where u_no = ? and b_no = ?";

        int rs_int=0;
        try {
            conn=getConnection();
            pstmt_select=conn.prepareStatement(sql_select_bno);
            pstmt_select.setString(1,orderListDto.getB_name());
            rs = pstmt_select.executeQuery();
            int b_no=0;
            while(rs.next()){
                b_no=rs.getInt("b_no");
                System.out.println("+++++++++++++++");
                System.out.println("b_no:"+b_no);
            }
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,orderListDto.getU_no());
            pstmt.setInt(2,b_no);
            rs_int = pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs_int>0;
    }

    @Transactional
    public ArrayList<OrderListDto> OrderList(BuyorderDto buyorderDto){
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        String sql = "SELECT * FROM buyorder JOIN book ON book.b_no = buyorder.b_no WHERE u_no = ?";
        ArrayList<OrderListDto> olt = new ArrayList<>();
        try {
            conn=getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,buyorderDto.getU_no());
            rs=pstmt.executeQuery();
            while (rs.next()){
                OrderListDto orderListDto = new OrderListDto();
                orderListDto.setB_name(rs.getString("b_name"));
                orderListDto.setU_no(rs.getInt("u_no"));
                orderListDto.setDate(rs.getDate("o_date"));
                orderListDto.setO_count(rs.getInt("o_count"));
                int total_price = rs.getInt("o_count") * rs.getInt("b_price");
                orderListDto.setTotal_price(total_price);
                olt.add(orderListDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return olt;
    }

    @Transactional
    public boolean orderRecode(BuyorderDto buyorderDto){
        Connection conn;
        PreparedStatement pstmt;
        String sql="Insert into buyorder (u_no,b_no,o_date,o_count) values (?,?,?,?)";
        LocalDateTime now = LocalDateTime.now();
        String s = now.toString();
        String a [] =s.split("T");
        System.out.println(a[0]);
        int recode =0;
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,buyorderDto.getU_no());
            pstmt.setInt(2,buyorderDto.getB_no());
            pstmt.setDate(3, Date.valueOf(a[0]));
            pstmt.setInt(4,buyorderDto.getO_count());
            recode = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return recode >0;
    }

    @Transactional
    public boolean userSignUp(UserDto userDto){
        Connection conn;
        PreparedStatement pstmt;
        String sql ="Insert into user (u_id, u_pw, u_name) values (?,?,?)";
        int res_num=0;
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,userDto.getU_id());
            pstmt.setString(2,userDto.getU_pw());
            pstmt.setString(3,userDto.getU_name());
            res_num=pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res_num>0;
    }

    @Transactional
    public boolean DuplicateCheck(UserDto userDto){
        // true 가 동일 id 존재
        Connection conn;
        PreparedStatement pstmt;
        String sql ="Select * from user where u_id = ?";
        ResultSet rs;
        try {
            conn= getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,userDto.getU_id());
            rs=pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Transactional
    public UserDto Login_Temp(UserDto userDto){
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        UserDto ud = new UserDto();
        String sql = "Select * from user where u_id = ? and u_pw = ?";
        try {
            conn=getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,userDto.getU_id());
            pstmt.setString(2,userDto.getU_pw());
            rs=pstmt.executeQuery();

            if(rs.next()){
                ud.setU_name(rs.getString("u_name"));
                ud.setU_no(rs.getInt("u_no"));
                ud.setU_id(rs.getString("u_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ud;
    }

}
