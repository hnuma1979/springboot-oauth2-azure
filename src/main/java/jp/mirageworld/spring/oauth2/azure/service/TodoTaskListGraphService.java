package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.TodoTaskList;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.TodoTaskListCollectionPage;
import com.microsoft.graph.requests.TodoTaskListCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoTaskListGraphService
        implements
        ICrudGraphService<TodoTaskList, TodoTaskListCollectionPage> {

    final TodoTaskListCollectionRequestBuilder builder;

    @Override
    public TodoTaskList create(@Nonnull TodoTaskList item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public TodoTaskList update(@Nonnull TodoTaskList item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public TodoTaskList delete(@Nonnull TodoTaskList item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public TodoTaskList get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public TodoTaskListCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public TodoTaskListCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
