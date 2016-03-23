/*
 * Copyright (c) 2016. Henry Addo Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.addhen.voto.sdk.test.util;

import com.addhen.voto.sdk.test.BaseTestCase;
import com.addhen.voto.sdk.util.StringUtils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Henry Addo
 */
public class StringUtilsTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldBeDigitsOnly() {
        assertTrue(StringUtils.isDigitsOnly("02456"));
    }

    @Test
    public void shouldBeNonDigitsOnly() {
        assertFalse(StringUtils.isDigitsOnly("0239str303"));
    }

    @Test
    public void shouldBeEmptyString() {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void shouldBeEmptyWhenNull() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void shouldNotBeEmpty() {
        assertFalse(StringUtils.isEmpty("hello world"));
    }
}
