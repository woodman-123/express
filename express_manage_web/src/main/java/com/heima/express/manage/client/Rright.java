
package com.heima.express.manage.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>rright complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="rright"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="child" type="{http://service.manage.express.heima.com/}rright" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="hasflag" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ismenu" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="levelcount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="parentid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="rightid" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="righttext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="righttype" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="righturl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rright", propOrder = {
    "child",
    "hasflag",
    "ismenu",
    "levelcount",
    "parentid",
    "rightid",
    "righttext",
    "righttype",
    "righturl"
})
public class Rright {

    @XmlElement(nillable = true)
    protected List<Rright> child;
    protected Integer hasflag;
    protected Boolean ismenu;
    protected Integer levelcount;
    protected Integer parentid;
    protected Integer rightid;
    protected String righttext;
    protected Integer righttype;
    protected String righturl;

    /**
     * Gets the value of the child property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the child property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChild().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rright }
     * 
     * 
     */
    public List<Rright> getChild() {
        if (child == null) {
            child = new ArrayList<Rright>();
        }
        return this.child;
    }

    /**
     * ��ȡhasflag���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHasflag() {
        return hasflag;
    }

    /**
     * ����hasflag���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHasflag(Integer value) {
        this.hasflag = value;
    }

    /**
     * ��ȡismenu���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsmenu() {
        return ismenu;
    }

    /**
     * ����ismenu���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsmenu(Boolean value) {
        this.ismenu = value;
    }

    /**
     * ��ȡlevelcount���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLevelcount() {
        return levelcount;
    }

    /**
     * ����levelcount���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLevelcount(Integer value) {
        this.levelcount = value;
    }

    /**
     * ��ȡparentid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * ����parentid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setParentid(Integer value) {
        this.parentid = value;
    }

    /**
     * ��ȡrightid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRightid() {
        return rightid;
    }

    /**
     * ����rightid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRightid(Integer value) {
        this.rightid = value;
    }

    /**
     * ��ȡrighttext���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRighttext() {
        return righttext;
    }

    /**
     * ����righttext���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRighttext(String value) {
        this.righttext = value;
    }

    /**
     * ��ȡrighttype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRighttype() {
        return righttype;
    }

    /**
     * ����righttype���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRighttype(Integer value) {
        this.righttype = value;
    }

    /**
     * ��ȡrighturl���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRighturl() {
        return righturl;
    }

    /**
     * ����righturl���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRighturl(String value) {
        this.righturl = value;
    }

}
