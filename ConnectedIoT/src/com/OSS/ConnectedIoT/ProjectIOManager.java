package com.OSS.ConnectedIoT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectIOManager {

	private String path = "Projects";
	
	private ArrayList<Project> returnList = new ArrayList<Project>();
	
	
	
	public ArrayList<Project> LoadProjectData()
	{
		returnList = new ArrayList<Project>();
		
		
		File file = new File(path);
		File [] fileList = null;
		
		if(!file.exists())
		{
			file.mkdirs();
			System.out.println("Created Directory - Projects Folder");
		}
		else // 이미 Projects 폴더가 생성되어있다면
		{
			// 내부에서 디렉토리들을 전부 가져와야한다
			fileList = file.listFiles();
			
			// 이제 폴더와 파일을 구분지어야 한다. 파일이 들어있을수도있다.
			
			for(int i=0;i<fileList.length;i++)
			{
				// 여기서 파일과 폴더를 구분지어보자
				
				if(fileList[i].isDirectory()==true)
				{
					String ProjectDate = fileList[i].getName(); // 프로젝트의 생성시간을 폴더명으로 지정해놓도록 한다.
					
					// 이 부분 파일저장할때 조금 더 해시라던가 다른 방법을 통해서 암호화하거나 할 수 있었으면 좋을 것 같다.
					
					File metaData = new File(path+"//"+ProjectDate+"//ProjectData.data");
					// 프로젝트 내부로 들어간다.
					
					if(metaData.exists()) // 메타 데이터가 존재한다면.
					{
						System.out.println("Load MetaData!");
						try {
							
							Scanner readData = new Scanner(metaData);
							
							String projectName = readData.nextLine(); // 첫 번째 한 줄을 읽는다.
							// ex. ProjectName::프로젝트제목 과 같은 형식으로 작성할 것이다.
						
							String[] projectNameSplit = projectName.split("::");
							
							String AfterProjectName = projectName.substring(projectNameSplit[0].length()+2, projectName.length());
							// projectNameSplit[0]을 제외한 부분이 프로젝트 명이 된다.
							
							
							String projectAddition = readData.nextLine();
							
							String[] projectAdditionSplit = projectAddition.split("::");
							String AfterProjectAddition = projectAddition.substring(projectAdditionSplit[0].length()+2, projectAddition.length());
								
							
							String projectInfo = readData.nextLine();
							String[] projectInfoSplit = projectInfo.split("::");
							
							String AfterProjectInfo = projectInfo.substring(projectInfoSplit[0].length()+2,projectInfo.length()); 
							// 일단 한줄 파싱. 그 이후로 계속 추가해나간다.
							
							
							while(readData.hasNextLine())
							{
								String tempInfo = readData.nextLine();
								//AfterProjectInfo += "\n";
								AfterProjectInfo += tempInfo; // 계속 더해나간다.
							}
							
							returnList.add(new Project(AfterProjectName,AfterProjectInfo,AfterProjectAddition));
							
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}
			
			
		}
		
		// 여기서 ArrayList를 return한다. 이때, size == 0 이면 1개도 담기지않은것.
		return returnList;
		
	}
}
