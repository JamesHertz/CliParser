package jh.cliApp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CliCmdArg {
    String value();
    String desc() default  "";
}
