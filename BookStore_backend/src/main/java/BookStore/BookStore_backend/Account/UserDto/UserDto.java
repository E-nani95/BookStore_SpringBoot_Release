package BookStore.BookStore_backend.Account.UserDto;


public class UserDto {


    int u_no;
    String u_id,u_pw,u_name;

    public int getU_no() {
        return u_no;
    }

    public void setU_no(int u_no) {
        this.u_no = u_no;
    }
    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_pw() {
        return u_pw;
    }

    public void setU_pw(String u_pw) {
        this.u_pw = u_pw;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
}

/*
u_no  -- 사용자 번호
u_id   -- 사용자 아이디
u_pw   -- 사용자 비밀번호
u_name -- 사용자 이름
PRIMARY KEY(u_no)
 */
