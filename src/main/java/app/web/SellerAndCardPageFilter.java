package app.web;

import java.util.HashMap;
import java.util.Optional;

public class SellerAndCardPageFilter {
	
	public enum MinCondition {
		POOR(7), PLAYED(6), LIGHT_PLAYED(5), GOOD(4), EXCELLENT(3), NEAR_MINT(2), MINT(1);
		
		int condition;
		
		public int getCondition() {
			return this.condition;
		}
		
		MinCondition(int condition) {
			this.condition = condition;
		}		
	}	
	
	private MinCondition minCondition = MinCondition.POOR;	
	private HashMap<String, Integer> languages = new HashMap<String, Integer>();
	/* 0 is no, 1 is yes  */
	private int isFoil = 0;
	private int isSigned = 0;
	private int isPlayset = 0;
	private int isAltered = 0;
	private Optional<Integer> amount = Optional.empty();
	private String apply = "Filter";
	
	public MinCondition getMinCondition() {
		return minCondition;
	}
	public void setMinCondition(MinCondition minCondition) {
		this.minCondition = minCondition;
	}
	public HashMap<String, Integer> getLanguages() {
		return languages;
	}
	public void setLanguages(HashMap<String, Integer> languages) {
		this.languages = languages;
	}
	public int getIsFoil() {
		return isFoil;
	}
	public void setIsFoil(int isFoil) {
		this.isFoil = isFoil;
	}
	public int getIsSigned() {
		return isSigned;
	}
	public void setIsSigned(int isSigned) {
		this.isSigned = isSigned;
	}
	public int getIsPlayset() {
		return isPlayset;
	}
	public void setIsPlayset(int isPlayset) {
		this.isPlayset = isPlayset;
	}
	public int getIsAltered() {
		return isAltered;
	}
	public void setIsAltered(int isAltered) {
		this.isAltered = isAltered;
	}
	public Optional<Integer> getAmount() {
		return amount;
	}
	public void setAmount(Optional<Integer> amount) {
		this.amount = amount;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	
}
