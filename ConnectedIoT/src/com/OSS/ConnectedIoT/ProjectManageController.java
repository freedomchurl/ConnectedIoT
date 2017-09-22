package com.OSS.ConnectedIoT;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProjectManageController implements Initializable{

	@FXML private Button Cancel; // 프로젝트 관리에서 선택하거나 취소하는 버튼
	@FXML private Button Choose;
	
	
	// Initialize는, 맨처음에 창이 불려질때 실행되게 된다. 그러므로, 여기서 테이블에 모든 것들을 추가해야한다.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		
		
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
	public void LoadProject()
	{
		
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
