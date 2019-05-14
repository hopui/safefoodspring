package com.ssafy.model.dto;

import java.util.Date;

public class TakenFood
{
	private String userEmail;
	private String foodName;
	private Date takenTime;
	private int quantity;
	private String foodCode;
	private int haccp;
	
	public TakenFood(){}
	public TakenFood(String userEmail, String foodName, Date takenTime, int quantity, String foodCode, int haccp)
	{
		this.userEmail = userEmail;
		this.foodName = foodName;
		this.takenTime = takenTime;
		this.quantity = quantity;
		this.foodCode = foodCode;
		this.haccp = haccp;
	}
	
	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public Date getTakenTime()
	{
		return takenTime;
	}

	public void setTakenTime(Date takenTime)
	{
		this.takenTime = takenTime;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public String getFoodCode()
	{
		return foodCode;
	}

	public void setFoodCode(String foodCode)
	{
		this.foodCode = foodCode;
	}

	public int getHaccp()
	{
		return haccp;
	}

	public void setHaccp(int haccp)
	{
		this.haccp = haccp;
	}

	public String getFoodName()
	{
		return foodName;
	}
	
	public void setFoodName(String foodName)
	{
		this.foodName = foodName;
	}

	@Override
	public String toString()
	{
		return "TakenFood [userEmail=" + userEmail + ", foodName=" + foodName + ", takenTime=" + takenTime
				+ ", quantity=" + quantity + ", foodCode=" + foodCode + ", haccp=" + haccp + "]";
	}
}