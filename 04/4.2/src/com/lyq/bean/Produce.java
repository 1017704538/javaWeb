package com.lyq.bean;
/**
 * 商品对象
 * @author Li YongQiang
 */
public class Produce {
	// 商品名称
	private String name = "Java Web编程词典全能版";
	// 商品价格
	private double price = 698.5;
	// 数量
	private int count = 400;
	// 出厂地址
	private String factoryAdd = "吉林省长春市明日科技有限公司";
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getCount() {
		return count;
	}
	public String getFactoryAdd() {
		return factoryAdd;
	}
}
