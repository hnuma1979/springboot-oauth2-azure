package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.ChecklistItem;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.ChecklistItemCollectionPage;
import com.microsoft.graph.requests.ChecklistItemCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChecklistItemGraphService
        implements
        ICrudGraphService<ChecklistItem, ChecklistItemCollectionPage> {

    final ChecklistItemCollectionRequestBuilder builder;

    @Override
    public ChecklistItem create(@Nonnull ChecklistItem item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public ChecklistItem update(@Nonnull ChecklistItem item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public ChecklistItem delete(@Nonnull ChecklistItem item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public ChecklistItem get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public ChecklistItemCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public ChecklistItemCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }
}
