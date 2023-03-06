package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.CalendarGroup;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.CalendarGroupCollectionPage;
import com.microsoft.graph.requests.CalendarGroupCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalendarGroupGraphService
        implements
        ICrudGraphService<CalendarGroup, CalendarGroupCollectionPage> {

    final CalendarGroupCollectionRequestBuilder builder;

    @Override
    public CalendarGroup create(@Nonnull CalendarGroup item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public CalendarGroup update(@Nonnull CalendarGroup item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public CalendarGroup delete(@Nonnull CalendarGroup item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public CalendarGroup get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public CalendarGroupCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public CalendarGroupCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

    public CalendarGraphService calendar(String id) {
        Assert.notNull(id, "id");
        return new CalendarGraphService(this.builder.byId(id).calendars());
    }

}
