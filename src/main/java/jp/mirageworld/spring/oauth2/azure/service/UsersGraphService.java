package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import org.springframework.util.Assert;

import com.microsoft.graph.models.User;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.requests.UserCollectionPage;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersGraphService {

    final GraphService graphService;

    public User create(@Nonnull User user) {
        Assert.notNull(user, "user");
        return this.graphService.users().buildRequest().post(user);
    }

    public User update(@Nonnull User user) {
        Assert.notNull(user, "user");
        return this.graphService.users(user.id).buildRequest().patch(user);
    }

    public User delete(@Nonnull User user) {
        Assert.notNull(user, "user");
        return this.graphService.users(user.id).buildRequest().delete();
    }

    public User get(@Nonnull String id) {
        Assert.notNull(id, "id");
        return this.graphService.users(id).buildRequest().get();
    }

    public UserCollectionPage list(List<Option> options) {
        return this.graphService.users().buildRequest(options).get();
    }

    public UserCollectionPage list(Option... options) {
        return this.list(List.of(options));
    }

}