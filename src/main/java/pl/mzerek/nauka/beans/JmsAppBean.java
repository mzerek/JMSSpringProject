package pl.mzerek.nauka.beans;

import java.io.File;
import java.io.Serializable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import pl.mzerek.nauka.beans.jms.utils.Receiver;

@Component("jmsAppBean")
public class JmsAppBean implements Serializable {

	private static final long serialVersionUID = 1L;

	static String mailboxDestinationThree = "mailbox-destination-three";

	@Autowired
	private JmsTemplate jmsTemplate;

//	@Bean
//	Receiver receiver() {
//		return new Receiver();
//	}
//
//	@Bean
//	MessageListenerAdapter adapter(Receiver receiver) {
//		MessageListenerAdapter messageListener = new MessageListenerAdapter(
//				receiver);
//		messageListener.setDefaultResponseQueueName(mailboxDestinationThree);
//		messageListener.setDefaultListenerMethod("receiveMessageOne");
//		return messageListener;
//	}
//
//	@Bean
//	SimpleMessageListenerContainer container(
//			MessageListenerAdapter messageListener,
//			ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setMessageListener(messageListener);
//		container.setConnectionFactory(connectionFactory);
//		container.setDestinationName(mailboxDestinationThree);
//		return container;
//	}

	public void sendMessage() {

		sendMessageForTwo();
	}

	public void readMessage() {

		getMessageFromTwo();
	}

	private void getMessageFromTwo() {

		

	}

	private void sendMessageForTwo() {
		FileSystemUtils.deleteRecursively(new File("activemq-data"));

		// Send a message
		MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("Message from AppOne!");
			}
		};

		System.out.println("Sending a new message from JMS Spring App.");
		jmsTemplate.send(mailboxDestinationThree, messageCreator);
	}

}
