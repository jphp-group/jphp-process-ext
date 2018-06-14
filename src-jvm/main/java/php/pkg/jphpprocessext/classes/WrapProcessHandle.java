package php.pkg.jphpprocessext.classes;

import java.lang.ProcessHandle.Info;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import php.pkg.jphpprocessext.JphpProcessExtExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection.Name;
import php.runtime.annotation.Reflection.Namespace;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.ext.core.classes.WrapProcess;
import php.runtime.ext.core.classes.time.WrapTime;
import php.runtime.lang.BaseWrapper;
import php.runtime.memory.ArrayMemory;
import php.runtime.reflection.ClassEntity;

@Name("ProcessHandle")
@Namespace(JphpProcessExtExtension.NS)
public class WrapProcessHandle extends BaseWrapper<ProcessHandle> {

    public WrapProcessHandle(Environment env, ProcessHandle wrappedObject) {
        super(env, wrappedObject);
    }

    public WrapProcessHandle(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Signature
    public void __construct(WrapProcess process) {
        if (process.getProcess() == null) {
            throw new IllegalStateException("Process must be started");
        }

        __wrappedObject = process.getProcess().toHandle();
    }

    @Signature
    public ProcessHandle parent() {
        return getWrappedObject().parent().orElse(null);
    }

    @Signature
    public boolean supportsNormalTermination() {
        return getWrappedObject().supportsNormalTermination();
    }

    @Signature
    public List<ProcessHandle> children() {
        return getWrappedObject().children().collect(Collectors.toList());
    }

    @Signature
    public List<ProcessHandle> descendants() {
        return getWrappedObject().descendants().collect(Collectors.toList());
    }

    @Signature
    public long pid() {
        return getWrappedObject().pid();
    }

    @Signature
    public boolean destroy() {
        return getWrappedObject().destroy();
    }

    @Signature
    public boolean destroyForcibly() {
        return getWrappedObject().destroyForcibly();
    }

    @Signature
    public boolean isAlive() {
        return getWrappedObject().isAlive();
    }

    @Signature
    public Memory info(Environment env) {
        ArrayMemory info = new ArrayMemory();

        Info inf = getWrappedObject().info();
        info.refOfIndex("command").assign(inf.command().orElse(""));
        info.refOfIndex("commandLine").assign(inf.commandLine().orElse(""));

        Optional<String[]> arguments = inf.arguments();
        if (arguments.isPresent()) {
            info.refOfIndex("arguments").assign(ArrayMemory.ofStrings(arguments.get()));
        } else {
            info.refOfIndex("arguments").assign(new ArrayMemory());
        }

        info.refOfIndex("user").assign(inf.user().orElse(""));

        Optional<Duration> duration = inf.totalCpuDuration();

        if (duration.isPresent()) {
            info.refOfIndex("totalCpuDuration").assign(duration.get().toMillis());
        }

        Optional<Instant> instant = inf.startInstant();

        if (instant.isPresent()) {
            info.refOfIndex("startedAt").assign(new WrapTime(env, new Date(instant.get().toEpochMilli())));
        } else {
            info.refOfIndex("startedAt").assign(Memory.NULL);
        }

        return info.toConstant();
    }

    @Signature
    public static ProcessHandle ofPid(long pid) {
        return ProcessHandle.of(pid).orElse(null);
    }

    @Signature
    public static ProcessHandle current() {
        return ProcessHandle.current();
    }

    @Signature
    public static List<ProcessHandle> allProcesses() {
        return ProcessHandle.allProcesses().collect(Collectors.toList());
    }
}
