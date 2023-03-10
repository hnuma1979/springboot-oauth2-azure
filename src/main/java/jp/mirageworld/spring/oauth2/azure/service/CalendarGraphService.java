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
        return this.builder().buildRequest().post(item);
    }

    @Override
    public Calendar update(@Nonnull Calendar item) {
        Assert.notNull(item, "item");
        return this.builder(item.id).buildRequest().patch(item);
    }

    @Override
    public Calendar delete(@Nonnull Calendar item) {
        Assert.notNull(item, "item");
        return this.builder(item.id).buildRequest().delete();
    }

    @Override
    public Calendar get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder(id).buildRequest().get();
    }

    @Override
    public CalendarCollectionPage list(List<Option> options) {
        return this.builder().buildRequest(options).get();
    }

    @Override
    public CalendarCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

    public EventGraphService events(String id) {
        return new EventGraphService(this.builder(id).events());
    }

    private CalendarCollectionRequestBuilder builder() {
        if (this.collectionBuilder == null) {
            throw new UnsupportedOperationException();
        }
        return this.collectionBuilder;
    }

    private CalendarRequestBuilder builder(@Nonnull String id) {
        if (this.collectionBuilder == null) {
            return this.builder;
        }
        return this.collectionBuilder.byId(id);
    }

}
