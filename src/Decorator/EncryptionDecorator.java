package Decorator;

import ActorProperties.IActor;
import ActorProperties.Message;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class EncryptionDecorator extends ActorDecorator {

    public EncryptionDecorator(IActor actor) {
        super(actor);
    }

    /**
     * Method to process a message and decrypt it
     * @param message Message of the codified queue
     */
    @Override
    public void process(Message message) throws InterruptedException {
        try {
            message.setBody(decrypt(message.getBody()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        super.process(message);

    }

    /**
     * Method to add a codified message to the queue
     * @param message Message that will be codified and sent to the queue
     */
    @Override
    public void send(Message message) {
        try {
            message.setBody(encript(message.getBody()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message of the codified code: From->"+message.getFrom()+" Message->"+message.getBody());
        super.send(message);
    }

    /**
     * Method that enables us to codify a string
     * @param s String to be codified
     * @return String codified
     * @throws UnsupportedEncodingException
     */
    private static String encript(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    /**
     * Method for decrypting a string
     * @param s Codified string that we want to decrypt
     * @return Decrypted string
     * @throws UnsupportedEncodingException
     */
    private static String decrypt(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
}
