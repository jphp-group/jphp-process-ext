package php.pkg.jphpprocessext;

import php.pkg.jphpprocessext.classes.WrapProcessHandle;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class JphpProcessExtExtension extends Extension {
    public static final String NS = "process";
    
    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }
    
    @Override
    public void onRegister(CompileScope scope) {
        registerWrapperClass(scope, ProcessHandle.class, WrapProcessHandle.class);
    }
}