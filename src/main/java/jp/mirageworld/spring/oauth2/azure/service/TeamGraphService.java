package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Team;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.TeamCollectionPage;
import com.microsoft.graph.requests.TeamCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamGraphService
        implements
        ICrudGraphService<Team, TeamCollectionPage> {

    final TeamCollectionRequestBuilder builder;

    @Override
    public Team create(@Nonnull Team item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public Team update(@Nonnull Team item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public Team delete(@Nonnull Team item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public Team get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public TeamCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public TeamCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
