package pl.mzerek.nauka.beans;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import pl.mzerek.nauka.adapter.JmsMessageAdapter;

@Component("jmsAppBean")
public class JmsAppBean implements Serializable {

	private static final long serialVersionUID = 1L;

	static String mailboxDestinationThree = "mailbox-destination-four";

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private JmsMessageAdapter jmsMessageAdapter;

	private List<String> lista;

	private String singleMessage;

	// @Bean
	// Receiver receiver() {
	// return new Receiver();
	// }
	//
	// @Bean
	// MessageListenerAdapter adapter(Receiver receiver) {
	// MessageListenerAdapter messageListener = new MessageListenerAdapter(
	// receiver);
	// messageListener.setDefaultResponseQueueName(mailboxDestinationThree);
	// messageListener.setDefaultListenerMethod("receiveMessageOne");
	// return messageListener;
	// }
	//
	// @Bean
	// SimpleMessageListenerContainer container(
	// MessageListenerAdapter messageListener,
	// ConnectionFactory connectionFactory) {
	// SimpleMessageListenerContainer container = new
	// SimpleMessageListenerContainer();
	// container.setMessageListener(messageListener);
	// container.setConnectionFactory(connectionFactory);
	// container.setDestinationName(mailboxDestinationThree);
	// return container;
	// }

	public void sendMessage() {

		if (singleMessage != null) {
			FileSystemUtils.deleteRecursively(new File("activemq-data"));

			// Send a message
			MessageCreator messageCreator = new MessageCreator() {
				@Override
				public Message createMessage(Session session)
						throws JMSException {
					return session.createTextMessage(singleMessage);
				}
			};
			
			System.out.println("Sending a new message from JMS Spring App.");
			jmsTemplate.send(mailboxDestinationThree, messageCreator);
			singleMessage = null;
		}

	}

	public void readMessage() {
		lista = jmsMessageAdapter.getMessageList();
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public String getSingleMessage() {
		return singleMessage;
	}

	public void setSingleMessage(String singleMessage) {
		this.singleMessage = singleMessage;
	}

}
