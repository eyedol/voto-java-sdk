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

package com.addhen.voto.sdk.model.subscribers;

/**
 * @author Henry Addo
 */
public enum IfPhoneNumberExists {
    /** Skip if phone number exists **/
    SKIP("skip"),
    /** Create duplicate if phone number exists **/
    CREATE_DUPLICATE("create_duplicate"),
    /** Add to group only if phone number exists **/
    ADD_TO_GROUP_ONLY("add_to_group_only");

    private final String value;

    IfPhoneNumberExists(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
