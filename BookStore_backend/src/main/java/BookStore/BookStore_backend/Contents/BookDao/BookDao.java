package BookStore.BookStore_backend.Contents.BookDao;

import BookStore.BookStore_backend.Contents.BookDto.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BookDao {
    private DataSource dataSource;

    public BookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Transactional
    public boolean updateBook(BookDivisionDto bookDivisionDto){
        Connection conn;
        PreparedStatement pstmt;
        StringBuilder sql = new StringBuilder("Update book set ");
        ArrayList<Object> params = new ArrayList<>();
        int res=0;
        try {
            conn=getConnection();
            if(bookDivisionDto.getB_name() != null){
                sql.append("b_name = ?, ");
                params.add(bookDivisionDto.getB_name());
            }

            if(bookDivisionDto.getB_author() != null){
                sql.append("b_author = ?, ");
                params.add(bookDivisionDto.getB_author());

            }

            if(bookDivisionDto.getDivisionDto() != null){
                DivisionDto dd = new DivisionDto();
                dd.setD_no(bookDivisionDto.getDivisionDto().getD_no());
                sql.append("d_no = ?, ");
                params.add(dd.getD_no());
            }

            if(bookDivisionDto.getB_count() != 0){
                sql.append("b_count = ?, ");
                params.add(bookDivisionDto.getB_count());
            }

            if(bookDivisionDto.getB_price() != 0){
                sql.append("b_price = ?, ");
                params.add(bookDivisionDto.getB_price());
            }

            if(bookDivisionDto.getB_exp() != null){
                sql.append("b_exp = ?, ");
                params.add(bookDivisionDto.getB_exp());

            }

            if(params.size()>0) {
                sql.setLength(sql.length() - 2);
                sql.append(" where b_name=?");
                params.add(bookDivisionDto.getB_name());
                System.out.println(sql.toString());


                pstmt= conn.prepareStatement(sql.toString());
                for (int i = 1; i < params.size() + 1; i++) {
                    pstmt.setObject(i, params.get(i - 1));
                }
                res = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res >0;
    }

    @Transactional
    public boolean addBook(BookDivisionDto bookDivisionDto){
        Connection conn;
        PreparedStatement pstmt_book,pstmt_select;
        ResultSet rs;
        String sql_select="select * from division where d_name=?";
        String sql_book= "Insert into book (d_no, b_count, b_price, b_name, b_author, b_exp, b_img) values (?,?,?,?,?,?,?)";
        int check_add=0;
        try {
            conn=getConnection();
            pstmt_select=conn.prepareStatement(sql_select);
            pstmt_select.setString(1, bookDivisionDto.getDivisionDto().getD_name());
            rs =pstmt_select.executeQuery();
            int bookNum =0;
            if(rs.next()){
                bookNum = rs.getInt("d_no");
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(bookDivisionDto.getDivisionDto().getD_name());
            System.out.println("b_no:"+bookNum);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");


            pstmt_book=conn.prepareStatement(sql_book);
            pstmt_book.setInt(1, bookNum);
            pstmt_book.setInt(2,bookDivisionDto.getB_count());
            pstmt_book.setInt(3,bookDivisionDto.getB_price());
            pstmt_book.setString(4,bookDivisionDto.getB_name());
            pstmt_book.setString(5,bookDivisionDto.getB_author());
            pstmt_book.setString(6,bookDivisionDto.getB_exp());
            pstmt_book.setString(7,bookDivisionDto.getB_img());
            check_add+=pstmt_book.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check_add>0;
    }

    @Transactional
    public ArrayList<BookListDto> SearchBook(BookListDto bookListDto){
        Connection conn;
        PreparedStatement pstmt_select;
        ResultSet rs_select;
        ArrayList<BookListDto> bld = new ArrayList<>();
        try {
            conn=getConnection();
            if(bookListDto.getB_name() != null){
                String sql="select * from book join division on book.d_no = division.d_no where b_name Like ? ORDER BY b_no ASC";
                pstmt_select=conn.prepareStatement(sql);
                pstmt_select.setString(1,"%"+bookListDto.getB_name()+"%");
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()){
                    BookListDto bdd = new BookListDto();
                    bdd.setB_no(rs_select.getInt("b_no"));
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setD_name(rs_select.getString("d_name"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setB_img(rs_select.getString("b_img"));
                    System.out.println("---------------------------------------");
                    System.out.println(rs_select.getString("b_img"));
                    System.out.println("---------------------------------------");
                    bld.add(bdd);
                }
            } else if (bookListDto.getB_author() != null) {
                String sql="select * from book join division on book.d_no = division.d_no where b_author Like ? ORDER BY b_no ASC";
                pstmt_select=conn.prepareStatement(sql);
                pstmt_select.setString(1,"%"+bookListDto.getB_author()+"%");
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()){
                    BookListDto bdd = new BookListDto();
                    bdd.setB_no(rs_select.getInt("b_no"));
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setD_name(rs_select.getString("d_name"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setB_img(rs_select.getString("b_img"));
                    bld.add(bdd);
                }

            } else if (bookListDto.getD_name() != null) {
                String sql="select * from book join division on book.d_no = division.d_no where d_name Like ? ORDER BY b_no ASC";
                pstmt_select=conn.prepareStatement(sql);
                pstmt_select.setString(1,"%"+bookListDto.getD_name()+"%");
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()) {
                    BookListDto bdd = new BookListDto();
                    bdd.setB_no(rs_select.getInt("b_no"));
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setD_name(rs_select.getString("d_name"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setB_img(rs_select.getString("b_img"));
                    bld.add(bdd);
                }
            } else {
                System.out.println("check");
                String sql="select * from book join division on book.d_no = division.d_no";
                pstmt_select=conn.prepareStatement(sql);
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()){
                    BookListDto bdd = new BookListDto();
                    bdd.setB_no(rs_select.getInt("b_no"));
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setD_name(rs_select.getString("d_name"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setB_img(rs_select.getString("b_img"));
                    bld.add(bdd);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bld;

    }

    @Transactional
    public BookDetailDto DetailBook(BookDetailDto bookDetailDto){
        Connection conn;
        PreparedStatement pstmt_select;
        ResultSet rs_select;
        BookDetailDto bdd = new BookDetailDto();
        try {
            conn=getConnection();
            String sql="select * from book join division on book.d_no = division.d_no where b_no = ?";
            pstmt_select=conn.prepareStatement(sql);
            System.out.println("double_check: "+bookDetailDto.getB_no());
            pstmt_select.setInt(1,bookDetailDto.getB_no());
            rs_select = pstmt_select.executeQuery();
            rs_select.next();
            bdd.setB_no(rs_select.getInt("b_no"));
            bdd.setB_name(rs_select.getString("b_name"));
            bdd.setB_author(rs_select.getString("b_author"));
            bdd.setD_name(rs_select.getString("d_name"));
            bdd.setB_count(rs_select.getInt("b_count"));
            bdd.setB_price(rs_select.getInt("b_price"));
            bdd.setB_exp(rs_select.getString("b_exp"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bdd;

    }


    @Transactional
    public ArrayList<BookDivisionDto> FindBook(BookDivisionDto bookDivisionDto){
        Connection conn;
        PreparedStatement pstmt_select;
        ResultSet rs_select;
        ArrayList<BookDivisionDto> bddl = new ArrayList<>();
        try {
            conn=getConnection();
            if(bookDivisionDto.getB_name() != null){
                System.out.println("test");
                String sql="select * from book join division on book.d_no = division.d_no where b_name Like ?";
                pstmt_select=conn.prepareStatement(sql);
                pstmt_select.setString(1,"%"+bookDivisionDto.getB_name()+"%");
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()){
                    DivisionDto dDto = new DivisionDto();
                    dDto.setD_no(rs_select.getInt("d_no"));
                    dDto.setD_name(rs_select.getString("d_name"));
                    BookDivisionDto bdd = new BookDivisionDto();
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setB_exp(rs_select.getString("b_exp"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setDivisionDto(dDto);
                    bddl.add(bdd);
                }
            } else if (bookDivisionDto.getB_author() != null) {
                String sql="select * from book join division on book.d_no = division.d_no where b_author Like ?";
                pstmt_select=conn.prepareStatement(sql);
                pstmt_select.setString(1,"%"+bookDivisionDto.getB_author()+"%");
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()){
                    DivisionDto dDto = new DivisionDto();
                    dDto.setD_no(rs_select.getInt("d_no"));
                    dDto.setD_name(rs_select.getString("d_name"));
                    BookDivisionDto bdd = new BookDivisionDto();
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setB_exp(rs_select.getString("b_exp"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setDivisionDto(dDto);
                    bddl.add(bdd);
                }

            } else if (bookDivisionDto.getDivisionDto().getD_no() != 0) {
                String sql="select * from book join division on book.d_no = division.d_no where d_no = ?";
                pstmt_select=conn.prepareStatement(sql);
                pstmt_select.setInt(1,bookDivisionDto.getDivisionDto().getD_no());
                rs_select = pstmt_select.executeQuery();
                while(rs_select.next()){
                    DivisionDto dDto = new DivisionDto();
                    dDto.setD_no(rs_select.getInt("d_no"));
                    dDto.setD_name(rs_select.getString("d_name"));
                    BookDivisionDto bdd = new BookDivisionDto();
                    bdd.setB_name(rs_select.getString("b_name"));
                    bdd.setB_author(rs_select.getString("b_author"));
                    bdd.setB_exp(rs_select.getString("b_exp"));
                    bdd.setB_count(rs_select.getInt("b_count"));
                    bdd.setB_price(rs_select.getInt("b_price"));
                    bdd.setDivisionDto(dDto);
                    bddl.add(bdd);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bddl;

    }

}
