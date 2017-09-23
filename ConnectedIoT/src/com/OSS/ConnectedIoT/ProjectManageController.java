package com.OSS.ConnectedIoT;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ProjectManageController implements Initializable{

	@FXML private Button Cancel; // 프로젝트 관리에서 선택하거나 취소하는 버튼
	@FXML private Button Choose;
	
	@FXML private TableView<ProjectModel> projects;
	
	private String path = "Projects";
	
	private ObservableList<ProjectModel> projectList = FXCollections.observableArrayList();
	
	// Initialize는, 맨처음에 창이 불려질때 실행되게 된다. 그러므로, 여기서 테이블에 모든 것들을 추가해야한다.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		LoadProject();
		
		
		Cancel.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				GoBackToMain(event);
			}
			
		});
		
		Choose.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
		
		
		
	}
	
	
	// 데이터를 로드하는 메소드
	// 1. 특정 폴더에서 폴더들의 개수를 확인한다
	// 2. 그리고 거기서 폴더들을 체크하고, 거기서 가져오게 된다.
	public void LoadProject()
	{
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
							
							projectList.add(new ProjectModel(AfterProjectName, AfterProjectInfo, ProjectDate));
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}
			projects.setItems(projectList);
		}
		
		
		
	}
	
	// 취소 버튼을 눌렀을 때, 원래의 Main Scene을 보여준다.
	public void GoBackToMain(ActionEvent event)
	{
		System.out.println("Close Create Stage");
		
		Stage primaryStage = (Stage) Cancel.getScene().getWindow();
		primaryStage.setResizable(false);
		
		primaryStage.setTitle("Connected IoT - Main Page");
		
		Parent createScene;
		try {
			createScene = FXMLLoader.load(getClass().getResource("ConnectedIoT.fxml"));
			Scene scene = new Scene(createScene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
