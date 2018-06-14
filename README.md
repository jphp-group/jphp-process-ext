# jphp-process-ext
Process API for jphp

### How to use?

1. Get process handle of a process instance:

```php
use php\lang\Process;
use process\ProcessHandle;

$process = new Process(['cmd', '/c', 'calc.exe']);
$processHandle = new ProcessHandle($process->start());

echo "PID: ", $processHandle->pid(), "\n";
echo "Info: ", print_r($processHandle->info(), true), "\n";
```

2. How to destroy process?

```php
$process = new Process(['cmd', '/c', 'calc.exe']);
$processHandle = new ProcessHandle($process->start());

$success = $processHandle->destroy();
```

3. How to get children processes?
```php
$processHandles = $processHandle->children();

foreach ($processHandles as $handle) {
   var_dump($handle->info());
}
```

4. How to get all process handles?
```php
$allProcesses = ProcessHandle::allProcesses();
```

5. How to get process handle by pid?
```php
$pid = 13894;
$processHandler = ProcessHandle::ofPid($pid);
```

6. How to get process handle of current process?
```php
$processHandler = ProcessHandle::current();
```