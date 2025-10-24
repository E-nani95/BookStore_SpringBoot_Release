package BookStore.BookStore_backend.Contents.BookDto;


/*
colum 설명
b_no  -- 책 번호
b_name -- 책 이름
d_no  -- 분류 번호 (FK)
b_author  -- 저자
b_count  -- 재고 수량
b_price -- 가격
b_exp  -- 책 설명
b_img --- 이미지 이름
PRIMARY KEY(b_no),
FOREIGN KEY(d_no) REFERENCES division(d_no)
*/


public class BookDto {
    int b_no, d_no, b_count, b_price;
    String b_name, b_author, b_exp, b_img;

    public String getB_img() {
        return b_img;
    }

    public void setB_img(String b_img) {
        this.b_img = b_img;
    }

    public int getB_no() {
        return b_no;
    }

    public void setB_no(int b_no) {
        this.b_no = b_no;
    }

    public int getD_no() {
        return d_no;
    }

    public void setD_no(int d_no) {
        this.d_no = d_no;
    }

    public int getB_count() {
        return b_count;
    }

    public void setB_count(int b_count) {
        this.b_count = b_count;
    }

    public int getB_price() {
        return b_price;
    }

    public void setB_price(int b_price) {
        this.b_price = b_price;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String getB_exp() {
        return b_exp;
    }

    public void setB_exp(String b_exp) {
        this.b_exp = b_exp;
    }
}



