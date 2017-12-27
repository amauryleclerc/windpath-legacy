//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2017.12.25 � 03:23:37 PM CET 
//


package fr.aleclerc.windpath.gpx;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour boundsType complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="boundsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="minlat" use="required" type="{http://www.topografix.com/GPX/1/0}latitudeType" />
 *       &lt;attribute name="minlon" use="required" type="{http://www.topografix.com/GPX/1/0}longitudeType" />
 *       &lt;attribute name="maxlat" use="required" type="{http://www.topografix.com/GPX/1/0}latitudeType" />
 *       &lt;attribute name="maxlon" use="required" type="{http://www.topografix.com/GPX/1/0}longitudeType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boundsType")
public class BoundsType {

    @XmlAttribute(name = "minlat", required = true)
    protected BigDecimal minlat;
    @XmlAttribute(name = "minlon", required = true)
    protected BigDecimal minlon;
    @XmlAttribute(name = "maxlat", required = true)
    protected BigDecimal maxlat;
    @XmlAttribute(name = "maxlon", required = true)
    protected BigDecimal maxlon;

    /**
     * Obtient la valeur de la propri�t� minlat.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinlat() {
        return minlat;
    }

    /**
     * D�finit la valeur de la propri�t� minlat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinlat(BigDecimal value) {
        this.minlat = value;
    }

    /**
     * Obtient la valeur de la propri�t� minlon.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinlon() {
        return minlon;
    }

    /**
     * D�finit la valeur de la propri�t� minlon.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinlon(BigDecimal value) {
        this.minlon = value;
    }

    /**
     * Obtient la valeur de la propri�t� maxlat.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxlat() {
        return maxlat;
    }

    /**
     * D�finit la valeur de la propri�t� maxlat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxlat(BigDecimal value) {
        this.maxlat = value;
    }

    /**
     * Obtient la valeur de la propri�t� maxlon.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxlon() {
        return maxlon;
    }

    /**
     * D�finit la valeur de la propri�t� maxlon.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxlon(BigDecimal value) {
        this.maxlon = value;
    }

}
