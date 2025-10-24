package BookStore.BookStore_backend.Contents.BookService;

import BookStore.BookStore_backend.Contents.BookDao.BookDao;
import BookStore.BookStore_backend.Contents.BookDto.BookDetailDto;
import BookStore.BookStore_backend.Contents.BookDto.BookDivisionDto;
import BookStore.BookStore_backend.Contents.BookDto.BookListDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class BookService {
    private final BookDao bookDao;
    private Path imgStorageLocation;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public boolean AddBook(BookDivisionDto bookDivisionDto){
        return bookDao.addBook(bookDivisionDto);
    }

    public ArrayList<BookDivisionDto> FindBook(BookDivisionDto bookDivisionDto){
        return bookDao.FindBook(bookDivisionDto);
    }

    public ArrayList<BookListDto> SearchBook(BookListDto bookListDto){
        return bookDao.SearchBook(bookListDto);
    }

    public BookDetailDto DetailBook(BookDetailDto bookDetailDto){
        return bookDao.DetailBook(bookDetailDto);
    }

    public boolean UpdateBook(BookDivisionDto bookDivisionDto){
        return bookDao.updateBook(bookDivisionDto);
    }

    public String loadImage(String path, MultipartFile file){
        this.imgStorageLocation = Paths.get(path).toAbsolutePath().normalize();

        // 파일을 저장할 디렉토리가 없으면 생성
        try {
            Files.createDirectories(this.imgStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("파일을 업로드할 디렉토리를 생성할 수 없습니다.");
        }

        String originName= file.getOriginalFilename();
//        unique한 이름은 뒤에 날짜 붙여서 할꺼임
        String uniqueName = originName; // + 날짜
        try {
            Path targetPath= this.imgStorageLocation.resolve(originName);
            Files.copy(file.getInputStream(), targetPath);
            return uniqueName;

        } catch (IOException e) {
            return null;
        }

    }
}
