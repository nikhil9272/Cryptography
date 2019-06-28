/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.apache.commons.lang3.StringUtils;

/**
 * This class is used to receive email with attachment.
 *
 * @author codesjava
 */
public class GmailUtilities1 {

    public static void receiveEmail(String pop3Host,
            String mailStoreType, String userName, String password) {
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
                Address[] toAddress
                        = message.getRecipients(Message.RecipientType.TO);
                System.out.println("---------------------------------");
                System.out.println("Details of Email Message "
                        + (i + 1) + " :");
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                //Iterate recipients 
                System.out.println("To: ");
                for (int j = 0; j < toAddress.length; j++) {
                    System.out.println(toAddress[j].toString());
                }

                //Iterate multiparts
                Object content = message.getContent();
                if (content instanceof String) {
                    String body = (String) content;
                    System.out.println(body);
                    System.out.println(body.length());
                    int aaa;
                    for (int j = 0; j < body.length(); j++) {
                        aaa = body.charAt(j);
                        
                        System.out.println(aaa);
                    }

                } else if (content instanceof Multipart) {
                    Multipart mp = (Multipart) content;

                    for (int k = 0; k < 1; k++) {
                        BodyPart bodyPart = mp.getBodyPart(k);
                        InputStream stream
                                = (InputStream) bodyPart.getInputStream();
                        BufferedReader br
                                = new BufferedReader(new InputStreamReader(stream));
                        String a = "";

//	        while (bufferedReader.ready()) {  
//	    	       //System.out.println(bufferedReader.readLine());  
//                        a=bufferedReader.readLine()+a;
//                        
////                        System.out.println("??"+a);
//                }  
                        String rc;
                        String b = "";
                        while ((rc = br.readLine()) != null) {
                       //System.out.println("hey123"+rc);
                            //b+=rc;
                            //System.out.println(rc);
                            b = b + rc + "\n";
                        }
                        System.out.println(b);
                  //String c=StringUtils.substringBefore(b, "<div dir=");
                        //System.out.println(c);
                        //System.out.println("??"+a);   
                        //System.out.println();  
                    }
                }
            }

            //close the folder and store objects
            emailFolder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
// public static String html2text(String html) {
//    return Jsoup.parse(html).text();
//}

    public static void main(String[] args) {
        String pop3Host = "pop.gmail.com";//change accordingly
        String mailStoreType = "pop3";
        final String userName = "chakravyuh222@gmail.com";//change accordingly
        final String password = "nikhiljatin";//change accordingly

        //call receiveEmail
        receiveEmail(pop3Host, mailStoreType, userName, password);
    }
}
