package com.revature.project0.batch412.keithsantamaria.screens;

import java.util.ArrayList;
import java.util.List;

public abstract class Screen implements IScreen{

	protected String title;
	protected List<String> contents;
	protected List<String> menuOptions;
	protected String inputPrompt;
	protected String footer;

	public Screen(){
		this.contents = new ArrayList<String>();
		this.menuOptions = new ArrayList<String>();
	}

	@Override
	public void show() {
		System.out.println(this.title);
		for (String content : this.contents) {
			System.out.println(content);
		}
		for (String menuOption : this.menuOptions) {
			System.out.println(menuOption);
		}
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getContents() {
		return contents;
	}
	public void setContents(List<String> contents) {
		this.contents = contents;
	}
	public List<String> getMenuOptions() {
		return menuOptions;
	}
	public void setMenuOptions(List<String> menuOptions) {
		this.menuOptions = menuOptions;
	}
	public String getInputPrompt() {
		return inputPrompt;
	}
	public void setInputPrompt(String inputPrompt) {
		this.inputPrompt = inputPrompt;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
}
