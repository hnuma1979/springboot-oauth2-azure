package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.AssociatedTeamInfo;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.AssociatedTeamInfoCollectionPage;
import com.microsoft.graph.requests.AssociatedTeamInfoCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssociatedTeamInfoGraphService
        implements
        ICrudGraphService<AssociatedTeamInfo, AssociatedTeamInfoCollectionPage> {

    final AssociatedTeamInfoCollectionRequestBuilder builder;

    @Override
    public AssociatedTeamInfo create(@Nonnull AssociatedTeamInfo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public AssociatedTeamInfo update(@Nonnull AssociatedTeamInfo item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public AssociatedTeamInfo delete(@Nonnull AssociatedTeamInfo item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public AssociatedTeamInfo get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public AssociatedTeamInfoCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public AssociatedTeamInfoCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
