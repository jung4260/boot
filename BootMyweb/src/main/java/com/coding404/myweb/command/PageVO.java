package com.coding404.myweb.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.coding404.myweb.util.Criteria;

import lombok.Data;

@Data
public class PageVO {

	private int start; //페이지네이션 시작번호
	private int end; //페이지네이션 끝번호
	private boolean prev; //이전버튼 활성화여부
	private boolean next; //다음버튼 활성화여부
	
	private int page; //현재조회하는 페이지번호(criteria가 가지고 있음)
	private int amount; //조회하는 데이터 개수 (criteria가 가지고 있음)
	private int total; //전체데이터 개수
	
	private int realEnd; //진짜 끝번호
	
	private Criteria criteria; //페이지기준
	private List<Integer> pageList; //페이지 시작~끝 item
	
	//생성자 - 계산에 필요함
	public PageVO(Criteria criteria, int total) {
		//생성될 때, 페이지번호, 데이터개수, 전체게스글수 초기값 지정
		this.page = criteria.getPage();
		this.amount = criteria.getAmount();
		this.total = total;
		this.criteria = criteria;
		
		//끝페이지 계산 - 현재조회하는 페이지에 따라 변화
		//ex) page 11이면 끝페이지는 20
		//ex) page 1이변 끝페이지 10
		//ex) page 31이면 끝페이지 40
		this.end = (int)(Math.ceil(this.page/10.0)) * 10 ;
		
		//시작페이지 계산
		//끝페이지 - 페이지네이션 수 - 1
		
		this.start = this.end - 10 + 1 ;
		
		//실제마지막 페이지 재계산 -realEend
		//만약에 총게시글이 54개라면 -> 끝페이지 계산시 10, 실제 끝번호는 6
		//만약에 총게시글수가 163개라면? -> 끝페이지 계산시 20, 실제 끝번호 17
		//(int)올림(총게시글 수 / 화면에 뿌려지는 데이터개수)
		this.realEnd = (int)(Math.ceil(this.total / (double) this.amount));
		
		//끝페이지 다시결정
		//데이터 개수 163개
		//1~10페이지 조회시에는, 끝페이지 번호 10, 진짜 끝번호는 17
		//11~20페이지 조회시에는, 끝페이지 번호 20, 진짜 끝번호는 17
		if(this.end > this.realEnd ) {
			this.end = this.realEnd; //
		}
		
		//이전버튼 활성화 여부
		//start는, 1, 11, 21, 31....
		this.prev = this.start > 1; //1보다 크면 true
		
		//다음버튼 활성화 여부 - 끝페이지 화살표를 나타낼지 말지 연관이 있음. 
		this.next = this.realEnd > this.end; // 
		
		//타임리프는 향상된 for문밖에 지원이 안되서, 페이지번호를 들고 있는 item을 생성
		this.pageList = IntStream.rangeClosed(this.start, this.end)
						.boxed()
						.collect(Collectors.toList());
		
	}

	
}
