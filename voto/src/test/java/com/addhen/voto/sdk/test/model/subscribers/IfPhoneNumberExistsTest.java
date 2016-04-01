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

package com.addhen.voto.sdk.test.model.subscribers;

import com.addhen.voto.sdk.model.subscribers.IfPhoneNumberExists;
import com.addhen.voto.sdk.test.BaseTestCase;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Henry Addo
 */
public class IfPhoneNumberExistsTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void shouldBeSkip() {
        assertEquals("skip", IfPhoneNumberExists.SKIP.toString());
    }

    @Test
    public void shouldBeCreateDuplicate() {
        assertEquals("create_duplicate", IfPhoneNumberExists.CREATE_DUPLICATE.toString());
    }

    @Test
    public void shouldBeAddToGroupOnly() {
        assertEquals("add_to_group_only", IfPhoneNumberExists.ADD_TO_GROUP_ONLY.toString());
    }

    @Test
    public void shouldSetSkip() {
        assertEquals(IfPhoneNumberExists.SKIP,
                IfPhoneNumberExists.valueOf(IfPhoneNumberExists.SKIP.name()));
    }

    @Test
    public void shouldKnowNumberOptions() {
        assertEquals(3, IfPhoneNumberExists.values().length);
    }
}
