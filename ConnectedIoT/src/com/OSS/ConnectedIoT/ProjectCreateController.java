package com.OSS.ConnectedIoT;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

// 프로젝트 생성을 하는 창에서 이벤트처리를 담당할 Controller

public class ProjectCreateController implements Initializable{

	@FXML private Button Create;
	@FXML private Button CancelCreate;
	
	@FXML private TextField projectName;
	@FXML private TextArea projectInfo;
	
	@FXML private ComboBox protocolBox;
	
	@FXML private Button AddNode;
	@FXML private TextField deviceInfo;
	@FXML private TextField deviceName;
	@FXML private TextField deviceAddress;
	
	@FXML private TableView<NodeModel> nodeTable;
	
	private ArrayList<Node> NodeList = new ArrayList<Node>();
	
	@FXML
	TableColumn<NodeModel,String> tcName ;
	@FXML
	TableColumn<NodeModel,String> tcInfo;
	@FXML
	TableColumn<NodeModel,String> tcAddress;
	@FXML
	TableColumn<NodeModel,String> tcProtocol;
	
	private String[] protocolList = {"TCP","UDP","COAP","MQTT"};
	
	ObservableList<String> protocolOptions = FXCollections.observableArrayList(
	
			protocolList
	);
	
	ObservableList<NodeModel> nodeModelList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		tcName.setCellValueFactory(new PropertyValueFactory<NodeModel,String>("NodeName"));
		tcInfo.setCellValueFactory(new PropertyValueFactory<NodeModel,String>("NodeInfo"));
		tcAddress.setCellValueFactory(new PropertyValueFactory<NodeModel,String>("NodeAddress"));
		tcProtocol.setCellValueFactory(new PropertyValueFactory<NodeModel,String>("NodeProtocol"));
		
		nodeTable.setItems(nodeModelList);
		
		SetProtocolBox();
		
		
		
		Create.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				AddProject(event);
			}
	
		});
		
		
		CancelCreate.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				GoBackToMain(event);
			}
			
		});

		AddNode.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddNode();
			}
			
		});
		
	}

	public void AddNode()
	{
		String devicename = deviceName.getText();
		String deviceaddress = deviceAddress.getText();
		String deviceprotocol = (String) protocolBox.getSelectionModel().getSelectedItem();
		String deviceinfo = deviceInfo.getText();
		
		System.out.println("Try Add node!");
		
		if(devicename != null && !"".equals(devicename) && deviceaddress != null && !"".equals(deviceaddress)
			&& deviceprotocol != null && !"".equals(deviceprotocol))
		{
			Node tmpNode = new Node(devicename,deviceprotocol,deviceinfo,deviceaddress);
			NodeList.add(tmpNode);
			// 새로운 노드를 추가한다. 저장은 프로젝트를 생성함과 동시에 이루어지도록 한다.
			
			nodeModelList.add(new NodeModel(tmpNode));
			System.out.println("Try Add node222!");
		}
		
	
	}
	
	// Protocol ComboBox를 설정한다.
	public void SetProtocolBox()
	{
		protocolBox.setItems(protocolOptions);
	}
	
	
	// 취소 버튼을 눌렀을 때, 원래의 Main Scene을 보여준다.
	public void GoBackToMain(ActionEvent event)
	{
		System.out.println("Close Create Stage");
		
		Stage primaryStage = (Stage) projectName.getScene().getWindow();
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
		
	
	public void AddProject(ActionEvent event)
	{
		System.out.println("Create Project");
		//여기서 일단 유효성 검사를 해야한다
		
		if(checkProject())
		{
			Project thisProject = new Project(projectName.getText(),projectInfo.getText());
			
			GoBackToMain(event);
		}
		else
		{
			System.out.println("오류 추가"); 
			// 오류 메세지 등을 팝업으로 띄워야 한다. 어떤 오류인지와 함께.
		}
	
		
	}
	
	public boolean checkProject()
	{
		// 한개씩 오류를 검사해나가면서 return false를 한다.
		if(checkProjectName() == false)
			return false; 
		
		return true;
	}
	
	public boolean checkProjectName()
	{
		String name = projectName.getText();
		
		if(name == null)
			return false;
		if("".equals(name))
			return false;
		
		return true;
	}
	
	public boolean checkProjectInfo()
	{
		String info = projectInfo.getText();
		
		if(info == null)
			return false;
			
		return true;
	}
	
		
}
