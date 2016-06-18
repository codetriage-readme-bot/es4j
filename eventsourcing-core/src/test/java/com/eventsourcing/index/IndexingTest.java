/**
 * Copyright (c) 2016, All Contributors (see CONTRIBUTORS file)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.eventsourcing.index;

import org.testng.annotations.Test;

public class IndexingTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void wrongReference() {
        Indexing.getAttribute(getClass(), "someProperty");
    }

}