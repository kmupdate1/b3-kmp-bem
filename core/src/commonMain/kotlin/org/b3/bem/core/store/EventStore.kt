package org.b3.bem.core.store

import org.b3.bem.core.event.BusinessEvent

interface EventStore {
    fun append(event: BusinessEvent<*>)
    fun append(events: Iterable<BusinessEvent<*>>)
    fun events(): Sequence<BusinessEvent<*>>
}
