package com.itedu.spring.integration.helloworld;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

public class HellowWorldMain {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/integration/helloWorldDemo.xml", HellowWorldMain.class);
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);

		inputChannel.send(new GenericMessage<String>("From Client Hello!"));
		System.out.println("==> Hello,365itedu! " + outputChannel.receive(0).getPayload());

	}

}
