package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.LinkedResource;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.LinkedResourceCollectionPage;
import com.microsoft.graph.requests.LinkedResourceCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinkedResourceGraphService
        implements
        ICrudGraphService<LinkedResource, LinkedResourceCollectionPage> {

    final LinkedResourceCollectionRequestBuilder builder;

    @Override
    public LinkedResource create(@Nonnull LinkedResource item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public LinkedResource update(@Nonnull LinkedResource item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public LinkedResource delete(@Nonnull LinkedResource item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public LinkedResource get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public LinkedResourceCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public LinkedResourceCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
