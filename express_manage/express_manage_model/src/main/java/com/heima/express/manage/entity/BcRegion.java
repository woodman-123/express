package com.heima.express.manage.entity;

public class BcRegion {
    private String id;

    private String province;

    private String city;

    private String district;

    private String postcode;

    private String shortcode;

    private String citycode;
    
    private String regionStr;
    

    public String getRegionStr() {
		return province+city+"市"+district;
	}

	public void setRegionStr(String regionStr) {
		this.regionStr = regionStr;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode == null ? null : shortcode.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

	@Override
	public String toString() {
		return "BcRegion [id=" + id + ", province=" + province + ", city=" + city + ", district=" + district
				+ ", postcode=" + postcode + ", shortcode=" + shortcode + ", citycode=" + citycode + ", regionStr="
				+ regionStr + "]";
	}



    
    
    
}