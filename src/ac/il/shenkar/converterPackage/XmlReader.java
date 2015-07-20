
package ac.il.shenkar.converterPackage;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/*
 * 
 * XmlReader Interface - 
 * Every class that inherits this interface, MUST implement it's methods. 
 * 
 */

public interface XmlReader
{
	public void readXml () throws ParserConfigurationException, MalformedURLException, SAXException, IOException;
	public double calculate (double from_rate , double from_unit, double to_rate,double to_unit, double amount);
}
