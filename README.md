# BEM SDK

BEM SDK provides a Kotlin DSL for creating, composing, and publishing BEM Facts.

The SDK is designed around a small set of operations:

- `measure` — create a measurement Fact
- `boundary` — compose Facts within a boundary
- `add` — add an existing Fact to a boundary
- `include` — select Facts for publishing
- `fact` — publish Facts individually
- `document` — publish multiple Facts as a document
- `http` — publish Facts over HTTP

## Measurement

A measurement creates a Fact for a specific equipment.

```kotlin
val pump1Measured = Pump1.measure {
    it.electric outflow 120.wh
    it.water outflow 500.m3
}
```

A measurement groups flows observed for the equipment into a single Fact.

Conceptually:

```text
pump1
├── electric OUT 120 Wh
└── water    OUT 500 m3
```

## Boundary

A boundary groups Facts within a context.

```kotlin
val takeWater = boundary("Take Water from river") {
    Pump2.measure {
        it.electric inflow 600.wh
        it.electric outflow 500.wh
        it.water inflow 750.m3
        it.water outflow 730.m3
    }
}
```

Conceptually:

```text
Take Water from river
└── pump2
    ├── electric IN  600 Wh
    ├── electric OUT 500 Wh
    ├── water    IN  750 m3
    └── water    OUT 730 m3
```

A boundary is not limited to a physical location.

It represents a context in which a group of Facts is considered together.

For example, a boundary may represent:

- a farm
- an area
- a plot
- an electrical grid
- a water intake system
- a production process
- another logical or physical scope

## Adding existing Facts

A Fact does not need to be created inside a boundary.

An existing Fact can be added using `add`.

```kotlin
val pump1Measured = Pump1.measure {
    it.electric outflow 120.wh
    it.water outflow 500.m3
}

val takeWater = boundary("Take Water from river") {
    add(pump1Measured)

    Pump2.measure {
        it.electric inflow 600.wh
        it.electric outflow 500.wh
        it.water inflow 750.m3
        it.water outflow 730.m3
    }
}
```

`add` does not create another measurement.

The existing Fact is used as a component of the new boundary Fact while retaining its own identity.

Conceptually:

```text
pump1Measured ──────────────┐
                            │
                            ▼
                 Take Water from river
                 ├── pump1Measured
                 └── pump2 measurement
```

The original `pump1Measured` can continue to exist independently.

## Nested boundaries

Boundaries can be nested.

```kotlin
val grid = boundary("Electric Power Grid") {
    add(pump1Measured)

    boundary("Water Taking") {
        add(takeWater)
    }

    Battery1.measure {
        it.electric inflow 800.wh
        it.electric outflow 200.wh
    }

    Pump3.measure {
        it.electric inflow 50.wh
        it.electric outflow 50.wh
    }
}
```

This allows hierarchical contexts to be represented directly in the DSL.

```text
Electric Power Grid
├── pump1Measured
├── Water Taking
│   └── Take Water from river
│       ├── pump1Measured
│       └── pump2
├── battery1
└── pump3
```

The same mechanism can represent structures such as:

```text
Farm
└── Area
    └── Plot
        └── Location
            └── Equipment
```

without introducing domain-specific `Farm`, `Area`, `Plot`, or `Location` types into the BEM core model.

## Composing larger Facts

Existing boundary Facts can themselves be composed into larger boundaries.

```kotlin
val farm = boundary("Off-Grid LABO Farm Play") {
    add(grid)

    EmployeeA.measure {
        it.human inflow 1.5.month
    }

    Pump1.measure {
        it.electric outflow 1.2.kwh
        it.water outflow 500.l
    }

    Battery2.measure {
        it.electric inflow 800.wh
        it.electric outflow 300.wh
    }
}
```

This gives the DSL three distinct composition operations:

```text
measure { ... }   create a new Measurement Fact and add it
boundary { ... }  create a new Boundary Fact and add it
add(...)          add an existing Fact
```

## Publishing

Creating and composing Facts is separate from publishing them.

Publishing scopes use `include` to select which Facts should be published.

### HTTP

```kotlin
http(client = client) {
    url = Url("http://localhost:8080/facts")

    include(
        pump1Measured,
        takeWater,
        grid,
        farm,
    )
}
```

`include` does not modify the Fact hierarchy.

It only selects Facts for the current publishing operation.

This distinction is intentional:

```text
add(fact)
    Add a Fact to the structure of another Fact.

include(fact)
    Include a Fact in a publishing operation.
```

## Individual Fact files

Facts can be written individually.

```kotlin
fact(format = Json) {
    outputFor = { fact ->
        SystemFileSystem.sink(
            Path("facts/${fact.id.value}/fact.json")
        ).buffered()
    }

    include(
        pump1Measured,
        takeWater,
        grid,
        farm,
    )
}
```

`outputFor` is evaluated for each Fact.

This allows the application to decide how Facts are stored.

For example, Facts can be partitioned by date:

```kotlin
val timeZone = TimeZone.of("Asia/Tokyo")

fact(format = Json) {
    outputFor = { fact ->
        val localDateTime =
            fact.timestamp.toLocalDateTime(timeZone)

        val directory = Path(
            "fact/" +
                "${localDateTime.year}/" +
                "${localDateTime.month}/" +
                "${localDateTime.day}/" +
                fact.id.value
        )

        SystemFileSystem.createDirectories(directory)

        SystemFileSystem.sink(
            Path(directory, "fact.json")
        ).buffered()
    }

    include(
        pump1Measured,
        takeWater,
        grid,
        farm,
    )
}
```

The resulting storage layout may look like:

```text
fact/
└── 2026/
    └── AUGUST/
        └── 1/
            └── <FactId>/
                ├── fact.json
                └── fact.xml
```

The SDK does not impose a directory layout or naming policy.

Storage organization remains an application concern.

## Documents

Multiple Facts can also be serialized as a single document.

```kotlin
document(format = Json) {
    output =
        SystemFileSystem
            .sink(Path("facts.json"))
            .buffered()

    include(
        pump1Measured,
        takeWater,
        grid,
        farm,
    )
}
```

XML documents are supported in the same way:

```kotlin
document(format = Xml) {
    output =
        SystemFileSystem
            .sink(Path("facts.xml"))
            .buffered()

    include(
        pump1Measured,
        takeWater,
        grid,
        farm,
    )
}
```

`fact` and `document` intentionally represent different publishing strategies.

```text
fact
    Fact → output
    Fact → output
    Fact → output

document
    Fact ─┐
    Fact ─┼→ Document → output
    Fact ─┘
```

A document is an SDK serialization/publishing representation.

The BEM core model itself does not require a collection of top-level Facts to form a document.

## Fact identity

Facts retain their identity when composed into other Facts.

For example:

```kotlin
val measured = Pump1.measure {
    it.water outflow 500.m3
}

val area = boundary("Area A") {
    add(measured)
}

val farm = boundary("Farm") {
    add(measured)
    add(area)
}
```

The same `measured` Fact may therefore appear in multiple contexts.

Conceptually:

```text
                 ┌── Area A
measured Fact ───┤
                 └── Farm
```

Adding an existing Fact does not imply that the measurement happened again.

Its Fact ID and timestamp remain those of the original Fact.

Serialized formats may embed the same Fact more than once when producing a self-contained document, but the Fact ID preserves its identity.

## Design

The BEM core model is concerned with Facts and their composition.

The SDK provides the mechanisms used to construct and publish those Facts.

```text
BEM Core
────────────────────────────
Fact
CompositeFact
Context
Equipment
Resource
Quantity
Direction
...

SDK DSL
────────────────────────────
measure
boundary
add

SDK Publishing
────────────────────────────
include
fact
document
http
formats
transports
storage policy
```

In particular:

- Core does not require Facts to be collected into a top-level list.
- Core does not define a document wrapper.
- Core does not define file or directory layouts.
- Core does not decide whether Facts are published individually or together.
- Core Facts remain independent of JSON, XML, HTTP, files, or other transport concerns.

The SDK exposes those choices without changing the meaning of the underlying Facts.
