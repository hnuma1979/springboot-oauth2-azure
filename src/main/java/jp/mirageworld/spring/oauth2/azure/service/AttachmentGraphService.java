package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Attachment;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.AttachmentCollectionPage;
import com.microsoft.graph.requests.AttachmentCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttachmentGraphService
        implements
        ICrudGraphService<Attachment, AttachmentCollectionPage> {

    final AttachmentCollectionRequestBuilder builder;

    @Override
    public Attachment create(@Nonnull Attachment item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public Attachment update(@Nonnull Attachment item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public Attachment delete(@Nonnull Attachment item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public Attachment get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public AttachmentCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public AttachmentCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
