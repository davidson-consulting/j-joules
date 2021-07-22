package org.powerapi.jjoules.jni;

/**
 * @author Benjamin DANGLOT
 * benjamin.danglot@davidson.fr
 * on 06/04/2021
 */
public class Perf {

    public native void start();

    public native long[] stop();

    public native long[] read();

    static {
        System.loadLibrary("perf");
    }

}
