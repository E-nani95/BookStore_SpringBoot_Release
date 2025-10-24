package BookStore.BookStore_backend.Account.UserService;

import BookStore.BookStore_backend.Account.UserDao.UserDao;
import BookStore.BookStore_backend.Account.UserDto.BuyorderDto;
import BookStore.BookStore_backend.Account.UserDto.OrderListDto;
import BookStore.BookStore_backend.Account.UserDto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao usersDao) {
        this.userDao = usersDao;
    }

    public boolean UserSignUp(UserDto userDto){
        return userDao.userSignUp(userDto);
    }

    public boolean OrderRecode(BuyorderDto buyorderDto){
        return userDao.orderRecode(buyorderDto);
    }

    public ArrayList<OrderListDto> OrderList(BuyorderDto buyorderDto){return userDao.OrderList(buyorderDto);}

    public boolean OrderDelete(OrderListDto orderListDto){return userDao.DeleteList(orderListDto);}

    public UserDto Login_Temp(UserDto userDto){
        return  userDao.Login_Temp(userDto);
    }

    public boolean DuplicateCheck(UserDto userDto){return userDao.DuplicateCheck(userDto);}
}
