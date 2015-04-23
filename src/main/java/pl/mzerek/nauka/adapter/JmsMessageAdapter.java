package pl.mzerek.nauka.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

public class JmsMessageAdapter implements MessageListener {
	
	private List<String> messageTable = new ArrayList<>();

	@Override
	public void onMessage(Message message) {
		
//		ObjectMessage message = session.createObjectMessage(someObj);
//		message.setJMSCorrelationID(id);
//		message.setJMSMessageID("xxx");
		
		ActiveMQTextMessage tekst = (ActiveMQTextMessage) message;
		try {
			System.out.println("DUPA " + tekst.getText());
			messageTable.add(tekst.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<String> getMessageList(){
		return messageTable;
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
