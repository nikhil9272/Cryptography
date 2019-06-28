package cryptography;

/**
 *
 * @author Acer
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties; 
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
 
/**
 * This class is used to receive email with attachment.
 * @author codesjava
 */
public class GmailUtilities { 
 public static void receiveEmail(String pop3Host,
    String mailStoreType, String userName, String password){
    //Set properties
    Properties props = new Properties();
    props.put("mail.store.protocol", "pop3");
    props.put("mail.pop3.host", pop3Host);
    props.put("mail.pop3.port", "995");
    props.put("mail.pop3.starttls.enable", "true");
 
    // Get the Session object.
    Session session = Session.getInstance(props);
 
    try {
        //Create the POP3 store object and connect to the pop store.
	Store store = session.getStore("pop3s");
	store.connect(pop3Host, userName, password);
 
	//Create the folder object and open it in your mailbox.
	Folder emailFolder = store.getFolder("INBOX");
	emailFolder.open(Folder.READ_ONLY);
 
	//Retrieve the messages from the folder object.
	Message[] messages = emailFolder.getMessages();
	System.out.println("Total Message" + messages.length);
 
	//Iterate the messages
	for (int i = 0; i < messages.length; i++) {
	   Message message = messages[i];
	   Address[] toAddress = 
             message.getRecipients(Message.RecipientType.TO);
	     System.out.println("---------------------------------");  
	     System.out.println("Details of Email Message " 
                                                   + (i + 1) + " :");  
	     System.out.println("Subject: " + message.getSubject());  
	     System.out.println("From: " + message.getFrom()[0]);  
 
	     //Iterate recipients 
	     System.out.println("To: "); 
	     for(int j = 0; j < toAddress.length; j++){
	       System.out.println(toAddress[j].toString());
	     }
 
	     //Iterate multiparts
	     Multipart multipart = (Multipart) message.getContent();
	     for(int k = 0; k < multipart.getCount(); k++){
	       BodyPart bodyPart = multipart.getBodyPart(k);  
	       InputStream stream = 
                             (InputStream) bodyPart.getInputStream();  
	       BufferedReader bufferedReader = 
	    	   new BufferedReader(new InputStreamReader(stream));  
 
	        while (bufferedReader.ready()) {  
	    	       System.out.println(bufferedReader.readLine());  
	    	}  
	    	   System.out.println();  
	      }  
	   }
 
	   //close the folder and store objects
	   emailFolder.close(false);
	   store.close();
	} catch (NoSuchProviderException e) {
		e.printStackTrace();
	} catch (MessagingException e){
		e.printStackTrace();
	} catch (Exception e) {
	       e.printStackTrace();
	}
 
    }
 
 public static void main(String[] args) {
  String pop3Host = "pop.gmail.com";//change accordingly
  String mailStoreType = "pop3";	
  final String userName = "chakravyuh222@gmail.com";//change accordingly
  final String password = "nikhiljatin";//change accordingly
 
  //call receiveEmail
  receiveEmail(pop3Host, mailStoreType, userName, password);
 }
}

