package jp.mirageworld.spring.oauth2.azure.service;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Todo;
import com.microsoft.graph.requests.TodoRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoGraphService {

    final TodoRequestBuilder builder;

    public Todo create(@Nonnull Todo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    public Todo update(@Nonnull Todo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().patch(item);
    }

    public Todo delete(@Nonnull Todo item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().delete();
    }

    public Todo get() {
        return this.builder.buildRequest().get();
    }

}
