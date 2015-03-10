package com.spy2k3.server.backend.frames.dao;

import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;
import com.tinkerpop.frames.annotations.gremlin.GremlinGroovy;


/**
 * Created by Vinay on 11/4/14.
 */
public interface OutcomeFrame extends VertexFrame{
    @Property("id")
    public String getId();

    @Property("name")
    public String getName();

    @Property("name")
    public void setName(String name);
    
    @Property("desc")
    public String getDesc();

    @Property("desc")
    public void setDesc(String desc);
    
    @Property("pid")
    public String getPid();

    @Property("pid")
    public void setPid(String pid);
    
    @Property("json")
    public String getJson();

    @Property("json")
    public void setJson(String json);
    
    @Property("owner")
    public String getOwner();

    @Property("owner")
    public void setOwner(String owner);
    
    @Property("created_at")
    public Long getCreated();

    @Property("created_at")
    public void setCreated(long created_at);
    
    @Property("updated_at")
    public Long getUpdated();

    @Property("updated_at")
    public void setUpdated(long updated_at);
    
    @Adjacency(label = "sub")
    public Iterable<OutcomeFrame> getChildren();

    @GremlinGroovy("it.inE.outV")
    public Iterable<OutcomeFrame> getParents();
    
}
