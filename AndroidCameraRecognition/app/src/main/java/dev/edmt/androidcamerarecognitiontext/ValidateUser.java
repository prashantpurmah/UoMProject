package dev.edmt.androidcamerarecognitiontext;

import org.json.JSONObject;

public class ValidateUser {

    HttpClient httpClient = new DefaultHttpClient();
    HttpPost post = new HttpPost("http://localhost:8080/Project-100/user/java/insert");
        post.setHeader("content-type", "application/json");

    JSONObject data = new JSONObject();
        try {
        data.put("first_name", f_name);
        data.put("last_name", l_name);
        data.put("email", em);
        StringEntity entity = new StringEntity(data.toString());
        post.setEntity(entity);
        HttpResponse resp = httpClient.execute(post);
    }
}
