package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Drive;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.DriveCollectionPage;
import com.microsoft.graph.requests.DriveCollectionRequestBuilder;
import com.microsoft.graph.requests.DriveRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DriveGraphService
        implements
        ICrudGraphService<Drive, DriveCollectionPage> {

    DriveCollectionRequestBuilder collectionBuilder;
    DriveRequestBuilder builder;

    public DriveGraphService(DriveCollectionRequestBuilder collectionBuilder) {
        this.collectionBuilder = collectionBuilder;
    }

    public DriveGraphService(DriveRequestBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Drive create(@Nonnull Drive item) {
        Assert.notNull(item, "item");
        return this.builder().buildRequest().post(item);
    }

    @Override
    public Drive update(@Nonnull Drive item) {
        Assert.notNull(item, "item");
        return this.builder(item.id).buildRequest().patch(item);
    }

    @Override
    public Drive delete(@Nonnull Drive item) {
        Assert.notNull(item, "item");
        return this.builder(item.id).buildRequest().delete();
    }

    @Override
    public Drive get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder(id).buildRequest().get();
    }

    @Override
    public DriveCollectionPage list(List<Option> options) {
        return this.builder().buildRequest(options).get();
    }

    @Override
    public DriveCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

    private DriveCollectionRequestBuilder builder() {
        if (this.collectionBuilder == null) {
            throw new UnsupportedOperationException();
        }
        return this.collectionBuilder;
    }

    private DriveRequestBuilder builder(@Nonnull String id) {
        if (this.collectionBuilder == null) {
            return this.builder;
        }
        return this.collectionBuilder.byId(id);
    }

}
