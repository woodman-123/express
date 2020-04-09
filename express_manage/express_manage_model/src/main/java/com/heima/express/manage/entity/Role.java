package com.heima.express.manage.entity;

import java.util.List;

public class Role {
    private Integer roleid;

    private String rolename;
    
    private String rightNames;
    
    
    private List<Integer> rights;
    

    public List<Integer> getRights() {
		return rights;
	}

	public void setRights(List<Integer> rights) {
		this.rights = rights;
	}

	public String getRightNames() {
		return rightNames;
	}

	public void setRightNames(String rightNames) {
		this.rightNames = rightNames;
	}

	public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", rolename=" + rolename + ", rightNames=" + rightNames + ", rights=" + rights
				+ "]";
	}

	
    
    
    
    
    
    
    
    
    
    
    
}