package jh.cliApp.annotations;

public @interface Command {
    String name();
    String desc() default "";
}
