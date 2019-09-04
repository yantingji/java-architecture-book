package stub;

public class HWS {
	public String echo(String value) {  System.out.println(">>>>> Service HelloWorldService: " + value + "   "
		    + new java.util.Date().toLocaleString());
		  return value;
		 }
}
