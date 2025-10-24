package BookStore.BookStore_backend.Contents.BookController;

import BookStore.BookStore_backend.Contents.BookDto.BookDetailDto;
import BookStore.BookStore_backend.Contents.BookDto.BookDivisionDto;
import BookStore.BookStore_backend.Contents.BookDto.BookListDto;
import BookStore.BookStore_backend.Contents.BookService.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    @Value ("${file.upload-dir}")
    private String imgPath;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/AddBook")
    public ResponseEntity<String> AddBook(@RequestBody BookDivisionDto bookDivisionDto){
        // ===== 디버깅 코드 추가 =====
        System.out.println("Controller에 들어온 데이터: " + bookDivisionDto);
        if (bookDivisionDto != null) {
//            System.out.println("b_no: " + bookDivisionDto.getDivisionDto().getD_no());
            System.out.println("b_name: " + bookDivisionDto.getB_name());
            System.out.println("b_img: " + bookDivisionDto.getB_img());

            System.out.println("divisionDto 객체: " + bookDivisionDto.getDivisionDto());
        }
        // ===========================

        boolean check_Add = bookService.AddBook(bookDivisionDto);
        if(check_Add){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Success\"}");
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }
    }

    @PostMapping("/FindBook")
    public ArrayList<BookDivisionDto> FindBook(@RequestBody BookDivisionDto bookDivisionDto){
        // ===== 디버깅 코드 추가 =====
        System.out.println("Controller에 들어온 데이터: " + bookDivisionDto);
        if (bookDivisionDto != null) {
//            System.out.println("b_no: " + bookDivisionDto.getDivisionDto().getD_no());
            System.out.println("b_name: " + bookDivisionDto.getB_name());
            System.out.println("b_count: " + bookDivisionDto.getB_count());
            // 여기서 divisionDto가 null인지 확인하는 것이 핵심입니다.
            System.out.println("divisionDto 객체: " + bookDivisionDto.getDivisionDto());
        }
        // ===========================

        return bookService.FindBook(bookDivisionDto);
    }

    @PostMapping("/SearchBook")
    public ArrayList<BookListDto> FindBook(@RequestBody BookListDto bookListDto){
        // ===== 디버깅 코드 추가 =====
        System.out.println("Controller에 들어온 데이터: " + bookListDto);
        if (bookListDto != null) {
//            System.out.println("b_no: " + bookDivisionDto.getDivisionDto().getD_no());
            System.out.println("b_name: " + bookListDto.getB_name());
            System.out.println("b_author: " + bookListDto.getB_author());
            System.out.println("d_name: " + bookListDto.getD_name());
        }
        // ===========================

        return bookService.SearchBook(bookListDto);
    }

    @PostMapping("/DetailBook")
    public BookDetailDto DetailBook(@RequestBody BookDetailDto bookDetailDto){
        System.out.println("DetailController에 들어온 데이터: " + bookDetailDto);
        if (bookDetailDto != null) {
//            System.out.println("b_no: " + bookDivisionDto.getDivisionDto().getD_no());
            System.out.println("b_no: " + bookDetailDto.getB_no());
        }
        // ===========================

        return bookService.DetailBook(bookDetailDto);
    }

    @PostMapping("/UpdateBook")
    public ResponseEntity<String> UpdateBook(@RequestBody BookDivisionDto bookDivisionDto){
        // ===== 디버깅 코드 추가 =====
        System.out.println("Controller에 들어온 데이터: " + bookDivisionDto);
        if (bookDivisionDto != null) {
//            System.out.println("b_no: " + bookDivisionDto.getDivisionDto().getD_no());
            System.out.println("b_name: " + bookDivisionDto.getB_name());
            System.out.println("b_count: " + bookDivisionDto.getB_count());
            // 여기서 divisionDto가 null인지 확인하는 것이 핵심입니다.
            System.out.println("divisionDto 객체: " + bookDivisionDto.getDivisionDto());
        }
        // ===========================

        boolean check_upadate = bookService.UpdateBook(bookDivisionDto);
        if(check_upadate){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Success\"}");
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }
    }
    @PostMapping("/uploadImage")
    public ResponseEntity<String> UploadImage(@RequestParam("file") MultipartFile file){
//        비어 있는지 확인하는 로직인데 클라이언트 단에서 할꺼라서 주석처리함
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("업로드할 파일을 선택해주세요.");
//        }

        String imgName = bookService.loadImage(imgPath, file);
        if(imgName != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \""+imgName+"\"}");
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"Message\" : \"Fail\"}");
        }
    }
}
