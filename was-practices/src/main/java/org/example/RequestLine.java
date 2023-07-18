package org.example;

public class RequestLine {
    private String queryString;
    private final String path;
    private final String method;

    // GET /calculate?operator=11&operator=+&operator=55
    public RequestLine(String requestLine) {
        String[] tokens  = requestLine.split(" ");
        this.method = tokens[0];

        String[] urlTokens = tokens[1].split("\\?");
        this.path = urlTokens[0];

        if(urlTokens.length >=2){
            this.queryString = urlTokens[1];
        }
    }
}
