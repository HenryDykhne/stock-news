# Data Schema
## Blacklist

| **Name** | **Data Type** | **Description** |
| --- | --- | --- |
| blacklistName | String | The name of the blacklist. |
| restrictedWords | String Array | A list of words that are not allowed to appear in the URL of a valid search result. |

## Stock

| **Name** | **Data Type** | **Description** |
| --- | --- | --- |
| stockName | String | The searchable acronym used for the stock. |
| searchTerms | String Array | A list of words that are used as part of the search query. |

## News JSON

| **Name** | **Data Type** | **Description** |
| --- | --- | --- |
| status | String | Indicates whether the request was successful or not. Options: `ok`, `error`. In the case of error a `code` and `message` property will be populated. |
| total Results | Integer | Indicates the number of relevant results returned. |
| articles | Array[Article] | A list of search results containing source, author, title, description, url, url to the attached image, date of publishing, and a preview of the content. |
| source | JSON Object | A JSON containing the id and the name of the source of the result. |
| id | String | The identifier `id` and a display `name` name for the source this article came from. |
| name | String | The author of the article |
| description | String | A description or snippet from the article. |
| url | String | The direct URL to the article. |
| urlToImage | String | The URL to a relevant image for the article. |
| publishedAt | String | The date and time that the article was published, in UTC (+000) |
| content | String | The unformatted content of the article, where available. This is truncated to 260 chars. |