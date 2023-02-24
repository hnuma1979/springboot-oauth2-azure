package jp.mirageworld.spring.oauth2.azure.service;

import java.util.Optional;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.microsoft.graph.models.DirectoryObject;
import com.microsoft.graph.models.Drive;
import com.microsoft.graph.models.ProfilePhoto;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.DirectoryObjectCollectionWithReferencesPage;
import com.microsoft.graph.requests.DirectoryObjectCollectionWithReferencesRequestBuilder;
import com.microsoft.graph.requests.DirectoryObjectWithReferenceRequestBuilder;
import com.microsoft.graph.requests.DriveRequestBuilder;
import com.microsoft.graph.requests.EventCollectionPage;
import com.microsoft.graph.requests.EventCollectionRequestBuilder;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.MessageCollectionRequestBuilder;
import com.microsoft.graph.requests.ProfilePhotoRequestBuilder;
import com.microsoft.graph.requests.UserCollectionPage;
import com.microsoft.graph.requests.UserRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GraphUsersService extends GraphService {

    /**
    * https://graph.microsoft.com/v1.0/me
    * 
    * @param graph
    * @return
    */
    public Mono<User> me(OAuth2AuthorizedClient graph) {
        log.debug("me");
        return Mono.justOrEmpty(this.client(graph).me().buildRequest().get());
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users
     * 
     * @param graph
     * @return
     */
    public Mono<UserCollectionPage> list(OAuth2AuthorizedClient graph) {
        log.debug("users");
        return Mono.justOrEmpty(this.client(graph).users().buildRequest().get());
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<User> get(OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<UserRequestBuilder> builder = Optional.ofNullable(this.client(graph).users().byId(id));
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/photo/$value
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<ProfilePhoto> photo(OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<ProfilePhotoRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users().byId(id)).map(b -> b.photo());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/manager
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<DirectoryObject> manager(OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<DirectoryObjectWithReferenceRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users().byId(id)).map(b -> b.manager());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/messages
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<MessageCollectionPage> messages(OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<MessageCollectionRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users().byId(id)).map(b -> b.messages());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/events
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<EventCollectionPage> events(OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<EventCollectionRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users().byId(id)).map(b -> b.events());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/drive
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<Drive> drive(OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<DriveRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users().byId(id)).map(b -> b.drive());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/memberOf
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<DirectoryObjectCollectionWithReferencesPage> memberOf(
            OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("users");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<DirectoryObjectCollectionWithReferencesRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users().byId(id)).map(b -> b.memberOf());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

}
