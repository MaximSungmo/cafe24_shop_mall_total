package com.cafe24.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.AdminProvider;
import com.cafe24.mysite.provider.CategoryProvider;
import com.cafe24.mysite.provider.CustomerProvider;
import com.cafe24.mysite.provider.ProductProvider;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.ProductVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminProvider adminProvider;

	@Autowired
	private CategoryProvider categoryProvider;
	
	@Autowired
	private CustomerProvider customerProvider;
	
	@Autowired
	private ProductProvider productProvider;
	
	
	public JSONResult2<List<CategoryVo>> get_category() {
		JSONResult2<List<CategoryVo>> category_list = categoryProvider.get_category_list();
		return category_list;
	}
	
	
	public JSONResult2<List<CustomerVo>> get_customer() {
		JSONResult2<List<CustomerVo>> customer_list = customerProvider.get_customer_list();
		return customer_list;
	}


	public JSONResult2<Boolean> insert_category(CategoryVo categoryvo) {
		return customerProvider.insert_category(categoryvo);
	}
	
	public void add_product(ProductVo productvo, MultipartFile files) {
		Long product_no = 0L;
//		JSONResult2<Long> product_ = productProvider.add_product(productvo);
		
		if(!files.isEmpty()) {
			String url_list = restore(files);
			productvo.getProduct_image_list().get(0).setProduct_no(product_no);
			productvo.getProduct_image_list().get(0).setUrl(url_list);

//			for(int i=0; i<url_list.size(); i++) {
//				productvo.getProduct_image_list().get(i).setProduct_no(product_no);
//				productvo.getProduct_image_list().get(i).setUrl(url_list));
//			}
		}
	}
	
	
//	if(logo_file.isEmpty()) {
//		blogService.updateBlogTitle(blogvo);
//	}else {
//		String url = blogService.restore(logo_file);
//		blogvo.setLogo(url);
//		blogService.updateBlog(blogvo);
//		model.addAttribute("blogvo", blogvo);
//	}
	
	
	
	
	/*
	 * 파일업로드
	 */
	private static final String SAVE_PATH = "/mysite-uploads";

	public String restore(MultipartFile image) {
		String url = null;
//		List<String> url_list = new ArrayList<String>();
		try {
//	        for(MultipartFile image : images) {
				// 파일 정보
				String originFilename = image.getOriginalFilename();
				System.out.println("오리지날 파일"+originFilename);
				String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
				Long size = image.getSize();
	
				// 서버에서 저장 할 파일 이름
				String saveFileName = genSaveFileName(extName);
	
				writeFile(image, saveFileName);
				url = saveFileName;
				// 파일 저장 후 List로 URL 받기
//				url_list.add(url);
//	        }
		} catch (IOException e) {
			// 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
			// 편의상 RuntimeException을 던진다.
			// throw new FileUploadException();
			throw new RuntimeException(e);
		}
		return url;
	}

	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		return fileName;
	}

	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		boolean result = false;

		byte[] data = multipartFile.getBytes();

		File folder = new File(SAVE_PATH);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		return result;
	}


	
	
}
