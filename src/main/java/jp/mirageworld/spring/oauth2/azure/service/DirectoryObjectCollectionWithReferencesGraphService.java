package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.DirectoryObject;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.DirectoryObjectCollectionPage;
import com.microsoft.graph.requests.DirectoryObjectCollectionWithReferencesRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DirectoryObjectCollectionWithReferencesGraphService
        implements
        ICrudGraphService<DirectoryObject, DirectoryObjectCollectionPage> {

    final DirectoryObjectCollectionWithReferencesRequestBuilder builder;

    @Override
    public DirectoryObject create(@Nonnull DirectoryObject item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public DirectoryObject update(@Nonnull DirectoryObject item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().put(item);
    }

    @Override
    public DirectoryObject delete(@Nonnull DirectoryObject item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public DirectoryObject get(@Nonnull String id) {
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public DirectoryObjectCollectionPage list(List<Option> options) {
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public DirectoryObjectCollectionPage list(Option... options) {
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

}
