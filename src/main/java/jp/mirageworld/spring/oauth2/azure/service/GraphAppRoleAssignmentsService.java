package jp.mirageworld.spring.oauth2.azure.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.util.Assert;

import com.microsoft.graph.models.AppRoleAssignment;
import com.microsoft.graph.requests.AppRoleAssignmentCollectionPage;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class GraphAppRoleAssignmentsService extends BaseGraphService {

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
        return Mono.justOrEmpty(this.client(graph).users(id).appRoleAssignments().buildRequest().get());
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
        return Mono.justOrEmpty(this.client(graph).users(id).appRoleAssignments().buildRequest().post(data));
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
        return Mono.justOrEmpty(this.client(graph).users(id).appRoleAssignments(dataId).buildRequest().delete());
    }

}
