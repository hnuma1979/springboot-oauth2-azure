package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.MailFolder;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.MailFolderCollectionPage;
import com.microsoft.graph.requests.MailFolderCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MailFolderGraphService
        implements
        ICrudGraphService<MailFolder, MailFolderCollectionPage> {

    final MailFolderCollectionRequestBuilder builder;

    @Override
    public MailFolder create(@Nonnull MailFolder item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public MailFolder update(@Nonnull MailFolder item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public MailFolder delete(@Nonnull MailFolder item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public MailFolder get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public MailFolderCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public MailFolderCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}