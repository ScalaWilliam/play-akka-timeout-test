# Demonstration of Play akka-http handling of timeout

## akka-http behaviour
`sbt run` and in another console: `curl -i http://localhost:9000`.

This produces a response after 20 seconds:
```
HTTP/1.1 503 Service Unavailable
Date: Sat, 11 Mar 2017 01:44:28 GMT
Content-Type: text/plain; charset=UTF-8
Content-Length: 105

The server was not able to produce a timely response to your request.
Please try again in a short while!%
```

This is not good.

## netty behaviour
`sbt -Duse-netty= run` and in another terminal: `curl -i http://localhost:9000`.
This produces no response, which is good.

# Why it matters

There may be cases where producing a response simply takes too long,
such as a transaction, an initial load or a background task.

And it may easily take over 20 seconds.

## Backwards compatibility
There may be others who experience a similar problem and won't find out until they migrate.

## My use case
In my particular use case I load in a bunch of reference data and do a computation
that takes a while but enables my app to live without a database and keep
it 100% Scala and 0% SQL. This computation will take 1 minute or more.

Both production, test and dev use cases have a similar problem:
I go to the initial page `/` and it times out. In dev case I need to call this endpoint to just compile the app.
Then I have to refresh again after a while (and I don't know after how long, since size of data/CPU availability availability changes),
when I could've just waited for the request to complete and seen it complete loading successfully in my browser tab.

As a side note in dev I use Hazelcast to cache the computed data.
