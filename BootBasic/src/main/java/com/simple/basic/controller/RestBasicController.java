package com.simple.basic.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.simple.basic.command.SimpleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController //스프링에도 존재 RestContronller = controller + responseBody
public class RestBasicController {
	
	@GetMapping("/aaa")
	@ResponseBody
	public String aaa() {
		return "REST API"; //데이터를 담음 - 데이터는 요청이 온 곳으로 대답해 줍니다.
	}

	
	//보내는 데이터에 대한 타입 명시 - produces
	//생략하게 되면 자동으로 JSON타입이 적힙니다.
	@GetMapping(value = "/bbb", produces = "text/plain")
	public String bbb() {
		return "@GetMapping(value = \bbb, produces = application/json)";
	}
	
	
	/////////////////////////////////////////////////////////////////
	//데이터를 보내는 방법
	@GetMapping(value = "/ccc", produces="application/json")
	public SimpleVO ccc() {
		return new SimpleVO("aaa123", 20, "naver", "서울시"); //JSON-binder모듈, 저동으로 JSON형식으로 변환해서 리턴
	}
	
	
	////맵형식 반환
	@GetMapping("/getObject")
	public Map<String, Object> getObject() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("total", 1);
		map.put("data", new SimpleVO("bbb123", 40, "kakao", "경기"));
		
		return map;
	}
	
	//리스트 반환
	@GetMapping("/getObject2")
	public List<SimpleVO> getObject2 (){
		
		List<SimpleVO> list = new ArrayList<> ();
		list.add(new SimpleVO("ccc123", 50, "tesla", "USA"));
		list.add(new SimpleVO("ddd123", 60, "Softbank", "Japan"));
		
		return list;
	}
	
	////////////////////////////////////////////////////////////
	//GET방식으로 데이터를 받는 방법
	
	//get => 쿼리스트링 or URL파라미터를 사용해서 데이터를 넘겨줍니다.
	//getData?id=aaa123&age=20
	
//	@GetMapping("/getData")
//	public String getData(@RequestParam("id") String id,
//						  @RequestParam("age") int age) {
//		
//		System.out.println(id + " & " + age);
//		
//		return "success";
//	}
	@CrossOrigin("*")
	@GetMapping("/getData")
	public String getData(SimpleVO vo) {
		
		System.out.println(vo.toString());
		
		return "success";
	}
//	
//	//getData2/홍길동/1
//	@GetMapping("/getData2/{name}/{age}")
//	public String getData2 ( @PathVariable("name") String name,
//							 @PathVariable("age") int age) {
//		
//		System.out.println(name + "," + age);
//		return "success";
//	}
//	
	
	////////////////////////////////////////////////////////////
	//POST방식으로 데이터를 받음
	//Form형식으로 데이터를 보내고, 받는 방식 - 클라이언트에서는 반드시 FORM으로
	@PostMapping("/getForm")
	public String getForm(SimpleVO vo) {
		
		System.out.println(vo.toString());
		
		return "성공";
	}
	
	//JSON형식으로 데이터를 보내고, 받는방법
	//{"id" : "홍길동","age" : 22,"address" : "서울시"} 
	//@RequestBody로 JSON object를 맵핑합니다.
//	@PostMapping("/getJSON")
//	public String getJSON(@RequestBody SimpleVO vo) {
//		
//		System.out.println(vo.toString());
//		return "success";
//	}
	//@CrossOrigin() -> 특정 주소에 대해서 요청이 들어온 것을 허용해 줍니다. 그리고 응답까지 완료해 줍니다.
	//@CrossOrigin("*")
	@CrossOrigin("*")
	@PostMapping("/getJSON")
	public String getJSON(@RequestBody Map<String, Object> map) { //vscode에서 보내는 데이터 @RequestBody는 JSON타입을 자동으로 Map을 타입을 변환시켜주지만 그전에 vscode에서 날릴때 contentType을 명시해줘야 된다.
		
		System.out.println(map.toString());
		
		return "success";
	}
	
	////////////////////////////////////////////////////////////////
	//consumer -> 반드시 정해진 타입으로 보내라 (명시)
	//produces -> 내가 이 타입으로 보낼게 (명시)
	//클라이언트에서 text를 보내면 실패합니다.
	//consumes ="application/json"은 json타입으로 데이터를 보내달라고 명시해 해줬기 때문에 json타입으로 보내야됩니다.
	@PostMapping(value="/getResult", consumes="application/json")
	public String getResult(@RequestBody String str) {
		
		System.out.println(str);
		
		return "success";
	}
	
	
	///////////////////////////////////////////////////////////////
	//응답문서 상세하게 작성하기 ResponseEntity<보내는타입>
	@PostMapping("/createResponse")
	public ResponseEntity<SimpleVO> createResponse() {
		
		//데이터
		SimpleVO vo = new SimpleVO("abc111", 44, "daum", null);
		
		//헤더
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Authorization", "my json web token"); //권한 - 토큰
		header.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입
		header.add("Access-Control-Allow-Origin", "*"); //서버가 다르더라도 요청을 허용함
		
		return new ResponseEntity<>(vo, header, HttpStatus.OK);
		
		//return new ResponseEntity<>(new SimpleVO("abc111", 44, "daum", null), HttpStatus.OK);
		
		
	}
	
	
//	1.
//	클라이언트 fetch
//	요청주소 : /api/v1/getData
//	메서드 : get
//	클라이언트에서 보낼데이터는 : num, name
//	서버에서 보낼데이터는 : SimpleVO
//	받을 수 있는 restAPI를 생성, 클라이언트에서는 fetch로 호출
	
//	@GetMapping("/api/v1/getData")
//	public ResponseEntity<SimpleVO> test01(@RequestParam("num") int num,
//											@RequestParam("name") String name) {
//		System.out.println(num + "," + name);
//		//서비스 -> DAO -> DB
//		
//		return new ResponseEntity<SimpleVO> (new SimpleVO("홍길동", 10, "네이버", "서울시"), HttpStatus.OK);
//		
//	}
	
	
//   2.
//	 클라이언트 fetch요청
//	 요청주소 : /api/v1/getInfo
//	 메서드 : post
//	 클라이언트에서 보낼데이터는 : num, name
//	 서버에서 보낼데이터는 : 리스트<SimpleVO>
//   받을 수 있는 restAPI를 생성, 클라이언트에서는 fetch로 호출
//	 ResponseEntity로 응답
//	@PostMapping("/api/v1/getInfo")
//	public ResponseEntity<List<SimpleVO>> test02 (@RequestBody Map<String, Object> map){
//		
//		System.out.println(map.toString());
//		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
//		header.add("Authorization", "my json web token"); 
//		header.add("Content-Type", "application/json"); 
//		header.add("Access-Control-Allow-Origin", "*");
//		
//		List<SimpleVO> list = new ArrayList<>();
//		list.add(new SimpleVO("JAVA", 20, "daum", "부산"));
//		list.add(new SimpleVO("CSS", 32, "kakao", "인천"));
//		return new ResponseEntity<> (list, HttpStatus.OK);
//	}
	
	
	@GetMapping("/api/v1/getData")
	public SimpleVO apiGetData(@RequestParam("num") int num,
								@RequestParam("name") String name) {
		
		System.out.println(name + " & " + num);
		
		return new SimpleVO(name, num, null, null);
	}
	
	@PostMapping("/api/v1/getInfo")
	public ResponseEntity<List<SimpleVO>> apiGetInfo(@RequestBody Map<String, Object> map){
		
		
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Authorization", "my json web token"); 
		header.add("Content-Type", "application/json"); 
		header.add("Access-Control-Allow-Origin", "*");
		
		List <SimpleVO> list = new ArrayList <> ();
		SimpleVO vo = new SimpleVO((String)map.get("name"), (Integer)map.get("num"), null, null);
		list.add(vo);
		
		System.out.println(map.toString());
		

		return new ResponseEntity<> (list, header, HttpStatus.OK);
		
	}
	
	
	
	
	
}
