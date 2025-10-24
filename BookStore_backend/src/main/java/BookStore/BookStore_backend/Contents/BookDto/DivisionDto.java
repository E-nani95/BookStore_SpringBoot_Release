package BookStore.BookStore_backend.Contents.BookDto;

public class DivisionDto {
    int d_no;
    String d_name;

    public int getD_no() {
        return d_no;
    }

    public void setD_no(int d_no) {
        this.d_no = d_no;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }
}

/*
d_no   -- 분류 번호
d_name -- 분류 이름
PRIMARY KEY(d_no)
 */


