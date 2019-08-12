package com.cafe24.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.dto.JSONResult2;
import com.cafe24.mysite.provider.AdminProvider;
import com.cafe24.mysite.provider.CategoryProvider;
import com.cafe24.mysite.provider.CustomerProvider;
import com.cafe24.mysite.provider.ProductProvider;
import com.cafe24.mysite.vo.CategoryVo;
import com.cafe24.mysite.vo.CustomerVo;
import com.cafe24.mysite.vo.ProductDetailVo;
import com.cafe24.mysite.vo.ProductImageVo;
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
	
	@Transactional
	public void add_product(ProductVo productvo, List<MultipartFile> multipartPhoto) {
		// 상품 우선 등록하기 
		JSONResult2<Long> product_no = productProvider.add_product(productvo);
		
		// 상품 디테일 추가하기
		List<ProductDetailVo> product_detail_list =  productvo.getProduct_detail_list();
		for(ProductDetailVo product_detail_vo : product_detail_list) {
			product_detail_vo.setProduct_no(product_no.getData());
		}
		JSONResult2<List<ProductDetailVo>> _product_detail_list = productProvider.add_product_detail(product_detail_list);

		// 상품 이미지 추가하기
		if(!multipartPhoto.isEmpty()) {
			List<String> url_list = restore(multipartPhoto);
			List<ProductImageVo> product_image_list =  new ArrayList<ProductImageVo>();
			int file_idx = 0;
			for(int i=0; i<multipartPhoto.size(); i++) {
				product_image_list.add(new ProductImageVo(product_no.getData(), url_list.get(file_idx),"Y",null));
				file_idx += 1; 
			}
			JSONResult2<List<ProductImageVo>> _product_image_list = productProvider.add_product_image(product_image_list);
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

	public List<String> restore(List<MultipartFile> images) {
		String url = null;
		List<String> url_list = new ArrayList<String>();
		try {
	        for(MultipartFile image : images) {
				// 파일 정보
				String originFilename = image.getOriginalFilename();
				String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
				// 서버에서 저장 할 파일 이름
				String saveFileName = genSaveFileName(extName);
	
				writeFile(image, saveFileName);
				url = saveFileName;
				// 파일 저장 후 List로 URL 받기
				url_list.add(url);
	        }
		} catch (IOException e) {
			// 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
			// 편의상 RuntimeException을 던진다.
			// throw new FileUploadException();
			throw new RuntimeException(e);
		}
		return url_list;
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
