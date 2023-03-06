package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.AttachmentBase;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.AttachmentBaseCollectionPage;
import com.microsoft.graph.requests.AttachmentBaseCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttachmentBaseGraphService
        implements
        ICrudGraphService<AttachmentBase, AttachmentBaseCollectionPage> {

    final AttachmentBaseCollectionRequestBuilder builder;

    @Override
    public AttachmentBase create(@Nonnull AttachmentBase item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public AttachmentBase update(@Nonnull AttachmentBase item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public AttachmentBase delete(@Nonnull AttachmentBase item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public AttachmentBase get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public AttachmentBaseCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public AttachmentBaseCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
