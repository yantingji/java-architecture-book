package com.itedu.spring.integration.ws.main;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itedu.spring.integration.ws.gateway.TicketGateway;
import com.itedu.spring.integration.ws.types.TicketRequest;
import com.itedu.spring.integration.ws.types.TicketResponse;
import com.itedu.spring.integration.ws.utils.DateUtils;

public class SpringWsClientMain {
  private static ClassPathXmlApplicationContext context;

  public static void main(String[] args) {
     new SpringWsClientMain().getXmlDate ();
  }
  
  private void getXmlDate () {
    String config = "classpath:com/itedu/spring/integration/ws/config/int-ws-config.xml";
    context = new ClassPathXmlApplicationContext();
    context.setConfigLocation(config);
    context.refresh();
    context.registerShutdownHook();

    TicketRequest request = new TicketRequest();
    request.setFilmId("welcome yantingji@126.com");
    request.setQuantity(new BigInteger("3"));
    request.setSessionDate(DateUtils.convertDate(new Date()));
    TicketGateway client = context.getBean(TicketGateway.class);

    TicketResponse response = client.invoke(request);
    System.out.println("365IT学院 Spring WS 例子：" + response.getFilmId() + "欢迎观看........");
    System.exit(0);
  }

}
