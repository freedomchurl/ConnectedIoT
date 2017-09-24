package com.OSS.ConnectedIoT;

// 각각의 프로젝트에 추가하게 되는 노드들을 담는 클래스. IoT디바이스 명 등의 정보가 필요하다.
public class Node {

	
	private String NodeName; // Node의 이름을 저장한다.
	private String ProtocolType; // 통신하는 프로토콜의 타입을 지정한다.
	private String NodeInfo; // 노드에 대한 설명
	private String NodeLocation; // 노드 위치 등등.. 이건 String이 아니라 다른걸로 대체하는것이 좋을 수도 있겠다.
	private String NodeAddress; // 노드의 IP주소나, http주소 등을 담도록 한다.
	
	
	// 그외 어떤것들이 필요할지 계속 단계적으로 추가해 나가도록 한다.
	
	Node(String name, String protocol, String info, String address)
	{
		this.NodeAddress = address;
		this.ProtocolType = protocol;
		this.NodeInfo = info;
		this.NodeName = name;
		this.NodeLocation = "";
	}

	public String getNodeName()
	{
		return this.NodeName;
	}
	
	public String getProejctInfo()
	{
		return this.NodeInfo;
	}
	
	public String getCreateAddress()
	{
		return this.NodeAddress;
	}
	
	public String getNodeProtocol()
	{
		return this.ProtocolType;
	}
}
