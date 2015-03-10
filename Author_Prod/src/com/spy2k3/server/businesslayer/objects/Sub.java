package com.spy2k3.server.businesslayer.objects;

import com.spy2k3.server.businesslayer.objects.Constants.Edgetype;

public class Sub extends GraphEdge{
	
	//constructors
	public Sub(){this.edgetype = Edgetype.SUB;};
	public Sub(String fid,String tid, String label){
		this.inNodeid = fid;
		this.outNodeid =tid;
		this.label = label;
		this.edgetype = Edgetype.SUB;
	};
}
