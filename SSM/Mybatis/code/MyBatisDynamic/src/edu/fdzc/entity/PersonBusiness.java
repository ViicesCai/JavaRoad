/**
 * 
 */
package edu.fdzc.entity;

/**
 * 人物业务扩展类
 * 
 * @author CAI
 *
 */
public class PersonBusiness extends Person { // 一个类中要想包含两个已有的类：继承属性多的，重写属性少的
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
		return super.toString() + ", [cardId=" + cardId + ", cardInfo=" + cardInfo + "]";
	}
}
