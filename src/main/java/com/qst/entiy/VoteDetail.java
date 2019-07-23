package com.qst.entiy;

public class VoteDetail {
	private String optName;
	private int optNumber;
	private int voteId;
	
	public VoteDetail(String optName, int optNumber, int voteId) {
		
		this.optName = optName;
		this.optNumber = optNumber;
		this.voteId = voteId;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public int getOptNumber() {
		return optNumber;
	}
	public void setOptNumber(int optNumber) {
		this.optNumber = optNumber;
	}
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	
}
