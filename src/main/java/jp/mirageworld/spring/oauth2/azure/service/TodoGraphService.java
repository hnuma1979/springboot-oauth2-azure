package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Todo;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.TodoRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoGraphService implements
        ICrudGraphService<Todo, Todo> {

    final TodoRequestBuilder builder;

    @Override
    public Todo create(@Nonnull Todo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public Todo update(@Nonnull Todo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().patch(item);
    }

    @Override
    public Todo delete(@Nonnull Todo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().delete();
    }

    public Todo get() {
        return this.builder.buildRequest().get();
    }

    @Override
    public Todo get(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Todo list(List<Option> options) {
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public Todo list(Option... options) {
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    public TodoTaskListGraphService list() {
        return new TodoTaskListGraphService(this.builder.lists());
    }
}
