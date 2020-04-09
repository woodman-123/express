
package com.heima.express.manage.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>role complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="role"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="rightNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rights" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="roleid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="rolename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "role", propOrder = {
    "rightNames",
    "rights",
    "roleid",
    "rolename"
})
public class Role {

    protected String rightNames;
    @XmlElement(nillable = true)
    protected List<Integer> rights;
    protected Integer roleid;
    protected String rolename;

    /**
     * 获取rightNames属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRightNames() {
        return rightNames;
    }

    /**
     * 设置rightNames属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRightNames(String value) {
        this.rightNames = value;
    }

    /**
     * Gets the value of the rights property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rights property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRights().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getRights() {
        if (rights == null) {
            rights = new ArrayList<Integer>();
        }
        return this.rights;
    }

    /**
     * 获取roleid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * 设置roleid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRoleid(Integer value) {
        this.roleid = value;
    }

    /**
     * 获取rolename属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置rolename属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRolename(String value) {
        this.rolename = value;
    }

}
