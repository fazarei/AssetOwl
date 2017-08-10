package com.assetowl.mvp.mapper;

/**
 * Created by jamespott on 14/2/17.
 */

import java.util.Collection;

public interface DataMapper<From, To> {
    To transform(From model);
    Collection<To> transform(Collection<From> models);
}
