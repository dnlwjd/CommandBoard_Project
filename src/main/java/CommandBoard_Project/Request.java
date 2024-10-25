package CommandBoard_Project;

public class Request {
    private String url;
    private Session session;

    public Request(String url, Session session) {
        this.url = url;
        this.session = session;
    }

    public String getUrl() {
        return url;
    }

    public Session getSession() {
        return session;
    }
}
