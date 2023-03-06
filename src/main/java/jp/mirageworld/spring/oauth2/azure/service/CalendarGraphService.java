package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.Calendar;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.CalendarCollectionPage;
import com.microsoft.graph.requests.CalendarCollectionRequestBuilder;
import com.microsoft.graph.requests.CalendarRequestBuilder;

import jakarta.annotation.Nonnull;

public class CalendarGraphService
        implements
        ICrudGraphService<Calendar, CalendarCollectionPage> {

    CalendarCollectionRequestBuilder collectionBuilder;
    CalendarRequestBuilder builder;

    public CalendarGraphService(CalendarCollectionRequestBuilder collectionBuilder) {
        this.collectionBuilder = collectionBuilder;
    }

    public CalendarGraphService(CalendarRequestBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Calendar create(@Nonnull Calendar item) throws UnsupportedOperationException {
        Assert.notNull(item, "item");
        if (this.collectionBuilder == null) {
            throw new UnsupportedOperationException();
        }
        return this.collectionBuilder.buildRequest().post(item);
    }

    @Override
    public Calendar update(@Nonnull Calendar item) {
        Assert.notNull(item, "item");
        if (this.collectionBuilder == null) {
            return this.builder.buildRequest().patch(item);
        }
        return this.collectionBuilder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public Calendar delete(@Nonnull Calendar item) {
        Assert.notNull(item, "item");
        if (this.collectionBuilder == null) {
            return this.builder.buildRequest().delete();
        }
        return this.collectionBuilder.byId(item.id).buildRequest().delete();
    }

    @Override
    public Calendar get(@Nonnull String id) {
        Assert.notNull(id, "id");
        if (this.collectionBuilder == null) {
            return this.builder.buildRequest().get();
        }
        return this.collectionBuilder.byId(id).buildRequest().get();
    }

    @Override
    public CalendarCollectionPage list(List<Option> options) {
        if (this.collectionBuilder == null) {
            throw new UnsupportedOperationException();
        }
        return this.collectionBuilder.buildRequest(options).get();
    }

    @Override
    public CalendarCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}
