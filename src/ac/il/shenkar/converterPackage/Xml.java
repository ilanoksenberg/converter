
package ac.il.shenkar.converterPackage;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Xml implements XmlReader
{
	/*
	 * Currency list.
	 */
	List <Currency> currencylist;
	public String update;
	/*
	 * Initiating the list
	 */
	public Xml () 
	{
		currencylist= new ArrayList<Currency>();
	}
	
	/*
	 * Converts the XML file to a list.
	 * @see ac.il.shenkar.convertCurrency.XmlReader#readXml()
	 */
	@Override
	public void readXml ()  throws ParserConfigurationException, MalformedURLException, SAXException, IOException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new URL("http://www.boi.org.il/currency.xml").openStream());
		
		//Get the last update from the XML
		update=(doc.getElementsByTagName("LAST_UPDATE").item(0).getTextContent());
		
		
		
		//Adding the Israeli currency;
		Currency cur= new Currency();
		cur.setCurrencyChange("0");
		cur.setCountry("Israel");
		cur.setCurrencyCode("NIS");
		cur.setCurrencyName("Shekel");
		cur.setCurrencyRate("1");
		cur.setCurrencyUnit("1");
		
		currencylist.add(cur);	
		
		
		for (int i=0; i<doc.getElementsByTagName("UNIT").getLength();i++)
		{
			Currency temp= new Currency();
			temp.setCurrencyChange(doc.getElementsByTagName("CHANGE").item(i).getTextContent());
			temp.setCountry(doc.getElementsByTagName("COUNTRY").item(i).getTextContent());
			temp.setCurrencyCode(doc.getElementsByTagName("CURRENCYCODE").item(i).getTextContent());
			temp.setCurrencyName(doc.getElementsByTagName("NAME").item(i).getTextContent());
			temp.setCurrencyRate((doc.getElementsByTagName("RATE").item(i).getTextContent()));
			temp.setCurrencyUnit((doc.getElementsByTagName("UNIT").item(i).getTextContent()));
			currencylist.add(temp);
			
		}
		
		
	}

	public List<Currency> getCurrlist()
	{
		return currencylist;
	}
	
	/*
	 * Calculate the amount the user enters.
	 * @see ac.il.shenkar.convertCurrency.XmlReader#calculate(double, double, double)
	 */
	@Override
	public double calculate(double from_rate , double from_unit, double to_rate,double to_unit, double amount)
	{
		double inRate,outRate,excangeRate;
		inRate = from_rate/from_unit;
		outRate = to_rate/to_unit;
		excangeRate = inRate/outRate;
		return (amount*excangeRate);
	}

	

}
