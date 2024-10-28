package CommandBoard_Project;

// URL 형식의 명령어와 세션 정보

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
}