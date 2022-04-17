package com.xxx.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxx.reader.entity.Book;
import com.xxx.reader.exception.BussiException;
import com.xxx.reader.service.BookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/book")
public class MBookController {
    @Resource
    private BookService bookService;

    @GetMapping("/index.html")
    public ModelAndView showBook() {
        return new ModelAndView("/management/book");
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("img")MultipartFile file, HttpServletRequest request) throws IOException {
        // 上传目录
        String upload = request.getServletContext().getResource("/").getPath() + "/upload/";
        // 文件名
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // 文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 保存文件到upload目录
        file.transferTo(new File(upload + fileName + suffix));
        Map<String, Object> ret = new HashMap<>();
        ret.put("errno", 0);
        ret.put("data", new String[]{"/upload/" + fileName + suffix});
        return ret;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> createBook(Book book) {
        Map<String, Object> result = new HashMap<>();
        try {
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            Document doc = Jsoup.parse(book.getDescription()); // 解析图书详情
            Element img = doc.select("img").first();
            String cover = img.attr("src");
            book.setCover(cover);
            bookService.createBook(book);
            result.put("code", 0);
            result.put("msg", "success");
        } catch (BussiException e) {
            e.printStackTrace();
            result.put("code", e.getCode());
            result.put("msg", e.getMsg());
        }
        return result;
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Integer page, Integer limit) {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        IPage<Book> pageObject = bookService.paging(null, null, page, limit);
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("code", "0");
        ret.put("msg", "success");
        ret.put("data", pageObject.getRecords());
        ret.put("count", pageObject.getTotal());
        return ret;
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Map<String, Object> selectById(@PathVariable("id") Long bookId) {
        Book book = bookService.selectById(bookId);
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", "0");
        ret.put("msg", "success");
        ret.put("data", book);
        return ret;
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateBook(Book book) {
        Map<String, Object> ret = new HashMap<>();
        try {
            Book rawBook = bookService.selectById(book.getBookId());
            rawBook.setBookName(book.getBookName());
            rawBook.setSubTitle(book.getSubTitle());
            rawBook.setAuthor(book.getAuthor());
            rawBook.setCategoryId(book.getCategoryId());
            rawBook.setDescription(book.getDescription());
            Document doc = Jsoup.parse(book.getDescription());
            String cover = doc.select("img").first().attr("src");
            rawBook.setCover(cover);
            bookService.updateBook(rawBook);
            ret.put("code", "0");
            ret.put("msg", "success");
        } catch (BussiException e) {
            e.printStackTrace();
            ret.put("code", e.getCode());
            ret.put("msg", e.getMsg());
        }
        return ret;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteBook(@PathVariable("id") Long bookId) {
        Map<String, Object> ret = new HashMap<>();
        try {
            bookService.deleteBook(bookId);
            ret.put("code", "0");
            ret.put("msg", "success");
        } catch (BussiException e) {
            e.printStackTrace();
            ret.put("code", e.getCode());
            ret.put("msg", e.getMsg());
        }
        return ret;
    }
}
