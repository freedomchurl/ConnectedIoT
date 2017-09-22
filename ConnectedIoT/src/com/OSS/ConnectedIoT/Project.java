package com.OSS.ConnectedIoT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

// 프로젝트 단위로 관리되는 ConnectedIoT 특성상, 프로젝트를 가지고 있어야한다.
public class Project { 

	private String projectName = null; // 프로젝트 이름
	private String projectInfo = null; // 프로젝트 설명
	private String createData = null; // 프로젝트 생성일
	 
	// 기타 등등 얼마든지 추가될 수 있다
	
	private ArrayList<Node> myNodes = null; // 노드들을 담을 ArrayList가 필요하다
	
	Project()
	{
		myNodes = new ArrayList<Node>(); // ArrayList를 초기화하고 생성하도록 한다.		
	}
	
	Project(String projectName, String projectInfo)
	{
		this.projectInfo = projectInfo;
		this.projectName = projectName;
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat current = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		// 현재 시간을 구해서 문자열로 저장하는 부분
		this.createData = current.format(cal.getTime());
		
		myNodes = new ArrayList<Node>();
		
		// 여기다가 노드들을 추가해야한다.
	}
	
}
