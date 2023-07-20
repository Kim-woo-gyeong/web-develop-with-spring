package org.example;

public class RequestLine {
    private QueryStrings queryStrings;
    private final String path;
    private final String method;

    public RequestLine(String method, String path, String queryString) {
        this.method = method;
        this.path = path;
        this.queryStrings = new QueryStrings(queryString);
    }

    // GET /calculate?operator=11&operator=+&operator=55
    public RequestLine(String requestLine) {
        String[] tokens  = requestLine.split(" ");
        this.method = tokens[0];

        String[] urlTokens = tokens[1].split("\\?");
        this.path = urlTokens[0];

        if(urlTokens.length >=2){
            this.queryStrings = new QueryStrings(urlTokens[1]);
        }
    }

	public boolean isGetRequest() {
		return "GET".equals(this.method);
	}

	public boolean matchPath(String urlPath) {
		return urlPath.equals(this.path);
	}

	public QueryStrings getQueryStrings() {
		return this.queryStrings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((queryStrings == null) ? 0 : queryStrings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestLine other = (RequestLine) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (queryStrings == null) {
			if (other.queryStrings != null)
				return false;
		} else if (!queryStrings.equals(other.queryStrings))
			return false;
		return true;
	}
}
