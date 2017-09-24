package com.OSS.ConnectedIoT;

import javafx.beans.property.SimpleStringProperty;

public class NodeModel {
	private SimpleStringProperty NodeName;
	private SimpleStringProperty NodeInfo;
	private SimpleStringProperty NodeAddress;
	private SimpleStringProperty NodeProtocol;
	
	public NodeModel(String NodeName, String NodeInfo, String NodeAddress, String NodeProtocol)
	{
		this.NodeProtocol = new SimpleStringProperty(NodeProtocol);
		this.NodeAddress = new SimpleStringProperty(NodeAddress);
		this.NodeInfo = new SimpleStringProperty(NodeInfo);
		this.NodeName = new SimpleStringProperty(NodeName);
	}
	
	public NodeModel(String NodeName, String NodeInfo, String NodeAddress)
	{
		this.NodeProtocol = new SimpleStringProperty("");
		this.NodeAddress = new SimpleStringProperty(NodeAddress);
		this.NodeInfo = new SimpleStringProperty(NodeInfo);
		this.NodeName = new SimpleStringProperty(NodeName);
	}
	
	public NodeModel(Node input)
	{
		this.NodeProtocol = new SimpleStringProperty(input.getNodeProtocol());
		this.NodeAddress = new SimpleStringProperty(input.getCreateAddress());
		this.NodeInfo = new SimpleStringProperty(input.getProejctInfo());
		this.NodeName = new SimpleStringProperty(input.getNodeName());
	}
	
	public String getName()
	{
		return NodeName.get();
	}
	
	public String getInfo()
	{
		return NodeInfo.get();
	}
	
	public String getAddress()
	{
		return NodeAddress.get();
	}
	
	public String getProtocol()
	{
		return NodeProtocol.get();
	}
	
	public void setName(String name)
	{
		NodeName.set(name);
	}
	
	public void setInfo(String info)
	{
		NodeInfo.set(info);
	}
	
	public void setAddress(String Address)
	{
		NodeAddress.set(Address);
	}
	
	public void setProtocol(String Protocol)
	{
		NodeProtocol.set(Protocol);
	}
}
