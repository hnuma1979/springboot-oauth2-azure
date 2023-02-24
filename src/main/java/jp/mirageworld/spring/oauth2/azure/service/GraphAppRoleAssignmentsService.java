package jp.mirageworld.spring.oauth2.azure.service;

import java.util.Optional;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.util.Assert;

import com.microsoft.graph.models.AppRoleAssignment;
import com.microsoft.graph.requests.AppRoleAssignmentCollectionPage;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class GraphAppRoleAssignmentsService extends GraphService {

    /**
    * GET https://graph.microsoft.com/v1.0/users/{id}/appRoleAssignments
    * 
    * @param graph
    * @param id 
    * @return
    */
    public Mono<AppRoleAssignmentCollectionPage> list(
            @Nonnull OAuth2AuthorizedClient graph, @Nonnull String id) {
        log.debug("list");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        return Mono.justOrEmpty(
                Optional.ofNullable(this.client(graph).users(id))
                        .map(b -> b.appRoleAssignments())
                        .map(b -> b.buildRequest().get()));
    }

    /**
    * POST https://graph.microsoft.com/v1.0/users/{id}/appRoleAssignments
    * 
    * @param graph
    * @param id 
    * @return
    */
    public Mono<AppRoleAssignment> add(
            @Nonnull OAuth2AuthorizedClient graph, @Nonnull String id, AppRoleAssignment data) {
        log.debug("add");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        return Mono.justOrEmpty(
                Optional.ofNullable(this.client(graph).users(id))
                        .map(b -> b.appRoleAssignments())
                        .map(b -> b.buildRequest().post(data)));
    }

    /**
    * DELETE https://graph.microsoft.com/v1.0/users/{id}/appRoleAssignments
    * 
    * @param graph
    * @param id 
    * @param dataId 
    * @return
    */
    public Mono<AppRoleAssignment> delete(
            @Nonnull OAuth2AuthorizedClient graph, @Nonnull String id, String dataId) {
        log.debug("delete");
        Assert.notNull(graph, "graph");
        Assert.notNull(id, "id");
        Assert.notNull(dataId, "dataId");
        return Mono.justOrEmpty(
                Optional.ofNullable(this.client(graph).users(id))
                        .map(b -> b.appRoleAssignments(dataId))
                        .map(b -> b.buildRequest().delete()));
    }

}
