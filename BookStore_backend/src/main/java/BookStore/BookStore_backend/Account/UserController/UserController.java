package BookStore.BookStore_backend.Account.UserController;

import BookStore.BookStore_backend.Account.UserDto.BuyorderDto;
import BookStore.BookStore_backend.Account.UserDto.OrderListDto;
import BookStore.BookStore_backend.Account.UserDto.UserDto;
import BookStore.BookStore_backend.Account.UserService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/SignUp")
    public ResponseEntity<String> SignUp(@RequestBody UserDto userDto){
//        test_ controller 들어왔는지
        System.out.println("UserDto: "+userDto);
        if(userDto !=null){
            System.out.println("user_id: "+userDto.getU_id());
            System.out.println("user_pw: "+userDto.getU_pw());
            System.out.println("user_name: "+userDto.getU_name());
        }else{
            System.out.println("Nothing");
        }

        boolean rs = userService.UserSignUp(userDto);
        if(rs){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Success\"}");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }
    }

    @PostMapping("/DuplicateCheck")
    public ResponseEntity<String> DuplicateCheck(@RequestBody UserDto userDto){
        System.out.println("DuplicateCheck DTO: "+userDto);
        if(userDto !=null){
            System.out.println("DuplicateCheck user_id: "+userDto.getU_id());
        }else{
            System.out.println("Nothing");
        }
        boolean rs=userService.DuplicateCheck(userDto);
        //True가 이미 존재하는거라서 Fail
        if(rs){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Success\"}");
        }
    }

    @PostMapping("/OrderDelete")
    public ResponseEntity<String> OrderDelete(@RequestBody OrderListDto orderListDto){
        System.out.println("checkOrederList!!!");
        System.out.println("orderListDto: "+orderListDto);
        if(orderListDto !=null){
            System.out.println("b_no: "+orderListDto.getB_name());
        }else{
            System.out.println("Nothing");
        }

        boolean rs=userService.OrderDelete(orderListDto);

        if(rs){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Success\"}");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }

    }

    @PostMapping("/OrderList")
    public ArrayList<OrderListDto> OrderList(@RequestBody BuyorderDto buyorderDto){
        System.out.println("checkOrederList!!!");
        System.out.println("buyorderDto: "+buyorderDto);
        if(buyorderDto !=null){
            System.out.println("b_no: "+buyorderDto.getB_no());
            System.out.println("o_date: "+buyorderDto.getO_date());
            System.out.println("u_no: "+buyorderDto.getU_no());
        }else{
            System.out.println("Nothing");
        }

        return userService.OrderList(buyorderDto);
    }

    @PostMapping("/OrderRecode")
    public ResponseEntity<String> OrderRecode(@RequestBody BuyorderDto buyorderDto){
//        test_ controller 들어왔는지
        System.out.println("buyorderDto: "+buyorderDto);
        if(buyorderDto !=null){
            System.out.println("b_no: "+buyorderDto.getB_no());
            System.out.println("o_date: "+buyorderDto.getO_date());
            System.out.println("u_no: "+buyorderDto.getU_no());
        }else{
            System.out.println("Nothing");
        }

        boolean rs = userService.OrderRecode(buyorderDto);
        if(rs){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Success\"}");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }
    }
    @PostMapping("/LoginTemp")
    public UserDto LoginTemp(@RequestBody UserDto userDto){
//    public ResponseEntity<String> LoginTemp(@RequestBody UserDto userDto){
        //        test_ controller 들어왔는지
        System.out.println("UserDto: "+userDto);
        if(userDto !=null){
            System.out.println("user_id: "+userDto.getU_id());
            System.out.println("user_pw: "+userDto.getU_id());
            System.out.println("user_id: "+userDto.getU_id());
        }else{
            System.out.println("Nothing");
        }
//        return userService.Login_Temp(userDto);
        return userService.Login_Temp(userDto);
//        System.out.println("test");
//        String responseString = "u_name: \"" + "Perfect" + "\""+", u_pw: \"" + "Perfect" + "\""+", u_id: \"" + "Perfect" + "\"";
//        return ResponseEntity.ok(responseString);
    }
}
