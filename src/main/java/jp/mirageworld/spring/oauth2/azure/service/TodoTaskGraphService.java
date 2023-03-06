package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.TodoTask;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.TodoTaskCollectionPage;
import com.microsoft.graph.requests.TodoTaskCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoTaskGraphService
        implements
        ICrudGraphService<TodoTask, TodoTaskCollectionPage> {

    final TodoTaskCollectionRequestBuilder builder;

    @Override
    public TodoTask create(@Nonnull TodoTask item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public TodoTask update(@Nonnull TodoTask item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public TodoTask delete(@Nonnull TodoTask item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public TodoTask get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public TodoTaskCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public TodoTaskCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
