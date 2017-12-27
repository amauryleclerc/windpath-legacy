//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2017.12.25 � 03:23:37 PM CET 
//


package fr.aleclerc.windpath.gpx;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.aleclerc.windpath.backend.poj package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.aleclerc.windpath.backend.poj
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Gpx }
     * 
     */
    public Gpx createGpx() {
        return new Gpx();
    }

    /**
     * Create an instance of {@link Gpx.Trk }
     * 
     */
    public Gpx.Trk createGpxTrk() {
        return new Gpx.Trk();
    }

    /**
     * Create an instance of {@link Gpx.Trk.Trkseg }
     * 
     */
    public Gpx.Trk.Trkseg createGpxTrkTrkseg() {
        return new Gpx.Trk.Trkseg();
    }

    /**
     * Create an instance of {@link Gpx.Rte }
     * 
     */
    public Gpx.Rte createGpxRte() {
        return new Gpx.Rte();
    }

    /**
     * Create an instance of {@link BoundsType }
     * 
     */
    public BoundsType createBoundsType() {
        return new BoundsType();
    }

    /**
     * Create an instance of {@link Gpx.Wpt }
     * 
     */
    public Gpx.Wpt createGpxWpt() {
        return new Gpx.Wpt();
    }

    /**
     * Create an instance of {@link Gpx.Trk.Trkseg.Trkpt }
     * 
     */
    public Gpx.Trk.Trkseg.Trkpt createGpxTrkTrksegTrkpt() {
        return new Gpx.Trk.Trkseg.Trkpt();
    }

    /**
     * Create an instance of {@link Gpx.Rte.Rtept }
     * 
     */
    public Gpx.Rte.Rtept createGpxRteRtept() {
        return new Gpx.Rte.Rtept();
    }

}
