package com.learning.hello.contoller;


import java.util.List;

import com.learning.hello.model.Mangata;
import com.learning.hello.model.Player;

public class MangataController {
	static Mangata mangata = new Mangata();
	
	public void addPlayer(String name, int bet, String abbr, String position) {
		mangata.addPlayer(name, bet, abbr, position);
	}
	
	public List<Player> getPlayers(){
		return mangata.getPlayers();
	}
	
	public String drawCard() {
		return mangata.drawCard();
	}
	
	public String getInPileTop() {
		if(mangata.inPileTop == null) {
			return "IN";
		}
		else {
			return mangata.inPileTop.toString();
		}
	}
	
	public String getOutPileTop() {
		if(mangata.outPileTop == null) {
			return "OUT";
		}
		else {
			return mangata.outPileTop.toString();
		}
	}
	
	public String getResult() {
		return mangata.getWinner();
	}
}