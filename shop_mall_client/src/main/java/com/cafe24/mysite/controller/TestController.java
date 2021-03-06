package com.cafe24.mysite.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.mysite.vo.OrdersDetailVo;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value = "/requestupload1")
    public String requestupload1(MultipartHttpServletRequest mtfRequest) {
        String src = mtfRequest.getParameter("src");
        MultipartFile mf = mtfRequest.getFile("file");

        String path = "C:\\image\\";

        String originFileName = mf.getOriginalFilename(); // 원본 파일 명
        long fileSize = mf.getSize(); // 파일 사이즈

        String safeFile = path + System.currentTimeMillis() + originFileName;

        try {
            mf.transferTo(new File(safeFile));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/";
    }
    
    @RequestMapping(value = "/requestupload2")
    public String requestupload2(MultipartHttpServletRequest mtfRequest) {
        List<MultipartFile> fileList = mtfRequest.getFiles("file");
        String src = mtfRequest.getParameter("src");

        String path = "C:image";

        for (MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename(); // 원본 파일 명
            long fileSize = mf.getSize(); // 파일 사이즈

            System.out.println("originFileName : " + originFileName);
            System.out.println("fileSize : " + fileSize);

            String safeFile = path + System.currentTimeMillis() + originFileName;
            try {
                mf.transferTo(new File(safeFile));
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return "test/multipart_test";
    }
    
    @RequestMapping(value = "/ab", method = RequestMethod.GET)
    public String as() {
        return "test/multipart_test";
    }
    
    @RequestMapping(value = "/ab", method = RequestMethod.POST)
    public String as(@ModelAttribute("orderdetailvo")  OrdersDetailVo orderdetailvo) {
    	System.out.println(orderdetailvo);
    	System.out.println("1==================================================");
        return "test/multipart_test";
    }
    
    @RequestMapping(value = "/gallery")
    public String aas() {
        return "gallery/index";
    }
}
