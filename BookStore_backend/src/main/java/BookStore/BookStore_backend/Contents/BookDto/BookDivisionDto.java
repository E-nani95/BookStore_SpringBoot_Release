package BookStore.BookStore_backend.Contents.BookDto;

public class BookDivisionDto {
    int b_count, b_price;
    String b_name, b_author, b_exp, b_img;
    DivisionDto divisionDto;

    public String getB_img() {
        return b_img;
    }

    public void setB_img(String b_img) {
        this.b_img = b_img;
    }

    public DivisionDto getDivisionDto() {
        return divisionDto;
    }

    public void setDivisionDto(DivisionDto divisionDto) {
        this.divisionDto = divisionDto;
    }

    public String getB_exp() {
        return b_exp;
    }

    public void setB_exp(String b_exp) {
        this.b_exp = b_exp;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public int getB_price() {
        return b_price;
    }

    public void setB_price(int b_price) {
        this.b_price = b_price;
    }

    public int getB_count() {
        return b_count;
    }

    public void setB_count(int b_count) {
        this.b_count = b_count;
    }
}
