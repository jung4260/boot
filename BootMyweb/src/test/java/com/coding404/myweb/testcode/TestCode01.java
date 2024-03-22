package com.coding404.myweb.testcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductMapper;
import com.coding404.myweb.util.Criteria;

@SpringBootTest
public class TestCode01 {

	@Autowired
	ProductMapper productMapper;
	
//	@Test
//	public void test01() {
//		
//		for(int i = 1; i<=100; i++) {
//			ProductVO vo = ProductVO.builder()
//							.prod_enddate("2024-06-05")
//							.prod_writer("admin")
//							.prod_name("test" + i)
//							.prod_price(1000 * i)
//							.prod_count(10 * i)
//							.prod_discount(5)
//							.prod_purchase_yn("N")
//							.prod_content("test" + i)
//							.prod_comment("test" + i)
//							.build();
//			productMapper.regist(vo);
//			
//		}
//	}
	
//	@Test
//	public void test02() {
//		
//		productMapper.getList(new Criteria(3, 10));
//		
//	}
//	
	
//	@Test
//	public void test03() {
//		Criteria cri = new Criteria (1, 10);
//		cri.setSearchName("상");
//		cri.setStartDate("2024-02-05");
//		cri.setSearchPrice("desc");
//		productMapper.getList(cri);
//	}
	
	
	
}
