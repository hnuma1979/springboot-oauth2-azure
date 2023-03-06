package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Group;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.GroupCollectionPage;
import com.microsoft.graph.requests.GroupCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupGraphService
        implements
        ICrudGraphService<Group, GroupCollectionPage> {

    final GroupCollectionRequestBuilder builder;

    @Override
    public Group create(@Nonnull Group item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public Group update(@Nonnull Group item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public Group delete(@Nonnull Group item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public Group get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public GroupCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public GroupCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

    public CalendarGraphService calendar(String id) {
        Assert.notNull(id, "id");
        return new CalendarGraphService(this.builder.byId(id).calendar());
    }

    public EventGraphService events(String id) {
        Assert.notNull(id, "id");
        return new EventGraphService(this.builder.byId(id).events());
    }

}
