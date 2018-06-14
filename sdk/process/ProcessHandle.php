<?php
namespace process;

use php\lang\Process;

/**
 * Class ProcessHandle
 * @package process
 */
class ProcessHandle
{
    /**
     * ProcessHandle constructor.
     * @param Process $process
     */
    public function __construct(Process $process)
    {
    }

    /**
     * @return int
     */
    public function pid(): int
    {
    }

    /**
     * @return bool
     */
    public function destroy(): bool
    {
    }

    /**
     * @return bool
     */
    public function destroyForcibly(): bool
    {
    }

    /**
     * @return bool
     */
    public function isAlive(): bool
    {
    }

    /**
     * @return array
     */
    public function info(): array
    {
    }

    /**
     * @return null|ProcessHandle
     */
    public function parent(): ?ProcessHandle
    {
    }

    /**
     * Returns a snapshot of the current direct children of the process.
     * The `parent` of a direct child process is the process.
     * Typically, a process that is `isAlive` not alive has no children.
     *
     * @return ProcessHandle[]
     */
    public function children(): array
    {
    }

    /**
     * Returns a snapshot of the descendants of the process.
     * The descendants of a process are the children of the process
     * plus the descendants of those children, recursively.
     * Typically, a process that is isAlive() not alive has no children.
     *
     * @return ProcessHandle[]
     */
    public function descendants(): array
    {
    }

    /**
     *
     * @return bool
     */
    public function supportsNormalTermination(): bool
    {
    }

    /**
     * @param int $pid
     * @return ProcessHandle
     */
    public static function ofPid(int $pid): ?ProcessHandle
    {
    }

    /**
     * @return ProcessHandle
     */
    public static function current(): ProcessHandle
    {
    }

    /**
     * @return ProcessHandle[]
     */
    public static function allProcesses(): array
    {
    }
}