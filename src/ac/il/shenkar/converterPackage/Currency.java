package ac.il.shenkar.converterPackage;

/*
 * This class holds the attributes of each coin.
 */

public class Currency
{
	
	private String currencyName;
	private String currencyUnit;
	private String currencyCode;
	private String currencyRate;
	private String change;
	private String country;
	
	/*
	 * Initiating the variables
	 */
	public Currency ()
	{
		currencyName="empty";
		currencyCode="empty";
		country="empty";
		currencyUnit="empty";
		currencyRate="empty";
		change="empty";
	}
	
	/*
	 * Creates a new currency
	 */
	public Currency (String name, String currencyCode, String country, String rate, String change, String unit)
	{
		this.currencyName= name;
		this.currencyCode= currencyCode;
		this.country= country;
		this.currencyRate= rate;
		this.change= change;
		this.currencyUnit=unit;
	}

	public String getCurrencyName()
	{
		return currencyName;
	}

	public void setCurrencyName(String name)
	{
		this.currencyName = name;
	}

	public String getCurrencyUnit()
	{
		return currencyUnit;
	}

	public void setCurrencyUnit(String unit)
	{
		this.currencyUnit = unit;
	}

	public String getCurrencyCode()
	{
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}

	public String getCurrencyRate()
	{
		return currencyRate;
	}

	public void setCurrencyRate(String rate)
	{
		this.currencyRate = rate;
	}

	public String getCurrencyChange()
	{
		return change;
	}

	public void setCurrencyChange(String change)
	{
		this.change = change;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}
	/*
	 * return the currency size
	 */
	 public int getCurrencySize()
	 {
		 return 5;
	 }
	
}
