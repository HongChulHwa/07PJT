package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setFileName("testFileName");
		product.setManuDate("20190324");
		product.setPrice(22);
		product.setProdDetail("dfg");
		product.setProdName("dfg");
	
		
		productService.addProduct(product);
		
		
		//==> console Ȯ��
		//System.out.println(user);
		
		//==> API Ȯ��
		Assert.assertEquals("testFileName", product.getFileName());
		Assert.assertEquals("20190324", product.getManuDate());
		Assert.assertEquals(22, product.getPrice());
		Assert.assertEquals("dfg", product.getProdDetail());
		Assert.assertEquals("dfg", product.getProdName());
	}

	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		 Product product = productService.getProduct(10042);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("dfg", product.getProdName());
		Assert.assertEquals("20190324", product.getManuDate());
		Assert.assertEquals(22, product.getPrice());
		Assert.assertEquals("dfg", product.getProdDetail());

		product.setProdName("dfg2");
		product.setPrice(222);
		product.setManuDate("20190326");
		product.setProdDetail("dfg3dfg");
		
		productService.updateProduct(product);
		
		
		Assert.assertNotNull(product);
		
	
		Assert.assertEquals("dfg2", product.getProdName());
		Assert.assertEquals(222, product.getPrice());
		Assert.assertEquals("20190326", product.getManuDate());
		Assert.assertEquals("dfg3dfg", product.getProdDetail());
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

		//==> �ʿ��ϴٸ�...
		Product product = new Product();
		product.setFileName("testfilename");
		product.setManuDate("930326");
		product.setPrice(1234);
		product.setProdDetail("detail");
		product.setProdName("testname");
		
		
		
		productService.addProduct(product);
		

	
	}
//	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());	
	 	
		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
////	 
	// @Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword("admin");
	 	Map<String,Object> map = productService.getProductList(search);
	 	

		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	

		//==> console Ȯ��
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 	 
}