package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.google.common.base.Objects;
import com.microsoft.graph.models.User;
import com.microsoft.graph.models.UserChangePasswordParameterSet;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.UserCollectionPage;
import com.microsoft.graph.requests.UserCollectionRequestBuilder;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersGraphService
        implements
        ICrudGraphService<User, UserCollectionPage> {

    final UserCollectionRequestBuilder builder;

    @Override
    public User create(@Nonnull User item) {
        Assert.notNull(item, "item");
        return this.builder.buildRequest().post(item);
    }

    @Override
    public User update(@Nonnull User item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().patch(item);
    }

    public void passwordChange(@Nonnull String id, @Nonnull String oldPassword, @Nonnull String newPassword) {
        Assert.notNull(id, "id");
        Assert.notNull(oldPassword, "oldPassword");
        Assert.notNull(newPassword, "newPassword");
        Assert.isTrue(!Objects.equal(oldPassword, newPassword), "oldPassword = newPassword");
        this.builder.byId(id)
                .changePassword(UserChangePasswordParameterSet.newBuilder()
                        .withCurrentPassword(oldPassword)
                        .withNewPassword(newPassword)
                        .build())
                .buildRequest().post();
    }

    @Override
    public User delete(@Nonnull User item) {
        Assert.notNull(item, "item");
        return this.builder.byId(item.id).buildRequest().delete();
    }

    @Override
    public User get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.builder.byId(id).buildRequest().get();
    }

    @Override
    public UserCollectionPage list(List<Option> options) {
        return this.builder.buildRequest(options).get();
    }

    @Override
    public UserCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

    public CalendarGroupGraphService calendarGroup(@Nonnull String id) {
        Assert.notNull(id, "id");
        return new CalendarGroupGraphService(this.builder.byId(id).calendarGroups());
    }

    public CalendarGraphService calendar(@Nonnull String id) {
        Assert.notNull(id, "id");
        return new CalendarGraphService(this.builder.byId(id).calendars());
    }

    public EventGraphService events(@Nonnull String id) {
        Assert.notNull(id, "id");
        return new EventGraphService(this.builder.byId(id).events());
    }

    public TodoGraphService todo(@Nonnull String id) {
        Assert.notNull(id, "id");
        return new TodoGraphService(this.builder.byId(id).todo());
    }

    public DriveGraphService drives(@Nonnull String id) {
        Assert.notNull(id, "id");
        return new DriveGraphService(this.builder.byId(id).drives());
    }

    public DriveGraphService drive(@Nonnull String id) {
        Assert.notNull(id, "id");
        return new DriveGraphService(this.builder.byId(id).drive());
    }

}
