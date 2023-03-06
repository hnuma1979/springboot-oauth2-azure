package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.UserTeamwork;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.UserTeamworkRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserTeamworkGraphService
        implements
        ICrudGraphService<UserTeamwork, UserTeamwork> {

    final UserTeamworkRequestBuilder builder;

    @Override
    public UserTeamwork create(@Nonnull UserTeamwork item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public UserTeamwork update(@Nonnull UserTeamwork item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().patch(item);
    }

    @Override
    public UserTeamwork delete(@Nonnull UserTeamwork item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().delete();
    }

    @Override
    public UserTeamwork get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.buildRequest().get();
    }

    @Override
    public UserTeamwork list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public UserTeamwork list(Option... options) {
        return this.list(List.of(options));
    }

}
