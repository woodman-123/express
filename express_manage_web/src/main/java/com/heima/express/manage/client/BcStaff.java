package com.heima.express.manage.client;

/**
 * 从webservice远程返回来的原来是个xml文件，这是为了适合其他语言的调用者
 *但我们这里都是用java语言写的，所以可以把远程的类直接考来用，而不用xml文件
 * @author Administrator
 *
 */


public class BcStaff {
    private String id;

    private String name;

    private String telephone;

    private String haspda;

    private String deltag;

    private String station;

    private String standard;
    
    private String newName;
    

    //下拉菜单显示姓名电话
    public String getNewName() {
		return this.name+":"+this.telephone;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda == null ? null : haspda.trim();
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag == null ? null : deltag.trim();
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }

	@Override
	public String toString() {
		return "BcStaff [id=" + id + ", name=" + name + ", telephone=" + telephone + ", haspda=" + haspda + ", deltag="
				+ deltag + ", station=" + station + ", standard=" + standard + ", newName=" + newName + "]";
	}
    
    
}