package CommandBoard_Project;

public class Request {
    private String url;
    private Session session;

    public Request(String url, Session session) {
        if (!url.startsWith("/")) {
            this.url = "/" + url;
        } else {
            this.url = url;
        }
        this.session = session;
    }

    public String getUrl() {
        return url;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "Request{url='" + url + "', loggedIn=" + session.isLoggedIn() + "}";
    }
}