package com.assetowl.mvp.mapper;

/**
 * Provides a base implementation of transform(Collection), simply calling transform(From) for each
 * element and building it into a list.
 *
 * Created by jamespott on 14/2/17.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static java.util.Collections.emptyList;


public abstract class BaseDataMapper<From, To> implements DataMapper<From, To> {
    @Override
    public List<To> transform(Collection<From> froms) {
        if (froms == null || froms.isEmpty()) return emptyList();
        final List<To> tos = new ArrayList<>(froms.size());
        for (From from : froms) {
            tos.add(transform(from));
        }
        return tos;
    }
}
