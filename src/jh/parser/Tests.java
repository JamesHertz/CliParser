package jh.parser;

import jh.parser.Argument;
import jh.parser.Parser;
import jh.parser.ParserFormat;
import jh.parser.exeptions.BadArgumentException;
import jh.parser.exeptions.WrongNumberOfArgsException;
import org.junit.Test;

import java.util.Iterator;

import static jh.parser.DataType.*;
import static jh.parser.LineParser.parseLine;

import static org.junit.Assert.*;
public class Tests {

    @Test
    public void lpTest1(){
        assertArrayEquals(parseLine("10 'james hertz' hertz"), new String[]{"10", "james hertz", "hertz"});
        assertArrayEquals(parseLine("tmp 'abc\\n' \\n"), new String[]{"tmp", "abc\n", "\\n"});
        assertArrayEquals(parseLine("tmp 'hi there\"' \\n"), new String[]{"tmp", "hi there\"", "\\n"});
        assertArrayEquals(parseLine("I just' doing something'"), new String[]{"I", "just", " doing something"});

        /*
        try{
            parseLine("10 'james' hertz' hertz");
            fail();
        }catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException);
        }
         */
    }

    @Test
    public void lpTest2(){
        try{
            parseLine("10 'james' hertz' hertz");
            fail();
        }catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException);
        }


        try{
            parseLine("that's it");
            fail();
        }catch (Exception e){
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Test
    public void test1(){
        Parser p = new ParserFormat(DECIMAL, STRING, STRING);
        Iterator<Argument> it = p.parse("10 'James Hertz' Hertz");
        assertEquals(10.0, it.next().toDecimal(), 0);
        assertEquals("James Hertz", it.next().toString());
        assertEquals("Hertz", it.next().toString());

        p = new ParserFormat(STRING , INTEGER, DECIMAL);
        it = p.parse("roulette 10 100.5");
        assertEquals("roulette", it.next().toString());
        assertEquals(10, it.next().toInteger());
        assertEquals(100.5, it.next().toDecimal(), 0);
    }

    @Test
    public void test02(){
       Parser p = new ParserFormat(INTEGER, INTEGER, INTEGER);
        Iterator<Argument> it;
       try{
            it = p.parse("hi there how");
            fail();
       }catch (Exception e){
           assertTrue(e instanceof BadArgumentException);
       }

        try{
            it = p.parse("hi there how 10");
            fail();
        }catch (Exception e){
            assertTrue(e instanceof WrongNumberOfArgsException);
        }
    }

}
