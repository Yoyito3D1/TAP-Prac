package ActorProperties;

public class Message {
    private Object from;
    private String body;

    public Message(Object from, String body) {
        this.from = from;
        this.body = body;
    }

    public Object getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() { return "Message{" + "from= " + from + ", body='" + body + '\'' + '}'; }
}
