package uk.to.and1558.Plugins.mcpcba.http;

public class BodyPublisher {
    String body;

    public BodyPublisher(String body) {
        this.body = body;
    }

    public static BodyPublisher ofString(String body) {
        return new BodyPublisher(body);
    }

	public String getBody() {
		return body;
	}
}
