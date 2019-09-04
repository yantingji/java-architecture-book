package stub.client;

import stub.HWSStub;
import stub.HWSStub.EchoResponse;

public class ClientMain {
	public static void main(String[] args) throws Exception {
	    HWSStub stub = new HWSStub();
	    HWSStub.Echo request = new HWSStub.Echo();
	    request.setValue(">>>>>>Client OK :::: "+new java.util.Date().toLocaleString()
	      +"365itedu ");  
	    EchoResponse response = stub.echo(request);
	    System.out.println("Response : " + response.get_return());
	   }

}
