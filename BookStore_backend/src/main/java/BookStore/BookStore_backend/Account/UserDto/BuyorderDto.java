package BookStore.BookStore_backend.Account.UserDto;

import java.sql.Date;

/*
    //column 설명
    o_no      -- 주문 번호
    u_no      -- 사용자 번호 (FK)
    b_no      -- 책 번호 (FK)
    o_date    -- 주문 날짜
    o_count   -- 주문 수량
    PRIMARY KEY(o_no),
    FOREIGN KEY(u_no) REFERENCES user(u_no),
    FOREIGN KEY(b_no) REFERENCES book(b_no)

 */


public class BuyorderDto {
    int o_no, u_no, b_no, o_count;
    Date o_date;

    public int getO_no() {
        return o_no;
    }

    public void setO_no(int o_no) {
        this.o_no = o_no;
    }

    public int getU_no() {
        return u_no;
    }

    public void setU_no(int u_no) {
        this.u_no = u_no;
    }

    public int getB_no() {
        return b_no;
    }

    public void setB_no(int b_no) {
        this.b_no = b_no;
    }

    public int getO_count() {
        return o_count;
    }

    public void setO_count(int o_count) {
        this.o_count = o_count;
    }


    public Date getO_date() {
        return o_date;
    }

    public void setO_date(Date o_date) {
        this.o_date = o_date;
    }
}

