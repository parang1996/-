package typing_battle.commons;

import java.text.DecimalFormat;
import java.util.ArrayList;

import typing_battle.model.WordDTO;

public class UtillClass {
	
	// 숫자를 천 단위마다 , 붙여주는 메소드
	
	public static String strMoney(int money) {
		DecimalFormat deciFormat = new DecimalFormat("#,###");
		return deciFormat.format(money);
	}
	
	
	// wordList에서 랜덤한 10개 추출한 리스트 리턴
	public static ArrayList<WordDTO> randArray(ArrayList<WordDTO> wordList){
		// 0부터 wordList.size -1 까지 (인덱스)의 랜덤 숫자를 10개 뽑는데 중복되지 않게 리스트에 담는다.
		ArrayList<Integer> randIdxList = new ArrayList<Integer>();
		while(randIdxList.size() < 10) {
			int randInt = (int)(Math.random()*wordList.size());
			if(randIdxList.indexOf(randInt) == -1) {
				randIdxList.add(randInt);				
			}
		}
		ArrayList<WordDTO> result = new ArrayList<WordDTO>();
		for(int i = 0; i < randIdxList.size(); i++) {
			result.add(wordList.get(randIdxList.get(i)));
		}
		return result;
	}
}
