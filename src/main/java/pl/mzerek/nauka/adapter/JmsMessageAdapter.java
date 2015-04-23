package pl.mzerek.nauka.adapter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

public class JmsMessageAdapter implements MessageListener {

	@Override
	public void onMessage(Message message) {
		
//		ObjectMessage message = session.createObjectMessage(someObj);
//		message.setJMSCorrelationID(id);
//		message.setJMSMessageID("xxx");
		
		ActiveMQTextMessage tekst = (ActiveMQTextMessage) message;
		try {
			System.out.println("DUPA " + tekst.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
//	
//	private void sendResult(final SomeResult someObj) {/	
//
//		template.send(responseQueue, new MessageCreator() {
//
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				ObjectMessage message = session.createObjectMessage(someObj);
//				message.setJMSCorrelationID(id);
//				message.setJMSMessageID("ID");
//				return message;
//			}
//
//		});
//	}

}
