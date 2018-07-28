# AndroidRxDemo

### Scheduler
> 1. **immediate()**: Creates and returns a Scheduler that executes work immediately on the current thread.
>
> 2. **trampoline()**: Creates and returns a Scheduler that queues work on the current thread to be executed after the current work completes.
>
> 3. **newThread()**: Creates and returns a Scheduler that creates a new Thread for each unit of work.
>
> 4. **computation()**: Creates and returns a Scheduler intended for computational work. This can be used for event-loops, processing callbacks and other computational work. Do not perform IO-bound work on this scheduler. Use Schedulers.**io()** instead.
>
> 5. **io()**: Creates and returns a Scheduler intended for IO-bound work.
The implementation is backed by an Executor thread-pool that will grow as needed. This can be used for asynchronously performing blocking IO. Do not perform computational work on this scheduler. Use Schedulers.**computation()** instead.


### Reference
- https://stackoverflow.com/questions/31276164/rxjava-schedulers-use-cases