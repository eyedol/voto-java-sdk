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

import com.addhen.voto.sdk.model.subscribers.Status;
import com.addhen.voto.sdk.test.BaseTestCase;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Henry Addo
 */
public class StatusTest extends BaseTestCase {

  @Before public void setUp() throws Exception {
    super.setUp();
  }

  @Test public void shouldSetInActive() {
    assertEquals(Status.INACTIVE, Status.valueOf(Status.INACTIVE.name()));
  }

  @Test public void shouldSetActive() {
    assertEquals(Status.ACTIVE, Status.valueOf(Status.ACTIVE.name()));
  }
}
