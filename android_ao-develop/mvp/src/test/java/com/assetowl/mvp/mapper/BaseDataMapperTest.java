package com.assetowl.mvp.mapper;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jamespott on 14/2/17.
 */

public class BaseDataMapperTest {
    private TestDataMapper mapper;

    @Before
    public void setup() {
        mapper = new TestDataMapper();
    }

    @Test
    public void shouldReturnEmptyListForNullCollection() {
        assertEquals(Collections.emptyList(), mapper.transform((List<Integer>) null));
    }

    @Test
    public void shouldReturnEmptyListForEmptyCollection() {
        assertEquals(Collections.emptyList(), mapper.transform(Collections.<Integer>emptyList()));
    }

    @Test
    public void shouldReturnListOfTransformedItemsForEachItem() {
        List<Integer> froms = Arrays.asList(1, 2, 3);
        assertEquals(Arrays.asList("1", "2", "3"), mapper.transform(froms));
    }

    private class TestDataMapper extends BaseDataMapper<Integer, String> {
        @Override
        public String transform(Integer model) {
            return model.toString();
        }
    }
}