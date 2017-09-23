package com.OSS.ConnectedIoT;

import javafx.beans.property.SimpleStringProperty;

public class ProjectModel {

	private SimpleStringProperty projectName;
	private SimpleStringProperty projectInfo;
	private SimpleStringProperty projectDate;
	private SimpleStringProperty projectAddition;
	
	public ProjectModel(String projectName, String projectInfo, String projectDate, String projectAddition)
	{
		this.projectAddition = new SimpleStringProperty(projectAddition);
		this.projectDate = new SimpleStringProperty(projectDate);
		this.projectInfo = new SimpleStringProperty(projectInfo);
		this.projectName = new SimpleStringProperty(projectName);
	}
	
	public ProjectModel(String projectName, String projectInfo, String projectDate)
	{
		this.projectAddition = new SimpleStringProperty("");
		this.projectDate = new SimpleStringProperty(projectDate);
		this.projectInfo = new SimpleStringProperty(projectInfo);
		this.projectName = new SimpleStringProperty(projectName);
	}
	
	public String getName()
	{
		return projectName.get();
	}
	
	public String getInfo()
	{
		return projectInfo.get();
	}
	
	public String getDate()
	{
		return projectDate.get();
	}
	
	public String getAddition()
	{
		return projectAddition.get();
	}
	
	public void setName(String name)
	{
		projectName.set(name);
	}
	
	public void setInfo(String info)
	{
		projectInfo.set(info);
	}
	
	public void setDate(String date)
	{
		projectDate.set(date);
	}
	
	public void setAddition(String addition)
	{
		projectAddition.set(addition);
	}
}
