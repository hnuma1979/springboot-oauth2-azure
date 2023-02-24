package jp.mirageworld.spring.oauth2.azure.service;

import java.time.ZoneOffset;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BaseGraphService {

    static final List<String> scopes = List.of("https://graph.microsoft.com/.default");

    GraphServiceClient<Request> client(OAuth2AuthorizedClient graph) {
        log.debug("make client");

        AccessToken at = new AccessToken(
                graph.getAccessToken().getTokenValue(),
                graph.getAccessToken().getExpiresAt().atOffset(ZoneOffset.ofHours(9)));

        TokenCredential credential = request -> Mono.justOrEmpty(at);
        TokenCredentialAuthProvider provider = new TokenCredentialAuthProvider(scopes, credential);

        return GraphServiceClient.builder()
                .authenticationProvider(provider)
                .buildClient();
    }

}
