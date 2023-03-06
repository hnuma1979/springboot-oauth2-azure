package jp.mirageworld.spring.oauth2.azure.service;

import java.util.List;

import com.microsoft.graph.options.Option;

import jakarta.annotation.Nonnull;

public interface ICrudGraphService<E, L> {

    public E create(@Nonnull E item);

    public E update(@Nonnull E item);

    public E delete(@Nonnull E item);

    public E get(@Nonnull String id);

    public L list(List<Option> options);

    default L list(Option... options) {
        return this.list(List.of(options));
    }
}
