package jh.projects.cliparser.cliApp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CliAppCommand {
    String key() default "";
    String desc() default "";
}
