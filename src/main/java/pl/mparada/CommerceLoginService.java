package pl.mparada;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Class simulates BLC commerce rest service
 */
@Service
public class CommerceLoginService {


    public User loginGoogleUser(String token) throws Exception {
        JacksonFactory jacksonFactory = new JacksonFactory();
        // using google library for sending http message with token instead of using customized http client

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new ApacheHttpTransport.Builder().build(), jacksonFactory)
                .setAudience(Collections.singletonList("114213467514-5uoc4jf7ug127q9p3ello8ksqo5vd22s.apps.googleusercontent.com"))
                .build();

        //create new user in commerce
        User user = new User();

        // call google by theirs api client
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {

            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            user.email = email;
            user.username = name;
            // SAVE USER IN BLC and create BLC session

        } else {
            System.out.println("Invalid ID token.");
            throw new Exception("Invalid ID token.");
        }
        return user;
    }
}
