package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicVO {

	private Integer topic_num; 
	private String topic_id;
	private String topic_head;
	private String topic_content;
	private String topic_date;
	
}
