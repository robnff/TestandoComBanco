package com.example.testandocombanco;

public class Pergunta {
	private int id;
	private String question;
	private String criteria;
	private int resposta;
	
	public Pergunta(String question, String criteria, int resposta) {
		super();
		this.question = question;
		this.criteria = criteria;
		this.resposta = resposta;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public int getResposta() {
		return resposta;
	}
	public void setResposta(int resposta) {
		this.resposta = resposta;
	}
	
	@Override
	public String toString(){
		return this.question;
	}
	
}
