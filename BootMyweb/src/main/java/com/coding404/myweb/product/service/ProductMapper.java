package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper
public interface ProductMapper {

	public int regist(ProductVO vo); //insert(vo,파일데이터)
	public ArrayList<ProductVO> getList(@Param("criteria")Criteria criteria, @Param("user_id")String user_id);
	public int getTotal(@Param("criteria")Criteria criteria, @Param("user_id")String user_id); //전체게시글 수

	
	public ProductVO getDetail (int prod_id);
	public int update(ProductVO vo);
	public int delete(int prod_id);
	
	
	//카테고리관련
	public ArrayList<CategoryVO> getCategory();
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	
	//파일업로드 insert
	public void registFile(ProductUploadVO vo);
	
	//이미지 조회
	public ArrayList<ProductUploadVO> getImgs(int prod_id);
	
	
}
