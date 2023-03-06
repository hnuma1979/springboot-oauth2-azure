package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.DateTimeTimeZone;
import com.microsoft.graph.models.Event;
import com.microsoft.graph.models.EventAcceptParameterSet;
import com.microsoft.graph.models.EventCancelParameterSet;
import com.microsoft.graph.models.EventDeclineParameterSet;
import com.microsoft.graph.models.EventForwardParameterSet;
import com.microsoft.graph.models.EventSnoozeReminderParameterSet;
import com.microsoft.graph.models.EventTentativelyAcceptParameterSet;
import com.microsoft.graph.models.Recipient;
import com.microsoft.graph.models.TimeSlot;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.EventCollectionPage;
import com.microsoft.graph.requests.EventCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EventGraphService
        implements
        ICrudGraphService<Event, EventCollectionPage> {

    final EventCollectionRequestBuilder builder;

    @Override
    public Event create(@Nonnull Event item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public Event update(@Nonnull Event item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    @Override
    public Event delete(@Nonnull Event item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public Event get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public EventCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public EventCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

    /**
     * 承認
     */
    public void accept(@Nonnull String id, String comment, boolean response) {
        Assert.notNull(id, "id");
        this.builder.byId(id).accept(
                EventAcceptParameterSet.newBuilder().withComment(comment).withSendResponse(response).build())
                .buildRequest().post();
    }

    /**
     * 仮承認
     */
    public void tentativelyAccept(@Nonnull String id, String comment, boolean response) {
        Assert.notNull(id, "id");
        this.builder.byId(id).tentativelyAccept(
                EventTentativelyAcceptParameterSet.newBuilder().withComment(comment).withSendResponse(response).build())
                .buildRequest().post();
    }

    /**
     * 仮承認（時間の提案）
     */
    public void tentativelyAccept(@Nonnull String id, String comment, TimeSlot time) {
        Assert.notNull(id, "id");
        this.builder.byId(id).tentativelyAccept(
                EventTentativelyAcceptParameterSet.newBuilder()
                        .withComment(comment).withSendResponse(true).withProposedNewTime(time).build())
                .buildRequest().post();
    }

    /**
     * 転送
     */
    public void forward(@Nonnull String id, String comment, List<Recipient> recipients) {
        Assert.notNull(id, "id");
        this.builder.byId(id).forward(
                EventForwardParameterSet.newBuilder().withComment(comment).withToRecipients(recipients).build())
                .buildRequest().post();
    }

    /**
     * キャンセル
     */
    public void cancel(@Nonnull String id, String comment) {
        Assert.notNull(id, "id");
        this.builder.byId(id).cancel(
                EventCancelParameterSet.newBuilder().withComment(comment).build())
                .buildRequest().post();
    }

    /**
     * 辞退
     */
    public void decline(@Nonnull String id, String comment, boolean response) {
        Assert.notNull(id, "id");
        this.builder.byId(id).decline(
                EventDeclineParameterSet.newBuilder().withComment(comment).withSendResponse(response).build())
                .buildRequest().post();
    }

    /**
     * 辞退（時間の提案）
     */
    public void decline(@Nonnull String id, String comment, TimeSlot time) {
        Assert.notNull(id, "id");
        this.builder.byId(id).decline(
                EventDeclineParameterSet.newBuilder()
                        .withComment(comment).withSendResponse(true).withProposedNewTime(time).build())
                .buildRequest().post();
    }

    /**
     * リマインダー
     */
    public void snoozeReminder(@Nonnull String id, DateTimeTimeZone reminder) {
        Assert.notNull(id, "id");
        this.builder.byId(id).snoozeReminder(
                EventSnoozeReminderParameterSet.newBuilder().withNewReminderTime(reminder).build())
                .buildRequest().post();
    }

    /**
     * リマインダー
     */
    public void dismissReminder(@Nonnull String id, DateTimeTimeZone reminder) {
        Assert.notNull(id, "id");
        this.builder.byId(id).dismissReminder().buildRequest().post();
    }

    public AttachmentGraphService attachments(@Nonnull String id) {
        return new AttachmentGraphService(this.builder.byId(id).attachments());
    }

}
