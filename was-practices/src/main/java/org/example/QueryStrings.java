package org.example;

import java.util.*;

public class QueryStrings {
	private final List<QueryString> queryStrings = new ArrayList<>();

	public QueryStrings(String queryStringLine) {
		String[] queryStringTokens = queryStringLine.split("&");

		Arrays.stream(queryStringTokens)
			  .forEach(queryString -> {
				  String[] tokens = queryString.split("=");
				  if(tokens.length < 2) {
					  throw new IllegalArgumentException("잘못된 queryString 포맷입니다.");
				  }

				  queryStrings.add(new QueryString(tokens[0], tokens[1]));
			  });
	}

	public String getValue(String key) {
		return this.queryStrings.stream()
						.filter(queryString -> queryString.exists(key))
						.map(QueryString::getValue)
						.findFirst()
						.orElse(null);
	}
}
