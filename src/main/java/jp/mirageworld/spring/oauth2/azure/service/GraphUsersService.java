package jp.mirageworld.spring.oauth2.azure.service;

import java.util.Optional;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.microsoft.graph.models.DirectoryObject;
import com.microsoft.graph.models.Drive;
import com.microsoft.graph.models.ProfilePhoto;
import com.microsoft.graph.models.User;
import com.microsoft.graph.models.UserChangePasswordParameterSet;
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
import com.microsoft.graph.requests.UserDeltaCollectionPage;
import com.microsoft.graph.requests.UserDeltaCollectionRequestBuilder;
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
    public Mono<User> me(@Nonnull OAuth2AuthorizedClient graph) {
        log.debug("me");
        Assert.notNull(graph, "graph");
        return Mono.justOrEmpty(this.client(graph).me().buildRequest().get());
    }

    /**
    * https://graph.microsoft.com/v1.0/me/changePassword
    * 
    * @param graph
    * @return
    */
    public Mono<Void> changePassword(@Nonnull OAuth2AuthorizedClient graph, String curPassword, String newPassword) {
        log.debug("changePassword");
        Assert.notNull(graph, "graph");
        Assert.notNull(curPassword, "curPassword");
        Assert.notNull(newPassword, "newPassword");
        this.client(graph).me()
                .changePassword(UserChangePasswordParameterSet
                        .newBuilder()
                        .withCurrentPassword(curPassword)
                        .withNewPassword(newPassword)
                        .build())
                .buildRequest()
                .post();
        return Mono.empty();
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users
     * 
     * @param graph
     * @return
     */
    public Mono<UserCollectionPage> list(@Nonnull OAuth2AuthorizedClient graph) {
        log.debug("list");
        Assert.notNull(graph, "graph");
        return Mono.justOrEmpty(this.client(graph).users().buildRequest().get());
    }

    /**
     * POST https://graph.microsoft.com/v1.0/users/
     * 
     * @param graph
     * @param user
     * @return
     */
    public Mono<User> create(@Nonnull OAuth2AuthorizedClient graph, @Nonnull User user) {
        log.debug("create");
        Assert.notNull(graph, "graph");
        Assert.notNull(user, "user");
        return Mono.justOrEmpty(Optional.ofNullable(this.client(graph).users().buildRequest().post(user)));
    }

    /**
     * PATH https://graph.microsoft.com/v1.0/users/{id}
     * 
     * @param graph
     * @param id 
     * @param user
     * @return
     */
    public Mono<User> update(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id, @Nonnull User user) {
        log.debug("update");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Assert.notNull(user, "user");
        return Mono.justOrEmpty(Optional.ofNullable(this.client(graph).users(id).buildRequest().patch(user)));
    }

    /**
     * DELETE https://graph.microsoft.com/v1.0/users/{id}
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<User> delete(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("delete");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        return Mono.justOrEmpty(Optional.ofNullable(this.client(graph).users(id).buildRequest().delete()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<User> get(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("get");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<UserRequestBuilder> builder = Optional.ofNullable(this.client(graph).users(id));
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/delta
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<UserDeltaCollectionPage> delta(@Nonnull OAuth2AuthorizedClient graph) {
        log.debug("photo");
        Assert.notNull(graph, "graph");
        Optional<UserDeltaCollectionRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users()).map(b -> b.delta());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/photo/$value
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<ProfilePhoto> photo(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("photo");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<ProfilePhotoRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users(id)).map(b -> b.photo());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/manager
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<DirectoryObject> manager(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("manager");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<DirectoryObjectWithReferenceRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users(id)).map(b -> b.manager());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/messages
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<MessageCollectionPage> messages(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("messages");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<MessageCollectionRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users(id)).map(b -> b.messages());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/events
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<EventCollectionPage> events(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("events");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<EventCollectionRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users(id)).map(b -> b.events());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

    /**
     * GET https://graph.microsoft.com/v1.0/users/{id}/drive
     * 
     * @param graph
     * @param id 
     * @return
     */
    public Mono<Drive> drive(@Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("drive");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<DriveRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users(id)).map(b -> b.drive());
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
        log.debug("memberOf");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Optional<DirectoryObjectCollectionWithReferencesRequestBuilder> builder = //
                Optional.ofNullable(this.client(graph).users(id)).map(b -> b.memberOf());
        return Mono.justOrEmpty(builder.map(b -> b.buildRequest().get()));
    }

}
