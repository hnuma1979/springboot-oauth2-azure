package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Team;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.TeamCollectionPage;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamGraphService {

    final GraphService graphService;

    public Team create(@Nonnull Team user) {
        Assert.notNull(user, "user");
        return this.graphService.teams().buildRequest().post(user);
    }

    public Team update(@Nonnull Team user) {
        Assert.notNull(user, "user");
        return this.graphService.teams(user.id).buildRequest().patch(user);
    }

    public Team delete(@Nonnull Team user) {
        Assert.notNull(user, "user");
        return this.graphService.teams(user.id).buildRequest().delete();
    }

    public Team get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.graphService.teams(id).buildRequest().get();
    }

    public TeamCollectionPage list(List<Option> options) {
        return this.graphService.teams().buildRequest(options).get();
    }

    public TeamCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
