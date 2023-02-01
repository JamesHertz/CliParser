package jh.cliApp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CliCommand {
    String value() default "";
    String desc() default "";
}