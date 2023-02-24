package jp.mirageworld.spring.oauth2.azure.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.microsoft.graph.models.DirectoryObject;
import com.microsoft.graph.models.Drive;
import com.microsoft.graph.models.ProfilePhoto;
import com.microsoft.graph.models.User;
import com.microsoft.graph.models.UserChangePasswordParameterSet;
import com.microsoft.graph.requests.DirectoryObjectCollectionWithReferencesPage;
import com.microsoft.graph.requests.EventCollectionPage;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.UserCollectionPage;
import com.microsoft.graph.requests.UserDeltaCollectionPage;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GraphUsersService extends BaseGraphService {

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
        return Mono.justOrEmpty(this.client(graph).users().buildRequest().post(user));
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
        return Mono.justOrEmpty(this.client(graph).users(id).buildRequest().patch(user));
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
        return Mono.justOrEmpty(this.client(graph).users(id).buildRequest().delete());
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
        return Mono.justOrEmpty(this.client(graph).users(id).buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users().delta().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).photo().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).manager().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).messages().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).events().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).drive().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).memberOf().buildRequest().get());
    }

}
