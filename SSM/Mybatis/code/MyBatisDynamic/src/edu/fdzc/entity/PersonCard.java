/**
 * 
 */
package edu.fdzc.entity;

/**
 * 身份证类
 * 
 * @author CAI
 *
 */
public class PersonCard {
	private int cardId;
	private String cardInfo;
	
	public int getCardId() {
		return cardId;
	}
	
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	public String getCardInfo() {
		return cardInfo;
	}
	
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	@Override
	public String toString() {
		return "[cardId=" + cardId + ", cardInfo=" + cardInfo + "]";
	}
	
}
