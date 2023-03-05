package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Group;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.GroupCollectionPage;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupsGraphService {

    final GraphService graphService;

    public Group create(@Nonnull Group user) {
        Assert.notNull(user, "user");
        return this.graphService.groups().buildRequest().post(user);
    }

    public Group update(@Nonnull Group user) {
        Assert.notNull(user, "user");
        return this.graphService.groups(user.id).buildRequest().patch(user);
    }

    public Group delete(@Nonnull Group user) {
        Assert.notNull(user, "user");
        return this.graphService.groups(user.id).buildRequest().delete();
    }

    public Group get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.graphService.groups(id).buildRequest().get();
    }

    public GroupCollectionPage list(List<Option> options) {
        return this.graphService.groups().buildRequest(options).get();
    }

    public GroupCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
