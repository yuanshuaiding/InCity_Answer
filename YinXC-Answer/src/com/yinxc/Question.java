package com.yinxc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String content;
	private List<String> choices=new ArrayList<String>();
	private String answer;
	private boolean isAnswered=false;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
}
