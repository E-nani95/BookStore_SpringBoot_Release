package BookStore.BookStore_backend.Account.UserDto;

import java.sql.Date;

public class OrderListDto {
    String b_name;
    int u_no,o_count,total_price;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public int getU_no() {
        return u_no;
    }

    public void setU_no(int u_no) {
        this.u_no = u_no;
    }

    public int getO_count() {
        return o_count;
    }

    public void setO_count(int o_count) {
        this.o_count = o_count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
